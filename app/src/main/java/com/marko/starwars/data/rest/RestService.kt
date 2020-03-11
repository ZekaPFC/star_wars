package com.marko.starwars.data.rest

import com.marko.starwars.data.planet.Planet
import com.marko.starwars.data.resident.Resident
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface RestService {
    @GET("planets/{id}")
    fun getPlanet(@Path("id") id: Int): Single<Planet>

    @POST("planets/{id}/like")
    fun likePlanet(@Path("id") id: Int): Single<Planet>

    @GET
    fun getResident(@Url url: String): Single<Resident>
}