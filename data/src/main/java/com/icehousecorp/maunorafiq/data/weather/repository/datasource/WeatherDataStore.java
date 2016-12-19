package com.icehousecorp.maunorafiq.data.weather.repository.datasource;

import com.icehousecorp.maunorafiq.data.weather.entity.response.WeatherResponse;

import rx.Observable;

/**
 * Created by Raffi on 11/25/2016.
 */

public interface WeatherDataStore {

    Observable<WeatherResponse> getWeatherEntity(final String city);
}