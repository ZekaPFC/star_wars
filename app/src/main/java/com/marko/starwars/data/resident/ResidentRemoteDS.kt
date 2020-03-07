package com.marko.starwars.data.resident


import com.marko.starwars.data.rest.RestService
import io.reactivex.Observable
import io.reactivex.Single


class ResidentRemoteDS(private val restService: RestService): ResidentDataSource {
    override fun getResident(residentId: Int): Single<Resident> {
       return restService.getResident(residentId)
    }

    override fun getResidents(): Observable<List<Resident>> {
        TODO("Not Implemented")
    }
}