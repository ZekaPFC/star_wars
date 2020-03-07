package com.marko.starwars.data.planet

import io.reactivex.Single

class PlanetLocalDS: PlanetDataSource {
    override fun getPlanet(planetId: Int): Single<Planet> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun likePlanet(planetId: Int): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}