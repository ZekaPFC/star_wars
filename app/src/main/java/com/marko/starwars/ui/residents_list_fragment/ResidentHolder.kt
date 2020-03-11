package com.marko.starwars.ui.residents_list_fragment

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.marko.starwars.data.resident.Resident
import javax.inject.Inject

open class ResidentHolder @Inject constructor(private val viewDataBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {

    fun bind(resident: Resident) {
        viewDataBinding.setVariable(BR.resident, resident)
        viewDataBinding.executePendingBindings()
    }

    fun navigateToAction(view: View) {
        view.findNavController()
            .navigate(
                ResidentListFragmentDirections
                    .actionResidentListFragmentToResidentProfileFragment()
            )
    }
}