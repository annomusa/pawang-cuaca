package com.icehousecorp.maunorafiq.domain.weather.repository

import com.icehousecorp.maunorafiq.domain.weather.Weather

import rx.Observable

/**
 * Created by maunorafiq on 12/2/16.
 */

interface WeatherRepository {
    fun currentWeather(city: String): Observable<Weather>

}
