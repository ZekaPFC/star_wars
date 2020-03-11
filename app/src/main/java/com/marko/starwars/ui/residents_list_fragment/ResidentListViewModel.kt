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

    /**
     * Residents obtaining method.
     * We try to refresh residents from network and if there isn't any data even in local DB
     * we show empty data screen
     */
    private fun fetchResidentFromNetwork() {
        compositeDisposable.add(residentRepo.fetchResidents()
            .subscribe {
                if (isDataAvailable.value != true) {
                    noDataAvailable()
                }
            })
    }

    private fun getResidents() {
        compositeDisposable.add(residentRepo.getResidents()
            .subscribe({ onDataLoaded(it) }, { noDataAvailable() })
        )
    }

    private fun onDataLoaded(residents: List<Resident>) {
        _residents.value = residents
        renderScreenBasedOnResponse(residents)
    }

    private fun renderScreenBasedOnResponse(residents: List<Resident>) {
        if (residents.isNullOrEmpty()) {
            noDataAvailable()
        } else {
            showContent()
        }
    }
}