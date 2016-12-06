package com.icehousecorp.maunorafiq.domain.city.repository;

import com.icehousecorp.maunorafiq.domain.city.City;
import com.icehousecorp.maunorafiq.domain.weathers.Weather;

import java.util.List;

import rx.Observable;

/**
 * Created by maunorafiq on 12/2/16.
 */

public interface CityRepository {

    boolean putCity(String city);

    Observable<List<City>> getCity();
}
