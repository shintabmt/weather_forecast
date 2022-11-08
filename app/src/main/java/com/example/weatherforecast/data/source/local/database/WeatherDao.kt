package com.example.weatherforecast.data.source.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherforecast.data.source.local.WeatherForecastEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather WHERE query ==:query")
    suspend fun queryWeather(query: String): WeatherForecastEntity?

    @Insert
    suspend fun insert(weatherForecastEntity: WeatherForecastEntity)
}