package com.example.weatherforecast.data.source.local

import com.example.weatherforecast.data.models.Weather
import javax.inject.Inject

class WeatherLocalDataSource @Inject constructor(private val dataStore: WeatherDataStore) {

    suspend fun save(query: String, weathers: List<Weather>) {
        dataStore.save(query, weathers)
    }

    suspend fun loadWeather(query: String): List<Weather>? {
        return dataStore.get(query)
    }

    suspend fun clear() {
        dataStore.clear()
    }
}
