package com.marko.starwars.di.module

import com.marko.starwars.di.component.FragmentComponent
import com.marko.starwars.di.scope.FragmentScope
import dagger.Module

@FragmentScope
@Module(subcomponents = [FragmentComponent::class])
class SubcomponentModule