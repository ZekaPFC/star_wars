package com.marko.starwars.DI.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
class AppModule(val  context: Context) {
    @Provides
    fun provideContext(): Context{
        return context
    }
}