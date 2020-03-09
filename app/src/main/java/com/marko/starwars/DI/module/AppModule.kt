package com.marko.starwars.DI.module

import android.content.Context
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
}