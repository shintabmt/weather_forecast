package com.example.weatherforecast.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherforecast.data.models.Weather

@Entity(tableName = "weather")
data class WeatherForecastEntity(
    @PrimaryKey val query: String,
    @ColumnInfo(name = "weathers") val weathers: List<Weather>
)