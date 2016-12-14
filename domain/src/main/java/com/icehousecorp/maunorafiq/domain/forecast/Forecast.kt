package com.icehousecorp.maunorafiq.domain.forecast

import com.icehousecorp.maunorafiq.domain.weather.Weather

/**
 * Created by maunorafiq on 11/29/16.
 */

class Forecast(val cityId: Int) {

    var cityName: String? = null
    var forecastList: List<Weather>? = null
}
