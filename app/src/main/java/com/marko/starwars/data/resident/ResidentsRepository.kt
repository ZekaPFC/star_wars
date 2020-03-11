package com.marko.starwars.data.resident

import com.marko.starwars.data.planet.PlanetRemoteDS
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ResidentsRepository @Inject constructor(
    private val residentLocalDS: ResidentLocalDS,
    private val residentRemoteDS: ResidentRemoteDS,
    private val planetRemoteDS: PlanetRemoteDS
) {
    fun getResidents(): Single<List<Resident>> {
        return residentLocalDS.getResidents()
    }

    fun getResident(residentId: Int): Single<Resident> {
        return residentLocalDS.getResident(residentId)
    }

    /**
     * Resident obtaining mechanics.
     * We first download planet with list of URLs for residents, then download resident one by one
     * and save it in local DB
     */
    fun fetchResidents(): Completable {
        var residentId = 0
        return planetRemoteDS.getPlanet(10)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { planet -> planet.residentsUrl?.map { it } }
            .flatMap { listOfUrls -> Observable.fromIterable(listOfUrls) }
            .subscribeOn(Schedulers.io())
            .flatMapSingle {
                residentId = getResidentIdFromUrl(it)
                fetchResident(it)
            }
            .flatMapCompletable { saveResident(residentId, it) }
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun fetchResident(residentUrl: String): Single<Resident> {
        return residentRemoteDS.getResident(residentUrl)
    }

    private fun saveResident(id: Int, resident: Resident): Completable {
        resident.residentId = id
        return residentLocalDS.saveResident(resident)
    }

    private fun getResidentIdFromUrl(residentUrl: String): Int {
        return residentUrl.substring(residentUrl.lastIndexOf("/").plus(1)).toInt()
    }
}