package com.marko.starwars.data.resident

import com.marko.starwars.data.database.AppDatabase
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ResidentLocalDS(val appDatabase: AppDatabase) : ResidentDataSource {
    override fun getResident(residentId: Int): Single<Resident> {
        return appDatabase.residentDao()
            .getResident(residentId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getResidents(): Observable<List<Resident>> {
        return appDatabase.residentDao()
            .getResidents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}