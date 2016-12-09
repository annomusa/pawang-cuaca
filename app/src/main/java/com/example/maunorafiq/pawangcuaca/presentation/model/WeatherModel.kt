package com.example.maunorafiq.pawangcuaca.presentation.model

/**
 * Created by maunorafiq on 11/29/16.
 */

class WeatherModel {

    var weatherId: Int = 0

    var weatherName: String? = null

    var weatherDescription: String? = null

    var weatherIcon: String? = null

    var cityName: String? = null

    var temperature: String? = null

    var pressure: String? = null

    var humidity: String? = null

    var day: String? = null

    var hourBegin: String? = null

    var hourEnd: String? = null

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("*** Weather Model ***\n")
        stringBuilder.append("City Name : " + this.cityName + "\n")
        stringBuilder.append("Weather Id : " + this.weatherId + "\n")
        stringBuilder.append("Weather Name : " + this.weatherName + "\n")
        stringBuilder.append("Weather Description : " + this.weatherDescription + "\n")
        stringBuilder.append("Weather Icon : " + this.weatherIcon + "\n")
        stringBuilder.append("Temperature : " + this.temperature + "\n")
        stringBuilder.append("Pressure : " + this.pressure + "\n")
        stringBuilder.append("Humidity : " + this.humidity + "\n")
        stringBuilder.append("UTC Day : " + this.day + "\n")
        stringBuilder.append("UTC Hour : " + this.hourBegin + "-" + this.hourEnd + "\n")

        return stringBuilder.toString()
    }
}
