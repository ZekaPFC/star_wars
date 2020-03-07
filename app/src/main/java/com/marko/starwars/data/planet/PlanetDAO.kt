package com.marko.starwars.data.planet

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

abstract class PlanetDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun savePlanet(vararg planet: Planet): Single<Long>

    @Query("SELECT * FROM Planet WHERE id = :planetId")
    abstract fun getPlanet(planetId: Int): Single<Planet>

    @Query("UPDATE planet SET likes = :likes WHERE id = :planetId")
    abstract fun likePlanet(planetId: Int, likes: Int): Single<Long>
}