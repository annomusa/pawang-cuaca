package com.icehousecorp.maunorafiq.domain.weathers.interactor;

import com.icehousecorp.maunorafiq.domain.UseCase;
import com.icehousecorp.maunorafiq.domain.weathers.repository.WeathersRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by maunorafiq on 12/1/16.
 */

public class GetWeathers extends UseCase {

    private final WeathersRepository weathersRepository;

    @Inject
    public GetWeathers(WeathersRepository weathersRepository) {
        this.weathersRepository = weathersRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.weathersRepository.listWeather();
    }
}
