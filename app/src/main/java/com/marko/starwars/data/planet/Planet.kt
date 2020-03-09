package com.marko.starwars.data.planet

import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
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
    @SerializedName("image_url") @ColumnInfo(name = "picture_url") val imageUrl: String?
)