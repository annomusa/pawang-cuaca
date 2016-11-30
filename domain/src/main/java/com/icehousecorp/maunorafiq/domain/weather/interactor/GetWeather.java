package com.icehousecorp.maunorafiq.domain.weather.interactor;

import com.icehousecorp.maunorafiq.domain.UseCase;
import com.icehousecorp.maunorafiq.domain.weather.repository.WeatherRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by maunorafiq on 11/28/16.
 */

public class GetWeather extends UseCase {

    private final String city;
    private final WeatherRepository weatherRepository;

    @Inject
    public GetWeather(String city, WeatherRepository weatherRepository) {
        this.city = city;
        this.weatherRepository = weatherRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.weatherRepository.currentWeather(this.city);
    }
}
