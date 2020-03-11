package com.marko.starwars.ui.planet_fragment

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.marko.starwars.data.planet.Planet
import com.marko.starwars.data.planet.PlanetRepository
import com.marko.starwars.di.scope.FragmentScope
import com.marko.starwars.ui.BaseViewModel
import com.marko.starwars.ui.utils.PrefUtil
import javax.inject.Inject


@FragmentScope
class PlanetViewModel @Inject constructor(
    private val planetRepository: PlanetRepository,
    private val prefUtil: PrefUtil
) : BaseViewModel() {
    private val _planetContent = MutableLiveData<Planet>()

    private val _name: MutableLiveData<String> = MutableLiveData()
    val nameLiveData: LiveData<String> = _name

    private val _rotationPeriod = MutableLiveData<String>()
    val rotationPeriodLiveData: LiveData<String> = _rotationPeriod

    private val _orbitalPeriod = MutableLiveData<String>()
    val orbitalPeriodLiveData: LiveData<String> = _orbitalPeriod

    private val _diameter = MutableLiveData<String>()
    val diameterLiveData: LiveData<String> = _diameter

    private val _climate = MutableLiveData<String>()
    val climateLiveData: LiveData<String> = _climate

    private val _gravity = MutableLiveData<String>()
    val gravityLiveData: LiveData<String> = _gravity

    private val _terrain = MutableLiveData<String>()
    val terrainLiveData: LiveData<String> = _terrain

    private val _surfaceWater = MutableLiveData<String>()
    val surfaceWaterLiveData: LiveData<String> = _surfaceWater

    private val _population = MutableLiveData<String>()
    val populationLiveData: LiveData<String> = _population

    private val _likes = MutableLiveData<Int>()
    val likesLiveData: LiveData<Int> = _likes

    private val _imageUrl = MutableLiveData<String>()
    val imageUrlLiveData: LiveData<String> = _imageUrl


    private val _isLiked = MutableLiveData<Boolean>()
    val isLikedLiveData: LiveData<Boolean> = _isLiked


    init {
        getPlanet(10)
        refreshPlanet(10)
        _isLiked.value = isPlanetLiked()
    }

    private fun refreshPlanet(id: Int) {
        compositeDisposable.add(planetRepository.refreshPlanet(id).subscribe({
            Log.d("planet", "Success")
        }, {
            if (isDataAvailable.value == false) {
                noDataAvailable()
            }
            Log.d("planet", it.localizedMessage)
        }))
    }

    private fun getPlanet(id: Int) {
        compositeDisposable.add(planetRepository.getPlanet(id)
            .doOnSubscribe { startLoading() }
            .subscribe({
                _planetContent.value = it
                showContent()
                bindViews(it)
            }, {
                noDataAvailable()
                it.localizedMessage
            })
        )
    }

    fun navigateToEnlargeProfilePicScreen(view: View) {
        view.findNavController()
            .navigate(
                PlanetFragmentDirections.actionPlanetFragmentToEnlargedProfileImageFragment(
                    _imageUrl.value!!
                )
            )
    }

    private fun bindViews(planet: Planet) {
        val populationInMilion: Int = planet.population!!.toInt().div(10000000)
        _diameter.postValue(": ".plus(planet.diameter.toString()))
        _gravity.postValue(": ".plus(planet.gravity))
        _likes.postValue(planet.likes)
        _orbitalPeriod.postValue(": ".plus(planet.orbitalPeriod))
        _climate.postValue(": ".plus(planet.climate))
        _name.postValue(planet.name)
        _population.postValue(": ".plus(populationInMilion).plus("M"))
        _rotationPeriod.postValue(": ".plus(planet.rotationPeriod))
        _surfaceWater.postValue(": ".plus(planet.surfaceWatter))
        _terrain.postValue(": ".plus(planet.terrain))
        _imageUrl.postValue(planet.imageUrl)
    }

    fun likePlanet() {
        if (_isLiked.value == false || _isLiked.value == null) {
            compositeDisposable.add(
                planetRepository.likePlanet(
                    10, _planetContent.value!!.likes.plus(1)
                ).subscribe({
                    setLikePlanetDrawable()
                }, { Log.d("LikeError", it.localizedMessage) })
            )
        }
    }

    private fun isPlanetLiked(): Boolean {
        return prefUtil.isAlreadyLiked()
    }

    private fun setLikePlanetDrawable() {
        prefUtil.likePlanet()
        _isLiked.value = true
    }

    fun navigateToResidentList(view: View) {
        view.findNavController()
            .navigate(PlanetFragmentDirections.actionPlanetFragmentToResidentListFragment())
    }
}