package com.example.weatherforecast.di

import com.example.weatherforecast.data.source.local.WeatherDataStore
import com.example.weatherforecast.data.source.remote.WeatherDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ProviderModule {

    @Singleton
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    @Singleton
    @Provides
    fun provideWeatherDtoMapper(): WeatherDtoMapper {
        return WeatherDtoMapper()
    }

    @Singleton
    @Provides
    fun provideWeatherDataStore(): WeatherDataStore {
        return WeatherDataStore()
    }
}

