package com.marko.starwars.ui.residents_list_fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marko.starwars.data.resident.Resident
import com.marko.starwars.data.resident.ResidentsRepository
import com.marko.starwars.di.scope.FragmentScope
import com.marko.starwars.ui.BaseViewModel
import javax.inject.Inject

@FragmentScope
class ResidentListViewModel @Inject constructor(private val residentRepo: ResidentsRepository) :
    BaseViewModel() {

    private val _residents = MutableLiveData<List<Resident>>()
    val residentsLiveData: LiveData<List<Resident>> = _residents

    init {
        fetchResidentFromNetwork()
        getResidents()
    }

    private fun fetchResidentFromNetwork() {
        compositeDisposable.add(residentRepo.fetchResidents()
            .subscribe(
                { Log.d("resident", "fetched residents") },
                { Log.d("resident", "error") }
            ))
    }

    private fun getResidents() {
        compositeDisposable.add(residentRepo.getResidents()
            .subscribe(
                { _residents.value = it },
                { Log.d("residentLocal", it.message) }
            ))
    }
}