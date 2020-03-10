package com.marko.starwars.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.marko.starwars.data.database.AppDatabase
import com.marko.starwars.data.resident.Resident
import com.marko.starwars.ui.utils.SizeUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
class AppModule(val context: Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideSharedPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "star-warsdb").build()
    }

    @Provides
    fun provideResidentList(): MutableList<Resident> {
        return mutableListOf()
    }
}