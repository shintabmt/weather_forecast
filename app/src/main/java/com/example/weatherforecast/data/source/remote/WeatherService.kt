package com.example.weatherforecast.data.source.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/data/2.5/forecast/daily?appid=60c6fbeb4b93ac653c492ba806fc346d")
    suspend fun fetchWeather(
        @Query("q") query: String,
        @Query("cnt") cnt: Int?,
        @Query("unit") unit: String?,
    ): Result<WeatherResponseDto>
}
