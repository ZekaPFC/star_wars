package com.marko.starwars.ui.utils

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefUtil @Inject constructor(private val pref: SharedPreferences) {
    private val PLANET_LIKE = "planet_liked"

    fun likePlanet() {
        pref.edit().putBoolean(PLANET_LIKE, true).apply()
    }

    fun isAlreadyLiked(): Boolean {
        return pref.getBoolean(PLANET_LIKE, false)
    }

}