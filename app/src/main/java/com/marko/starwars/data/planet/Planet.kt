package com.marko.starwars.data.planet

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Planet(
    @PrimaryKey @ColumnInfo(name = "id") val planetId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "rotation_period") val rotationPeriod: Int,
    @ColumnInfo(name = "orbital_period") val orbitalPeriod: Int,
    @ColumnInfo(name = "diameter") val diameter: Int,
    @ColumnInfo(name = "climate") val climate: String,
    @ColumnInfo(name = "gravity") val gravity: String,
    @ColumnInfo(name = "terrain") val terrain: String,
    @ColumnInfo(name = "surface_water") val surfaceWatter: String,
    @ColumnInfo(name = "population") val population: String,
    @ColumnInfo(name = "residents_url") val residentsUrl: List<String>,
    @ColumnInfo(name = "likes") val likes: Int
)