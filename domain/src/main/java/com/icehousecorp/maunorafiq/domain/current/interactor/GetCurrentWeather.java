package com.icehousecorp.maunorafiq.domain.current.interactor;

import com.icehousecorp.maunorafiq.domain.current.repository.CurrentWeatherRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by maunorafiq on 11/28/16.
 */

public class GetCurrentWeather extends UseCase {

    private final String city;
    private final CurrentWeatherRepository currentWeatherRepository;

    @Inject
    public GetCurrentWeather(String city, CurrentWeatherRepository currentWeatherRepository) {
        this.city = city;
        this.currentWeatherRepository = currentWeatherRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.currentWeatherRepository.currentWeather(this.city);
    }
}
