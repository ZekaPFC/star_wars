package com.marko.starwars.data.planet

import io.reactivex.Completable
import io.reactivex.Observable

interface PlanetDataSource {
    fun getPlanet(planetId: Int): Observable<Planet>
    fun likePlanet(planetId: Int, likes: Int): Completable
}
