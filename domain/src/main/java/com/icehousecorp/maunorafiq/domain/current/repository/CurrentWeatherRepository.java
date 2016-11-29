package com.icehousecorp.maunorafiq.domain.current.repository;

import com.icehousecorp.maunorafiq.domain.current.CurrentWeather;

import rx.Observable;

/**
 * Created by maunorafiq on 11/28/16.
 */

public interface CurrentWeatherRepository {
    Observable<CurrentWeather> currentWeather(String city);
}
