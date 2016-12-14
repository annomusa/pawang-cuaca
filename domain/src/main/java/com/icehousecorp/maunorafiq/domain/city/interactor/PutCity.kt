package com.icehousecorp.maunorafiq.domain.city.interactor

import com.icehousecorp.maunorafiq.domain.UseCase
import com.icehousecorp.maunorafiq.domain.city.repository.CityRepository
import com.icehousecorp.maunorafiq.domain.weather.repository.WeatherRepository

import javax.inject.Inject

import rx.Observable

/**
 * Created by maunorafiq on 12/2/16.
 */

class PutCity
@Inject
constructor(private val cityRepository: CityRepository,
            private val weatherRepository: WeatherRepository) : UseCase() {

    private var city = "Berlin"

    override fun buildUseCaseObservable(): Observable<*> {
        if (this.cityRepository.putCity(city)) {
            return weatherRepository.currentWeather(city)
        } else {
            return Observable.just(null)
        }
    }

    fun setCity(city: String) {
        this.city = city
    }
}
