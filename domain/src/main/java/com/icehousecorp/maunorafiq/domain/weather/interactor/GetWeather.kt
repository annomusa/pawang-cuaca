package com.icehousecorp.maunorafiq.domain.weather.interactor

import com.icehousecorp.maunorafiq.domain.UseCase
import com.icehousecorp.maunorafiq.domain.weather.repository.WeatherRepository

import javax.inject.Inject

import rx.Observable

/**
 * Created by maunorafiq on 11/28/16.
 */

class GetWeather
@Inject
constructor(private val weatherRepository: WeatherRepository) : UseCase() {

    private var city = "Amsterdam"

    override fun buildUseCaseObservable(): Observable<*> {
        return this.weatherRepository.currentWeather(this.city)
    }

    fun setCity(city: String?) {
        this.city = city ?: "Amsterdam"
    }
}