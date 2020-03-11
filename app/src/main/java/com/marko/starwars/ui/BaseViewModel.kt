package com.marko.starwars.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isDataAvailable = MutableLiveData<Boolean>()
    val isDataAvailable: LiveData<Boolean> = _isDataAvailable

    fun startLoading() {
        _isLoading.value = true
        _isDataAvailable.value = false
    }

    fun noDataAvailable() {
        _isLoading.value = false
        _isDataAvailable.value = false
    }

    fun showContent() {
        _isDataAvailable.value = true
        _isLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }

    }
}