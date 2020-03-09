package com.marko.starwars.UI.PlanetFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.marko.starwars.UI.BaseViewModel
import com.marko.starwars.data.planet.Planet
import com.marko.starwars.data.planet.PlanetRepository
import javax.inject.Inject

class PlanetViewModel @Inject constructor(
    private val planetRepository: PlanetRepository
) : BaseViewModel() {

    val planetMLiveData: MutableLiveData<Planet> = MutableLiveData()
    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getPlanet(10)
        refreshPlanet(10)
    }

    fun refreshPlanet(id: Int) {
        compositeDisposable.add(planetRepository.refreshPlanet(id).subscribe({
            Log.d("planet", "Success")
        }, { Log.d("planet", it.localizedMessage) }))
    }

    fun getPlanet(id: Int) {
        compositeDisposable.add(planetRepository.getPlanet(id)
            .doOnSubscribe { startLoading() }
            .subscribe({
                stopLoading()
                emitLoadedData(it)
            }, { it.localizedMessage })
        )
    }

    private fun startLoading() {
        loadingLiveData.postValue(true)
    }

    private fun stopLoading() {
        loadingLiveData.postValue(false)
    }

    private fun emitLoadedData(planet: Planet) {
        planetMLiveData.postValue(planet)
    }
}