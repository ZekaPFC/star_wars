package com.marko.starwars.DI.component

import com.marko.starwars.DI.scope.FragmentScope
import com.marko.starwars.UI.PlanetFragment.PlanetFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface FragmentComponent {
    @Subcomponent.Builder
    interface Factory {
        fun create(): FragmentComponent
    }

    fun inject(planetFragment: PlanetFragment)
}