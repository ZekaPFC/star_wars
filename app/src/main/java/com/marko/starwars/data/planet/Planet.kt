package com.marko.starwars.data.planet

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "planet")
data class Planet(
    @PrimaryKey @ColumnInfo(name = "id") var planetId: Int,
    @ColumnInfo(name = "name") val name: String?,
    @SerializedName("rotation_period") @ColumnInfo(name = "rotation_period") val rotationPeriod: Int,
    @SerializedName("orbital_period") @ColumnInfo(name = "orbital_period") val orbitalPeriod: Int,
    @ColumnInfo(name = "diameter") val diameter: Int,
    @ColumnInfo(name = "climate") val climate: String?,
    @ColumnInfo(name = "gravity") val gravity: String?,
    @ColumnInfo(name = "terrain") val terrain: String?,
    @SerializedName("surface_water") @ColumnInfo(name = "surface_water") val surfaceWatter: String?,
    @ColumnInfo(name = "population") val population: String?,
    @ColumnInfo(name = "likes") val likes: Int,
    @SerializedName("image_url") @ColumnInfo(name = "picture_url") val imageUrl: String?,
    @Ignore @SerializedName("residents") val residentsUrl: List<String>?
) {
    constructor(
        planetId: Int,
        name: String?,
        rotationPeriod: Int,
        orbitalPeriod: Int,
        diameter: Int,
        climate: String?,
        gravity: String?,
        terrain: String?,
        surfaceWatter: String?,
        population: String?,
        likes: Int,
        imageUrl: String?
    ) : this(
        planetId,
        name,
        rotationPeriod,
        orbitalPeriod,
        diameter,
        climate,
        gravity,
        terrain,
        surfaceWatter,
        population,
        likes,
        imageUrl,
        null
    )
}
