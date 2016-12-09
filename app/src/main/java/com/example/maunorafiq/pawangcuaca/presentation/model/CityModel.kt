package com.example.maunorafiq.pawangcuaca.presentation.model

/**
 * Created by maunorafiq on 12/5/16.
 */

class CityModel(val cityName: String) {

    var ordinal: Int = 0
    var weatherModel: WeatherModel? = null

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("*** City Model ***\n")
        stringBuilder.append("City name : " + this.cityName + "\n")
        stringBuilder.append("City Ordinal : " + this.ordinal + "\n")
        stringBuilder.append("Weather Icon : " + this.weatherModel.toString() + "\n")
        stringBuilder.append("******************\n")

        return stringBuilder.toString()
    }
}
