package com.example.weatherforecast.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherforecast.data.source.local.WeatherForecastEntity

@Database(entities = [WeatherForecastEntity::class], version = 1)
@TypeConverters(WeatherConverter::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}