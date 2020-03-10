package com.marko.starwars.data.resident

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "resident")
data class Resident(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var residentId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "height") val height: String,
    @ColumnInfo(name = "mass") val mass: String,
    @SerializedName("hair_color") @ColumnInfo(name = "hair_color") val hairColor: String,
    @SerializedName("skin_color") @ColumnInfo(name = "skin_color") val skinColor: String,
    @SerializedName("eye_color") @ColumnInfo(name = "eye_color") val eyeColor: String,
    @SerializedName("birth_year") @ColumnInfo(name = "birth_yeah") val birthYear: String,
    @ColumnInfo(name = "gender") val gender: String,
    @SerializedName("image_url") @ColumnInfo(name = "image_url") val imageUrl: String
)