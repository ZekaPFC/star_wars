package com.marko.starwars.ui.planet_fragment

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marko.starwars.data.planet.Planet
import com.marko.starwars.data.planet.PlanetRepository
import com.marko.starwars.ui.BaseViewModel
import com.marko.starwars.ui.utils.SizeUtil
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import javax.inject.Inject


class PlanetViewModel @Inject constructor(
    private val planetRepository: PlanetRepository,
    private val sizeUtil: SizeUtil
) : BaseViewModel() {

    val planetMLiveData: MutableLiveData<Planet> = MutableLiveData()
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

    private val _likes = MutableLiveData<String>()
    val likesLiveData: LiveData<String> = _likes

    private val _imageUrl = MutableLiveData<String>()
    val imageUrlLiveData: LiveData<String> = _imageUrl

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    val imageSize: Int = sizeUtil.dpToPx(250f).toInt()


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
                bindViews(it)
            }, { it.localizedMessage })
        )
    }

    private fun startLoading() {
        _isLoading.postValue(true)
    }

    private fun stopLoading() {
        _isLoading.postValue(false)
    }

    private fun emitLoadedData(planet: Planet) {
        planetMLiveData.postValue(planet)
    }

    private fun bindViews(planet: Planet) {
        val populationInMilion: Int = planet.population!!.toInt().div(10000000)
        _diameter.postValue(": ".plus(planet.diameter.toString()))
        _gravity.postValue(": ".plus(planet.gravity))
        _likes.postValue(" " + planet.likes.toString())
        _orbitalPeriod.postValue(": ".plus(planet.orbitalPeriod))
        _climate.postValue(": ".plus(planet.climate))
        _name.postValue(planet.name)
        _population.postValue(": ".plus(populationInMilion))
        _rotationPeriod.postValue(": ".plus(planet.rotationPeriod))
        _surfaceWater.postValue(": ".plus(planet.surfaceWatter))
        _terrain.postValue(": ".plus(planet.terrain))
        _imageUrl.postValue(planet.imageUrl)
    }

    companion object {
        @BindingAdapter("imageUrl","imageSize")
        @JvmStatic
        fun loadImage(view: CircleImageView, imageUrl: String?,imageSize: Int) {
            Picasso.get()
                .load(imageUrl)
                 .resize(imageSize, imageSize)
                .into(view)
        }
    }


}