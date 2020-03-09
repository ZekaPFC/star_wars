package com.marko.starwars.DI.module

import android.content.Context
import android.content.SharedPreferences
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
    fun provideSizeUtils(context: Context): SizeUtil{
        return SizeUtil(context)
    }

    @Singleton
    @Provides
    fun provideSharedPrefs(context: Context):SharedPreferences{
        return context.getSharedPreferences("myPrefs",Context.MODE_PRIVATE)
    }

}