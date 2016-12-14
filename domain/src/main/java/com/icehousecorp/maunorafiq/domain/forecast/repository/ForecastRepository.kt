package com.icehousecorp.maunorafiq.domain.forecast.repository

import com.icehousecorp.maunorafiq.domain.forecast.Forecast

import rx.Observable

/**
 * Created by maunorafiq on 11/29/16.
 */

interface ForecastRepository {
    fun forecastWeather(city: String): Observable<Forecast>
}
