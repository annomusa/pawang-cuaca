package com.icehousecorp.maunorafiq.data.forecast.repository.datasource

import com.google.gson.GsonBuilder
import com.icehousecorp.maunorafiq.data.forecast.entity.response.ForecastResponse
import com.icehousecorp.maunorafiq.data.forecast.net.ForecastApi

import java.util.HashMap

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

import com.icehousecorp.maunorafiq.data.Constant.API_BASE_URL
import com.icehousecorp.maunorafiq.data.Constant.openWeatherApi

/**
 * Created by maunorafiq on 11/29/16.
 */

class CloudForecastDataStore : ForecastDataStore {

    private val restApi: ForecastApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        this.restApi = retrofit.create(ForecastApi::class.java)
    }

    override fun getForecastEntity(city: String): Observable<ForecastResponse> {
        val data = HashMap<String, String>()
        data.put("q", city)
        data.put("units", "metric")
        data.put("appid", openWeatherApi)
        return this.restApi.getForecast(data)
    }
}
