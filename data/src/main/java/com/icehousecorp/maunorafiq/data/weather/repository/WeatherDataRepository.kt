package com.icehousecorp.maunorafiq.data.weather.repository

import com.icehousecorp.maunorafiq.data.weather.entity.mapper.WeatherEntityDataMapper
import com.icehousecorp.maunorafiq.data.weather.repository.datasource.WeatherDataStore
import com.icehousecorp.maunorafiq.data.weather.repository.datasource.WeatherDataStoreFactory
import com.icehousecorp.maunorafiq.domain.weather.Weather
import com.icehousecorp.maunorafiq.domain.weather.repository.WeatherRepository

import javax.inject.Inject
import javax.inject.Singleton

import rx.Observable

/**
 * Created by maunorafiq on 12/2/16.
 */
@Singleton
class WeatherDataRepository
@Inject
constructor(private val weatherDataStoreFactory: WeatherDataStoreFactory,
            private val weatherEntityDataMapper: WeatherEntityDataMapper) : WeatherRepository {

    override fun currentWeather(city: String): Observable<Weather> {
        val weatherDataStore = this.weatherDataStoreFactory.create()

        return weatherDataStore
                .getWeatherEntity(city)
                .map({ weatherResponse -> weatherEntityDataMapper.transform(weatherResponse, city) })
    }
}
