package com.example.weatherforecast.data.source.local.database;

import androidx.room.TypeConverter;

import com.example.weatherforecast.data.models.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class WeatherConverter {

    @TypeConverter
    public String fromWeatherList(List<Weather> weathers) {
        if (weathers == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather>>() {
        }.getType();
        return gson.toJson(weathers, type);
    }

    @TypeConverter
    public List<Weather> toWeatherList(String weathersString) {
        if (weathersString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather>>() {
        }.getType();
        return gson.fromJson(weathersString, type);
    }
}