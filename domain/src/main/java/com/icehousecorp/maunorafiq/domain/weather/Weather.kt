package com.icehousecorp.maunorafiq.domain.weather

/**
 * Created by maunorafiq on 11/28/16.
 */

class Weather(val cityId: Int) {

    var cityName: String? = null
    var utcTime: Int = 0
    var weatherId: Int = 0
    var weatherName: String? = null
    var weatherDescription: String? = null
    var weatherIcon: String? = null
    var temperature: String? = null
    var pressure: String? = null
    var humidity: String? = null

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("*** Weather Weather ***\n")
        stringBuilder.append("City id : " + this.cityId + "\n")
        stringBuilder.append("City name : " + this.cityName + "\n")
        stringBuilder.append("UTC Time : " + this.utcTime + "\n")
        stringBuilder.append("Weather id : " + this.weatherId + "\n")
        stringBuilder.append("Weather name : " + this.weatherName + "\n")
        stringBuilder.append("Weather Description : " + this.weatherDescription + "\n")
        stringBuilder.append("Weather Icon : " + this.weatherIcon + "\n")
        stringBuilder.append("Weather temperature : " + this.temperature + "\n")
        stringBuilder.append("Weather Pressure : " + this.pressure + "\n")
        stringBuilder.append("Weather humidity : " + this.humidity + "\n")
        stringBuilder.append("***********************\n")

        return stringBuilder.toString()
    }
}
