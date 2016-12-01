package com.icehousecorp.maunorafiq.domain.weathers.repository;

import com.icehousecorp.maunorafiq.domain.weathers.Weather;

import java.util.List;

import rx.Observable;

/**
 * Created by maunorafiq on 11/28/16.
 */

public interface WeathersRepository {
    Observable<Weather> currentWeather(String city);

    Observable<List<Weather>> listWeather();
}
