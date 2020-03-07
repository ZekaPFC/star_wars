package com.marko.starwars.data.resident

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ResidentsRepository(
    private val residentLocalDS: ResidentDataSource,
    private val residentRemoteDS: ResidentDataSource
) {

    fun getResident(id: Int): Single<Resident> {
        return residentRemoteDS.getResident(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

}