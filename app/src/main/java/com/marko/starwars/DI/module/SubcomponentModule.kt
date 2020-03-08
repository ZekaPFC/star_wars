package com.marko.starwars.DI.module

import com.marko.starwars.DI.component.FragmentComponent
import com.marko.starwars.DI.scope.FragmentScope
import dagger.Module

@FragmentScope
@Module(subcomponents = [FragmentComponent::class])
class SubcomponentModule