package com.marko.starwars.ui.residents_list_fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marko.starwars.data.resident.Resident
import com.marko.starwars.databinding.ResidentListItemBinding
import javax.inject.Inject

class ResidentAdapter @Inject constructor(
    private val context: Context,
    private val residents: MutableList<Resident>
) :
    RecyclerView.Adapter<ResidentHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResidentHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemBinding = ResidentListItemBinding.inflate(layoutInflater, parent, false)
        val residentHolder = ResidentHolder(itemBinding)
        itemBinding.holder = residentHolder
        return residentHolder
    }

    override fun getItemCount(): Int {
        return residents.size
    }

    override fun onBindViewHolder(holder: ResidentHolder, position: Int) {
        val resident = residents[position]
        holder.bind(resident)
    }

    fun addItems(residents: List<Resident>, clearPrevious: Boolean = false) {
        if (clearPrevious) {
            this.residents.clear()
        }
        this.residents.addAll(residents)
        notifyDataSetChanged()
    }
}