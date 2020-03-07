package com.marko.starwars.data.planet

import com.marko.starwars.data.rest.RestService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PlanetRemoteDS(private val restService: RestService): PlanetDataSource {
    override fun getPlanet(planetId: Int): Single<Planet> {
        return restService.getPlanet(planetId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun likePlanet(planetId: Int):Single<Boolean> {
       return restService.likePlanet(planetId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { return@map true }
    }
}