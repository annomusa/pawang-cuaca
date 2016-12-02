package com.icehousecorp.maunorafiq.domain.weathers.repository;

import com.icehousecorp.maunorafiq.domain.weathers.Weather;

import rx.Observable;

/**
 * Created by maunorafiq on 12/2/16.
 */

public interface WeatherRepository {
    Observable<Weather> currentWeather(String city);

}
