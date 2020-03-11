package com.marko.starwars.di.component

import com.marko.starwars.di.module.AppModule
import com.marko.starwars.di.module.NetworkModule
import com.marko.starwars.di.module.SubcomponentModule
import com.marko.starwars.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SubcomponentModule::class, NetworkModule::class,AppModule::class])
interface AppComponent {
    fun fragmentComponent():FragmentComponent.Factory
    fun inject(mainActivity: MainActivity)
}