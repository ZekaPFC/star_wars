package com.marko.starwars.ui.resident_profile_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marko.starwars.data.resident.Resident
import com.marko.starwars.data.resident.ResidentsRepository
import com.marko.starwars.di.scope.FragmentScope
import com.marko.starwars.ui.BaseViewModel
import javax.inject.Inject

@FragmentScope
class ResidentProfileViewModel @Inject constructor(private val residentRepo: ResidentsRepository) :
    BaseViewModel() {

    private val _name = MutableLiveData<String>()
    val nameLiveData: LiveData<String> = _name

    private val _gender = MutableLiveData<String>()
    val genderLiveData: LiveData<String> = _gender

    private val _hairColor = MutableLiveData<String>()
    val hairColorLiveData: LiveData<String> = _hairColor

    private val _skinColor = MutableLiveData<String>()
    val skinColorLiveData: LiveData<String> = _skinColor

    private val _height = MutableLiveData<String>()
    val heightLiveData: LiveData<String> = _height

    private val _eyeColor = MutableLiveData<String>()
    val eyeColorLiveData: LiveData<String> = _eyeColor

    private val _mass = MutableLiveData<String>()
    val massLiveData: LiveData<String> = _mass

    private val _birthYear = MutableLiveData<String>()
    val birthYearLiveData: LiveData<String> = _birthYear

    private val _imageUrl = MutableLiveData<String>()
    val imageUrlLiveData: LiveData<String> =_imageUrl

    fun getResidentById(residentId: Int) {
        compositeDisposable.add(
            residentRepo
                .getResident(residentId)
                .subscribe({ bindViews(it) }, {})
        )
    }

    private fun bindViews(resident: Resident) {
        _name.value = resident.name
        _gender.value = resident.gender
        _hairColor.value = resident.hairColor
        _skinColor.value = resident.skinColor
        _height.value = resident.height
        _eyeColor.value = resident.eyeColor
        _mass.value = resident.mass
        _birthYear.value = resident.birthYear
        _imageUrl.value= resident.imageUrl
    }
}