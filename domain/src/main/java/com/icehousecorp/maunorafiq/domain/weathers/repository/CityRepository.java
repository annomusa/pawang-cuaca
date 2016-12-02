package com.icehousecorp.maunorafiq.domain.weathers.repository;

import com.icehousecorp.maunorafiq.domain.weathers.Weather;

import java.util.List;

import rx.Observable;

/**
 * Created by maunorafiq on 12/2/16.
 */

public interface CityRepository {

    Observable<Weather> putCity(String city);
}
