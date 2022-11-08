package com.example.weatherforecast.data.source.local

import com.example.weatherforecast.data.models.Weather

class WeatherDataStore() {

    private var _weathers: List<Weather>? = null
    private var _query: String? = null

    fun save(query: String, weathers: List<Weather>?) {
        this._query = query
        this._weathers = weathers
    }

    fun get(query: String): List<Weather>? {
        return if (_query == query) _weathers else null
    }

    fun clear() {
        _weathers = null
        _query = null
    }
}
