package com.marko.starwars.base

import android.app.Application
import com.marko.starwars.DI.component.AppComponent
import com.marko.starwars.DI.component.DaggerAppComponent
import com.marko.starwars.DI.module.AppModule

class BaseApp : Application() {

    val appComponent: AppComponent = DaggerAppComponent.builder()
        .appModule(AppModule(this))
        .build()

    override fun onCreate() {
        super.onCreate()

    }

}