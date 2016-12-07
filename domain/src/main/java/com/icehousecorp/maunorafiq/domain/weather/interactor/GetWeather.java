package com.icehousecorp.maunorafiq.domain.weather.interactor;

import com.icehousecorp.maunorafiq.domain.UseCase;
import com.icehousecorp.maunorafiq.domain.weather.repository.WeatherRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by maunorafiq on 11/28/16.
 */

public class GetWeather extends UseCase {

    private String city = "Amsterdam";
    private final WeatherRepository weatherRepository;

    @Inject
    public GetWeather(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.weatherRepository.currentWeather(this.city);
    }

    public void setCity(String city) {
        this.city = city;
    }
}