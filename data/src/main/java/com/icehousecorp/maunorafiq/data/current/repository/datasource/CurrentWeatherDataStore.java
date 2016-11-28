package com.icehousecorp.maunorafiq.data.current.repository.datasource;

import com.icehousecorp.maunorafiq.data.current.entity.response.CurrentWeatherResponse;

import rx.Observable;

/**
 * Created by Raffi on 11/25/2016.
 */

public interface CurrentWeatherDataStore {

    Observable<CurrentWeatherResponse> currentWeatherEntity(final String city);
}
