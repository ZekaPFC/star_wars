package com.marko.starwars.data.resident

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Resident(
    @PrimaryKey @ColumnInfo(name = "id") val residentId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "mass") val mass: Int,
    @ColumnInfo(name = "hair_color") val hairColor: String,
    @ColumnInfo(name = "skin_color") val skinColor: String,
    @ColumnInfo(name = "eye_color") val eyeColor: String,
    @ColumnInfo(name = "birth_yeah") val birthYear: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "image_url") val imageUrl: String
)