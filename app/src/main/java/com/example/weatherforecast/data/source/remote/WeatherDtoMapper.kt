package com.example.weatherforecast.data.source.remote

import com.example.weatherforecast.data.models.Weather
import java.util.*

class WeatherDtoMapper {

    fun map(dto: WeatherResponseDto): List<Weather> {
        return dto.list.map {
            Weather(
                date = Date(it.dt.toLong()),
                temperature = it.temp.day,
                pressure = it.pressure,
                humidity = it.humidity,
                description = it.weather.firstOrNull()?.description ?: "",
            )
        }
    }
}
