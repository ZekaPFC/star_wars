package com.marko.starwars.data.planet

import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class PlanetRepository @Inject constructor(
    private val planetLocalDS: PlanetLocalDS,
    private val planetRemoteDS: PlanetRemoteDS
) {

    fun getPlanet(id: Int): Observable<Planet> {
        return planetLocalDS.getPlanet(id)
    }

    fun likePlanet(id: Int, likes: Int): Completable {
        return planetRemoteDS.likePlanet(id, likes)
            .andThen(planetLocalDS.likePlanet(id, likes))
    }

    fun refreshPlanet(id: Int): Completable {
        return planetRemoteDS.getPlanet(id)
            .doOnNext { savePlanet(it) }
            .ignoreElements()
    }

    private fun savePlanet(planet: Planet) {
        planet.planetId = 10
        planetLocalDS.savePlanet(planet)
    }
}