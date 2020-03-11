package com.marko.starwars.data.resident


import com.marko.starwars.data.rest.RestService
import io.reactivex.Single
import javax.inject.Inject

class ResidentRemoteDS @Inject constructor(private val restService: RestService) {
    fun getResident(residentUrl: String): Single<Resident> {
        return restService.getResident(residentUrl)
    }
}