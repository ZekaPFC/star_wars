package com.marko.starwars.data.resident

import com.marko.starwars.data.database.AppDatabase
import com.marko.starwars.di.scope.FragmentScope
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@FragmentScope
class ResidentLocalDS @Inject constructor(private val appDatabase: AppDatabase) {
    fun getResident(residentId: Int): Single<Resident> {
        return appDatabase.residentDao()
            .getResident(residentId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getResidents(): Single<List<Resident>> {
        return appDatabase.residentDao()
            .getResidents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun saveResident(resident: Resident): Completable {
        return appDatabase.residentDao().saveResident(resident)
    }
}