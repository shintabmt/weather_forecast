package com.example.weatherforecast.data.source.local

import com.example.weatherforecast.data.models.Weather
import com.example.weatherforecast.data.source.local.database.WeatherDao
import javax.inject.Inject

class WeatherLocalDataSource @Inject constructor(private val weatherDao: WeatherDao) {

    suspend fun save(query: String, weathers: List<Weather>) {
        weatherDao.insert(WeatherForecastEntity(query, weathers))
    }

    suspend fun loadWeather(query: String): List<Weather>? {
        return weatherDao.queryWeather(query)?.weathers
    }
}
