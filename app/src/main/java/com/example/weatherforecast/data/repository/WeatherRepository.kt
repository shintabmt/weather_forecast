package com.example.weatherforecast.data.repository

import com.example.weatherforecast.data.source.local.WeatherLocalDataSource
import com.example.weatherforecast.data.source.remote.WeatherRemoteDataSource
import com.example.weatherforecast.utils.ValueConst
import com.example.weatherforecast.utils.ValueConst.UNIT
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val defaultDispatcher: CoroutineDispatcher,
    private val localDataSource: WeatherLocalDataSource,
    private val remoteDataSource: WeatherRemoteDataSource,
) {
    suspend fun loadWeathers(
        query: String,
    ) = withContext(defaultDispatcher) {
        val localData = localDataSource.loadWeather(query)
        if (localData == null) {
            remoteDataSource.fetchWeather(query = query, cnt = ValueConst.CNT_LENGTH, unit = UNIT)
                .onSuccess {
                    localDataSource.save(query, it)
                }
        } else {
            Result.success(localData)
        }
    }
}