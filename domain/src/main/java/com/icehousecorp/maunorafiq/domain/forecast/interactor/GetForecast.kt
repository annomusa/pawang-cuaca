package com.icehousecorp.maunorafiq.domain.forecast.interactor

import com.icehousecorp.maunorafiq.domain.UseCase
import com.icehousecorp.maunorafiq.domain.forecast.repository.ForecastRepository

import javax.inject.Inject

import rx.Observable

/**
 * Created by maunorafiq on 11/29/16.
 */

class GetForecast
@Inject
constructor(private val forecastRepository: ForecastRepository) : UseCase() {

    private var city = "Amsterdam"

    override fun buildUseCaseObservable(): Observable<*> {
        return this.forecastRepository.forecastWeather(this.city)
    }

    fun setCity(city: String) {
        this.city = city
    }
}
