package com.icehousecorp.maunorafiq.domain.forecast.interactor;

import com.icehousecorp.maunorafiq.domain.UseCase;
import com.icehousecorp.maunorafiq.domain.forecast.repository.ForecastRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by maunorafiq on 11/29/16.
 */

public class GetForecast extends UseCase {

    private final String city;
    private final ForecastRepository forecastRepository;

    @Inject
    public GetForecast(String city, ForecastRepository forecastRepository) {
        this.city = city;
        this.forecastRepository = forecastRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.forecastRepository.forecastWeather(this.city);
    }
}
