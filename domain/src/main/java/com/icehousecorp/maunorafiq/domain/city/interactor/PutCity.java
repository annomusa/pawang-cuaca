package com.icehousecorp.maunorafiq.domain.city.interactor;

import com.icehousecorp.maunorafiq.domain.UseCase;
import com.icehousecorp.maunorafiq.domain.city.repository.CityRepository;
import com.icehousecorp.maunorafiq.domain.weathers.repository.WeatherRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by maunorafiq on 12/2/16.
 */

public class PutCity extends UseCase {

    private String city = "Berlin";
    private final CityRepository cityRepository;
    private final WeatherRepository weatherRepository;

    @Inject
    public PutCity(CityRepository cityRepository,
                   WeatherRepository weatherRepository) {
        this.cityRepository = cityRepository;
        this.weatherRepository = weatherRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        if (this.cityRepository.putCity(city)) {
            return weatherRepository.currentWeather(city);
        } else {
            return Observable.just(null);
        }
    }

    public void setCity(String city) {
        this.city = city;
    }
}
