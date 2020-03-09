package com.marko.starwars.UI.PlanetFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.marko.starwars.DI.component.FragmentComponent
import com.marko.starwars.DI.scope.FragmentScope
import com.marko.starwars.R
import com.marko.starwars.UI.MainActivity
import com.marko.starwars.UI.utils.SizeUtil
import com.marko.starwars.data.planet.Planet
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.planet_fragment.*
import javax.inject.Inject

@FragmentScope
class PlanetFragment : Fragment() {

    private lateinit var fragmentComponent: FragmentComponent
    @Inject
    lateinit var planetViewModel: PlanetViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentComponent = (context as MainActivity).appComponent.fragmentComponent().create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentComponent.inject(this)
        configureLoadingObservable()
        configurePlanetObservable()
        return inflater.inflate(R.layout.planet_fragment, container, false)
    }

    private fun configurePlanetObservable() {
        planetViewModel.planetMLiveData.observe(viewLifecycleOwner) {
            bindViews(it)
        }
    }

    private fun configureLoadingObservable() {
        planetViewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                progress_bar.visibility = View.VISIBLE
                ll_content.visibility = View.GONE
            } else {
                progress_bar.visibility = View.GONE
                ll_content.visibility = View.VISIBLE
            }
        }
    }

    private fun bindViews(planet: Planet) {
        val imageSize: Int = SizeUtil.dpToPx(250f, resources).toInt()
        val populationInMilion: Int = planet.population!!.toInt().div(10000000)
        Picasso.get().load(planet.imageUrl).resize(imageSize, imageSize).into(iv_planet)
        tv_diameter.text = getString(R.string.diameter).plus(": ").plus(planet.diameter)
        tv_gravity.text = getString(R.string.gravity).plus(": ").plus(planet.gravity)
        tv_like.text = planet.likes.toString().plus(" ").plus(getString(R.string.likes))
        tv_orbital_period.text =
            getString(R.string.orbital_period).plus(": ").plus(planet.orbitalPeriod)
        tv_climate.text = getString(R.string.climate).plus(": ").plus(planet.climate)

        tv_planet_name.text = planet.name
        tv_population.text = getString(R.string.population).plus(": ").plus(populationInMilion).plus("M")

        tv_rotation_period.text =
            getString(R.string.rotation_period).plus(": ").plus(planet.rotationPeriod)

        tv_surface_water.text =
            getString(R.string.surface_water).plus(": ").plus(planet.surfaceWatter)
        tv_terrain.text = getString(R.string.terrain).plus(": ").plus(planet.terrain)
    }
}