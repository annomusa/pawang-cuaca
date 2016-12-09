package com.example.maunorafiq.pawangcuaca.presentation.mapper

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel
import com.icehousecorp.maunorafiq.domain.weather.Weather

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Collections
import java.util.Locale
import java.util.TimeZone

import javax.inject.Inject

/**
 * Created by maunorafiq on 11/29/16.
 */
@PerActivity
class WeatherModelDataMapper
@Inject
constructor() {

    fun transform(weather: Weather): WeatherModel {
        val weatherModel = WeatherModel()
        weatherModel.cityName = weather.cityName
        weatherModel.weatherId = weather.weatherId
        weatherModel.weatherName = weather.weatherName
        weatherModel.weatherDescription = weather.weatherDescription
        weatherModel.weatherIcon = weather.weatherIcon
        weatherModel.temperature = weather.temperature + 0x00B0.toChar().toString() + "C"
        weatherModel.pressure = weather.pressure + " hPa"
        weatherModel.humidity = weather.humidity + "%"

        val calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getDefault()
        calendar.timeInMillis = (weather.utcTime * 1000).toLong()

        val hour = Integer.valueOf(SimpleDateFormat("HH", Locale.ENGLISH).format(calendar.time))!!
        val hourBegin = (hour - 2).toString()
        val hourEnd = (Integer.valueOf(hourBegin)!! + 3).toString()

        weatherModel.day = SimpleDateFormat("EEE", Locale.ENGLISH).format(calendar.time)
        weatherModel.hourBegin = hourBegin
        weatherModel.hourEnd = hourEnd

        return weatherModel
    }

    fun transform(weathers: List<Weather>?): List<WeatherModel> {
        val result: List<WeatherModel>

        if (weathers != null) {
            result = weathers.map { transform(it) }
        } else {
            result = emptyList<WeatherModel>()
        }
        return result
    }

}
