package com.example.weatherforecast.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.base.BaseViewModel
import com.example.weatherforecast.data.models.Weather
import com.example.weatherforecast.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    BaseViewModel() {
    private val _weathers = MutableLiveData<List<Weather>>()

    val weathers: LiveData<List<Weather>>
        get() = _weathers

    fun searchWeather(query: String) {
        viewModelScope.launch(handler) {
            showLoading(true)
            weatherRepository.loadWeathers(query)
                .also {
                    showLoading(false)
                }.fold(
                    onSuccess = {
                        _weathers.value = it
                    },
                    onFailure = {
                        handleError(it)
                    }
                )
        }
    }
}