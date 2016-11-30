package com.icehousecorp.maunorafiq.domain.forecast.repository;

import com.icehousecorp.maunorafiq.domain.forecast.Forecast;

import rx.Observable;

/**
 * Created by maunorafiq on 11/29/16.
 */

public interface ForecastRepository {
    Observable<Forecast> forecastWeather(String city);
}
