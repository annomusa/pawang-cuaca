package com.icehousecorp.maunorafiq.data.weather.repository.datasource

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Raffi on 11/25/2016.
 */
@Singleton
class WeatherDataStoreFactory
@Inject
constructor() {

    fun create(): WeatherDataStore {
        val weatherDataStore: WeatherDataStore

        weatherDataStore = CloudWeatherDataStore()

        return weatherDataStore
    }
}
