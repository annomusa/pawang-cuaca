package com.icehousecorp.maunorafiq.domain.weather.repository;

import com.icehousecorp.maunorafiq.domain.weather.Weather;

import rx.Observable;

/**
 * Created by maunorafiq on 11/28/16.
 */

public interface WeatherRepository {

    Observable<Weather> currentWeather(String city);
}
