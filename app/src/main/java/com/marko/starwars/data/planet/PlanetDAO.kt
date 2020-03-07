package com.marko.starwars.data.planet

import androidx.room.Insert
import androidx.room.OnConflictStrategy

abstract class PlanetDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun savePlanet(planet: Planet): Long
}