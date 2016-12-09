package com.example.maunorafiq.pawangcuaca.presentation.model

/**
 * Created by maunorafiq on 11/29/16.
 */

class ForecastModel(val cityName: String) {

    var currentWeather: WeatherModel? = null
    var forecastList: List<WeatherModel>? = null

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("*** Forecast ***")
        stringBuilder.append("City name : " + cityName)
        stringBuilder.append("Weather : " + currentWeather.toString())
        stringBuilder.append("Forecast : " + forecastList?.size)

        return stringBuilder.toString()
    }
}
