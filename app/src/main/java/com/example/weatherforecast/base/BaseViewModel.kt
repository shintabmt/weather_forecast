package com.example.weatherforecast.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel : ViewModel() {


    val handler = CoroutineExceptionHandler { _, exception ->
        handleError(exception)
    }

    private val _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error

    private val _loading = MutableLiveData<Boolean>()

    val loading: LiveData<Boolean>
        get() = _loading

    fun handleError(it: Throwable) {
        it.printStackTrace()
        _error.postValue(it.message)
    }

    fun showLoading(it: Boolean) {
        _loading.postValue(it)
    }
}
