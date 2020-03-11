package com.marko.starwars.base

import android.app.Application
import com.marko.starwars.di.component.AppComponent
import com.marko.starwars.di.component.DaggerAppComponent
import com.marko.starwars.di.module.AppModule

class BaseApp : Application() {
    val appComponent: AppComponent = DaggerAppComponent.builder()
        .appModule(AppModule(this))
        .build()
}