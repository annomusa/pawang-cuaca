package com.icehousecorp.maunorafiq.data.forecast.net

import com.icehousecorp.maunorafiq.data.forecast.entity.response.ForecastResponse

import retrofit2.http.GET
import retrofit2.http.QueryMap
import rx.Observable

/**
 * Created by maunorafiq on 11/29/16.
 */

interface ForecastApi {
    @GET("forecast")
    fun getForecast(@QueryMap parameter: Map<String, String>): Observable<ForecastResponse>
}
