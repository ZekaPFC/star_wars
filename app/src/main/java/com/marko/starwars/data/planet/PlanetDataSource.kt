package com.marko.starwars.data.planet

import io.reactivex.Single

interface PlanetDataSource {

    fun getPlanet(planetId: Int): Single<Planet>

    fun likePlanet(planetId: Int): Single<Boolean>
}