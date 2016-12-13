package com.icehousecorp.maunorafiq.data.weather.repository.datasource

import com.google.gson.GsonBuilder
import com.icehousecorp.maunorafiq.data.weather.net.WeatherApi
import com.icehousecorp.maunorafiq.data.weather.entity.response.WeatherResponse

import java.util.HashMap

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

import com.icehousecorp.maunorafiq.data.Constant.API_BASE_URL
import com.icehousecorp.maunorafiq.data.Constant.openWeatherApi

/**
 * Created by Raffi on 11/25/2016.
 */

class CloudWeatherDataStore : WeatherDataStore {

    private val restApi: WeatherApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        this.restApi = retrofit.create(WeatherApi::class.java)
    }

    override fun getWeatherEntity(city: String): Observable<WeatherResponse> {
        val data = HashMap<String, String>()
        data.put("q", city)
        data.put("units", "metric")
        data.put("appid", openWeatherApi)
        return this.restApi.getWeather(data)
    }
}
