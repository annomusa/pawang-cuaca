package com.icehousecorp.maunorafiq.domain.current.repository;

import com.icehousecorp.maunorafiq.domain.current.CurrentWeather;

import rx.Observable;


/**
 * Created by Raffi on 11/28/2016.
 */

public interface CurrentWeatherRepository {
    Observable<CurrentWeather> currentWeather(final String city);
}
