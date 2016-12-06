package com.icehousecorp.maunorafiq.domain.city.interactor;

import com.icehousecorp.maunorafiq.domain.UseCase;
import com.icehousecorp.maunorafiq.domain.city.repository.CityRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by maunorafiq on 12/5/16.
 */

public class GetCity extends UseCase {

    private final CityRepository cityRepository;

    @Inject
    public GetCity(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.cityRepository.getCity();
    }
}
