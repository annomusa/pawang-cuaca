package com.icehousecorp.maunorafiq.domain.weathers.interactor;

import com.icehousecorp.maunorafiq.domain.UseCase;
import com.icehousecorp.maunorafiq.domain.weathers.repository.CityRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by maunorafiq on 12/2/16.
 */

public class PutCity extends UseCase {

    private String city = "Berlin";
    private final CityRepository cityRepository;

    @Inject
    public PutCity(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.cityRepository.putCity(city);
    }

    public void setCity(String city) {
        this.city = city;
    }
}
