package com.marko.starwars.data.planet

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
abstract class PlanetDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun savePlanet(vararg planet: Planet): Completable

    @Query("SELECT * FROM Planet WHERE id = :planetId")
    abstract fun getPlanet(planetId: Int): Flowable<Planet>

    @Query("UPDATE planet SET likes = :likes WHERE id = :planetId")
    abstract fun likePlanet(planetId: Int, likes: Int): Completable
}