package com.marko.starwars.data.planet

import com.marko.starwars.data.database.AppDatabase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlanetLocalDS @Inject constructor(private val appDatabase: AppDatabase) : PlanetDataSource {
    override fun getPlanet(planetId: Int): Observable<Planet> {
        return appDatabase.planetDao()
            .getPlanet(planetId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
    }

    override fun likePlanet(planetId: Int, likes: Int): Completable {
        return appDatabase.planetDao()
            .likePlanet(planetId, likes)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun savePlanet(planet: Planet) {
        appDatabase.planetDao()
            .savePlanet(planet)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}