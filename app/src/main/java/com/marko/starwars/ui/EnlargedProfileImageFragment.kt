package com.marko.starwars.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.marko.starwars.di.scope.FragmentScope
import com.marko.starwars.databinding.ProfileImageFragmentBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.profile_image_fragment.*

@FragmentScope
class EnlargedProfileImageFragment : Fragment() {
    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideAppBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val profileFragmentBinding = ProfileImageFragmentBinding.inflate(inflater, container, false)
        profileFragmentBinding.picFragment = this
        return profileFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args by navArgs<EnlargedProfileImageFragmentArgs>()
        loadImage(args.imageUrl)
    }

    private fun loadImage(imageUrl: String) {
        Picasso.get().load(imageUrl).into(iv_planet_large)
    }

    fun close() {
        findNavController().navigateUp()
    }
}