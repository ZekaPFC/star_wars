package com.marko.starwars.ui.resident_profile_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marko.starwars.R
import com.marko.starwars.databinding.ResidentProfileFragmentBinding
import com.marko.starwars.di.scope.FragmentScope
import com.marko.starwars.ui.MainActivity
import javax.inject.Inject

@FragmentScope
class ResidentProfileFragment : Fragment() {

    @Inject
    lateinit var residentProfileViewModel: ResidentProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).appComponent.fragmentComponent().create().inject(this)
        (activity as MainActivity).appBarTitle(getString(R.string.resident_profile))
        val viewBinding = ResidentProfileFragmentBinding.inflate(inflater, container, false)
        viewBinding.viewmodel = residentProfileViewModel
        viewBinding.lifecycleOwner = this
        residentProfileViewModel.getResidentById(73)
        return viewBinding.root
    }
}