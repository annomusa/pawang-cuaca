package com.icehousecorp.maunorafiq.data.weather.net

import com.icehousecorp.maunorafiq.data.weather.entity.response.WeatherResponse

import retrofit2.http.GET
import retrofit2.http.QueryMap
import rx.Observable

/**
 * Created by maunorafiq on 11/16/16.
 */

interface WeatherApi {
    @GET("weather")
    fun getWeather(@QueryMap parameter: Map<String, String>): Observable<WeatherResponse>
}
