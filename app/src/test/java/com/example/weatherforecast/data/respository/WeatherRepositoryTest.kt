package com.example.weatherforecast.data.respository

import com.example.weatherforecast.data.models.Weather
import com.example.weatherforecast.data.repository.WeatherRepository
import com.example.weatherforecast.data.source.local.WeatherLocalDataSource
import com.example.weatherforecast.data.source.remote.WeatherRemoteDataSource
import com.example.weatherforecast.utils.ValueConst
import io.mockk.*
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class WeatherRepositoryTest {


    private var localDataSource: WeatherLocalDataSource = mockk()


    private var remoteDataSource: WeatherRemoteDataSource = mockk()

    private val repository = WeatherRepository(
        UnconfinedTestDispatcher(),
        localDataSource,
        remoteDataSource
    )

    val weather = Weather(
        Date(), 0.0, 0, 0, "description"
    )

    @Test
    fun loadWeathersWithCacheData() = runTest(UnconfinedTestDispatcher()) {
        val query = "query"
        val cnt = ValueConst.CNT_LENGTH
        val unit = ValueConst.UNIT
        val weathers = listOf(weather)

        coEvery {
            localDataSource.loadWeather(
                any(),
            )
        } returns weathers

        coEvery {
            localDataSource.save(
                any(),
                any(),
            )
        } just Runs
        coEvery {
            remoteDataSource.fetchWeather(
                any(),
                any(),
                any(),
            )
        } returns Result.success(emptyList<Weather>())

        val result = repository.loadWeathers(query)

        coVerify { localDataSource.loadWeather(query) }
        coVerify(exactly = 0) { remoteDataSource.fetchWeather(query, cnt, unit) }
        coVerify(exactly = 0) { localDataSource.save(query, weathers) }
        assert(result.isSuccess)
        assert(result.getOrNull() == weathers)

    }

    @Test
    fun loadWeathersWithoutCacheDataSuccess() = runTest(UnconfinedTestDispatcher()) {
        val query = "query"
        val cnt = ValueConst.CNT_LENGTH
        val unit = ValueConst.UNIT
        val weathers = listOf(weather)

        coEvery {
            localDataSource.loadWeather(
                any(),
            )
        } returns null

        coEvery {
            localDataSource.save(
                any(),
                any(),
            )
        } just Runs
        coEvery {
            remoteDataSource.fetchWeather(
                any(),
                any(),
                any(),
            )
        } returns Result.success(weathers)

        val result = repository.loadWeathers(query)

        coVerify { localDataSource.loadWeather(query) }
        coVerify { remoteDataSource.fetchWeather(query, cnt, unit) }
        coVerify { localDataSource.save(query, weathers) }
        assert(result.isSuccess)
        assert(result.getOrNull() == weathers)

    }

    @Test
    fun loadWeathersWithoutCacheDataFailure() = runTest(UnconfinedTestDispatcher()) {
        val query = "query"
        val cnt = ValueConst.CNT_LENGTH
        val unit = ValueConst.UNIT
        val weathers = listOf(weather)
        val exception = java.lang.RuntimeException()

        coEvery {
            localDataSource.loadWeather(
                any(),
            )
        } returns null

        coEvery {
            localDataSource.save(
                any(),
                any(),
            )
        } just Runs
        coEvery {
            remoteDataSource.fetchWeather(
                any(),
                any(),
                any(),
            )
        } returns Result.failure(exception)

        val result = repository.loadWeathers(query)

        coVerify { localDataSource.loadWeather(query) }
        coVerify { remoteDataSource.fetchWeather(query, cnt, unit) }
        coVerify(exactly = 0) { localDataSource.save(query, weathers) }
        assert(result.isFailure)
        assert(result.getOrNull() == null)
        assert(result.exceptionOrNull() == exception)

    }
}
