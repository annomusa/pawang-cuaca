package com.icehousecorp.maunorafiq.data.forecast.entity.mapper

import com.icehousecorp.maunorafiq.data.forecast.entity.response.ForecastResponse
import com.icehousecorp.maunorafiq.data.forecast.entity.response.ListTime
import com.icehousecorp.maunorafiq.domain.forecast.Forecast
import com.icehousecorp.maunorafiq.domain.weather.Weather

import java.util.ArrayList

import javax.inject.Inject
import javax.inject.Singleton

import com.icehousecorp.maunorafiq.data.Constant.iconImageUrl

/**
 * Created by maunorafiq on 11/29/16.
 */

@Singleton
class ForecastEntityDataMapper
@Inject
constructor() {

    fun transform(forecastResponse: ForecastResponse?, city: String): Forecast? {
        var forecast: Forecast? = null
        if (forecastResponse != null) {
            forecast = Forecast(forecastResponse.city!!.id!!)
            forecast.cityName = city

            val weatherList = ArrayList<Weather>()
            for (item in forecastResponse.list) {
                val weather = Weather(forecastResponse.city!!.id!!)
                weather.cityName = forecastResponse.city!!.name
                weather.weatherId = item.weather[0].id!!
                weather.weatherName = item.weather[0].main
                weather.weatherDescription = item.weather[0].description
                weather.weatherIcon = iconImageUrl + item.weather[0].icon + ".png"
                weather.temperature = item.main!!.temp!!.toString()
                weather.pressure = item.main!!.pressure!!.toString()
                weather.humidity = item.main!!.humidity!!.toString()
                weather.utcTime = item.dt!!
                weatherList.add(weather)
            }
            forecast.forecastList = weatherList
        }
        return forecast
    }
}
