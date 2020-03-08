package com.marko.starwars.UI.PlanetFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.marko.starwars.DI.component.FragmentComponent
import com.marko.starwars.DI.scope.FragmentScope
import com.marko.starwars.R
import com.marko.starwars.UI.MainActivity
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
            Toast.makeText(context, "Eureka!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun configureLoadingObservable() {
        planetViewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                progress_bar.visibility = View.VISIBLE
            } else {
                progress_bar.visibility = View.GONE
            }
        }
    }
}