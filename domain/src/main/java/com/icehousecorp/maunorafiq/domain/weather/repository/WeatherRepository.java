package com.icehousecorp.maunorafiq.domain.weather.repository;

import com.icehousecorp.maunorafiq.domain.weather.Weather;

import rx.Observable;

/**
 * Created by maunorafiq on 12/2/16.
 */

public interface WeatherRepository {
    Observable<Weather> currentWeather(String city);

    Observable<Weather> currentWeather(Double lat, Double lon);
}
