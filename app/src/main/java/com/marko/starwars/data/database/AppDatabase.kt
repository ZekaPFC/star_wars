package com.marko.starwars.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marko.starwars.data.planet.Planet
import com.marko.starwars.data.planet.PlanetDAO
import com.marko.starwars.data.resident.Resident
import com.marko.starwars.data.resident.ResidentDAO

@Database(entities = [Planet::class, Resident::class],version = 1)
abstract class AppDatabase(): RoomDatabase() {
    abstract fun planetDao(): PlanetDAO
    abstract fun residentDao(): ResidentDAO
}