package com.icehousecorp.maunorafiq.domain.weather.interactor;

import com.icehousecorp.maunorafiq.domain.UseCase;
import com.icehousecorp.maunorafiq.domain.weather.repository.WeatherRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by maunorafiq on 11/28/16.
 */

public class GetWeather extends UseCase {

    private String city;
    private Double lat;
    private Double lon;
    private final WeatherRepository weatherRepository;

    @Inject
    public GetWeather(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        if (this.city != null) return this.weatherRepository.currentWeather(this.city);
        else return this.weatherRepository.currentWeather(lat, lon);
    }

    public void setCity(String city) {
        this.city = city;
        this.lat = null;
        this.lon = null;
    }

    public void setCoordinate(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        this.city = null;
    }
}