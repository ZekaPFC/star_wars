package com.marko.starwars.di.component

import com.marko.starwars.di.scope.FragmentScope
import com.marko.starwars.ui.EnlargedProfileImageFragment
import com.marko.starwars.ui.planet_fragment.PlanetFragment
import com.marko.starwars.ui.residents_list_fragment.ResidentListFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface FragmentComponent {
    @Subcomponent.Builder
    interface Factory {
        fun create(): FragmentComponent
    }

    fun inject(planetFragment: PlanetFragment)
    fun inject(enlargedProfileImageFragment: EnlargedProfileImageFragment)
    fun inject(residentListFragment: ResidentListFragment)
}