package com.icehousecorp.maunorafiq.domain.forecast.interactor;

import com.icehousecorp.maunorafiq.domain.UseCase;
import com.icehousecorp.maunorafiq.domain.forecast.repository.ForecastRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by maunorafiq on 11/29/16.
 */

public class GetForecast extends UseCase {

    private String city;
    private final ForecastRepository forecastRepository;

    @Inject
    public GetForecast(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.forecastRepository.forecastWeather(this.city);
    }

    public void setCity(String city) {
        this.city = city;
    }
}
