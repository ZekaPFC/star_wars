package com.marko.starwars.ui.planet_fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marko.starwars.databinding.PlanetFragmentBinding
import com.marko.starwars.di.component.FragmentComponent
import com.marko.starwars.di.scope.FragmentScope
import com.marko.starwars.ui.MainActivity
import javax.inject.Inject

@FragmentScope
class PlanetFragment : Fragment() {
    private lateinit var fragmentComponent: FragmentComponent
    @Inject
    lateinit var planetViewModel: PlanetViewModel

    private lateinit var viewBinding: PlanetFragmentBinding

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideAppBar()
    }

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
        setupViewBinding(inflater, container)
        return viewBinding.root
    }

    private fun setupViewBinding(layoutInflater: LayoutInflater, container: ViewGroup?) {
        viewBinding = PlanetFragmentBinding.inflate(layoutInflater, container, false)
        viewBinding.viewmodel = planetViewModel
        viewBinding.lifecycleOwner = this
    }
}