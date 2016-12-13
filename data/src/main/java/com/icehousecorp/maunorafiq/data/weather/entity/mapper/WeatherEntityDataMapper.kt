package com.icehousecorp.maunorafiq.data.weather.entity.mapper

import javax.inject.Inject
import javax.inject.Singleton

import com.icehousecorp.maunorafiq.data.weather.entity.response.WeatherResponse
import com.icehousecorp.maunorafiq.domain.weather.Weather

import com.icehousecorp.maunorafiq.data.Constant.iconImageUrl

/**
 * Created by Raffi on 11/25/2016.
 */
@Singleton
class WeatherEntityDataMapper
@Inject
constructor() {

    fun transform(weatherResponse: WeatherResponse?, city: String): Weather? {
        var weather: Weather? = null
        if (weatherResponse != null) {
            weather = Weather(weatherResponse.id!!)
            weather.cityName = city
            weather.weatherId = weatherResponse.weather[0].id!!
            weather.weatherName = weatherResponse.weather[0].main
            weather.weatherDescription = weatherResponse.weather[0].description
            weather.weatherIcon = iconImageUrl + weatherResponse.weather[0].icon + ".png"
            weather.utcTime = weatherResponse.dt!!
            weather.temperature = Integer.toString(weatherResponse.main!!.temp!!.toInt())
            weather.pressure = weatherResponse.main!!.pressure!!.toString()
            weather.humidity = weatherResponse.main!!.humidity!!.toString()
        }
        return weather
    }
}
