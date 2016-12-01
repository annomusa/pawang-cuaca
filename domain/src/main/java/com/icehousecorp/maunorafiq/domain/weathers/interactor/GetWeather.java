package com.icehousecorp.maunorafiq.domain.weathers.interactor;

import com.icehousecorp.maunorafiq.domain.UseCase;
import com.icehousecorp.maunorafiq.domain.weathers.repository.WeathersRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by maunorafiq on 11/28/16.
 */

public class GetWeather extends UseCase {

    private final String city;
    private final WeathersRepository weathersRepository;

    @Inject
    public GetWeather(String city, WeathersRepository weathersRepository) {
        this.city = city;
        this.weathersRepository = weathersRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.weathersRepository.currentWeather(this.city);
    }
}