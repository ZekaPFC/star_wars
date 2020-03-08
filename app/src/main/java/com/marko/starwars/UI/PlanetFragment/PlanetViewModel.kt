package com.marko.starwars.UI.PlanetFragment

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
        compositeDisposable.add(planetRepository.refreshPlanet(id).subscribe({}, {}))
    }

    fun getPlanet(id: Int) {
        compositeDisposable.add(planetRepository.getPlanet(id)
            .doOnSubscribe { startLoading() }
            .subscribe({
                emitLoadedData(it)
                stopLoading()
            }, { it.localizedMessage })
        )
    }

    private fun startLoading() {
        loadingLiveData.value = true
    }

    private fun stopLoading() {
        loadingLiveData.value = false
    }

    private fun emitLoadedData(planet: Planet) {
        planetMLiveData.value = planet
    }
}