package com.example.weatherforecast.data.models

import java.util.*


data class Weather(
    val date: Date,
    val temperature: Double,
    val pressure: Int,
    val humidity: Int,
    val description: String,
)