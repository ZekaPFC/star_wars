package com.marko.starwars.data.planet

import com.marko.starwars.data.rest.RestService
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlanetRemoteDS @Inject constructor(private val restService: RestService) : PlanetDataSource {
    override fun getPlanet(planetId: Int): Observable<Planet> {
        return restService.getPlanet(planetId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
    }

    override fun likePlanet(planetId: Int, likes: Int): Completable {
        return restService.likePlanet(planetId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .ignoreElement()
    }
}