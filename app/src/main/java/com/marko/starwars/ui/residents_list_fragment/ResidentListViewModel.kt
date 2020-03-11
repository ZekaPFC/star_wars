package com.marko.starwars.ui.residents_list_fragment

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
        startLoading()
        fetchResidentFromNetwork()
        getResidents()
    }

    private fun fetchResidentFromNetwork() {
        compositeDisposable.add(residentRepo.fetchResidents()
            .subscribe {
                if (isDataAvailable.value == null || isDataAvailable.value == false) {
                    noDataAvailable()
                }
            })
    }

    private fun getResidents() {
        compositeDisposable.add(residentRepo.getResidents()
            .subscribe(
                {
                    _residents.value = it
                    if (it.isNotEmpty()) {
                        showContent()
                    } else {
                        noDataAvailable()
                    }

                },
                {
                    noDataAvailable()
                }
            ))
    }
}