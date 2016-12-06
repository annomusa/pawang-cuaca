package com.icehousecorp.maunorafiq.data.city.repository.datasource;

import com.icehousecorp.maunorafiq.data.city.entity.CityEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by maunorafiq on 12/1/16.
 */

public interface CityDataStore {

    List<CityEntity> getListCityEntity();

    boolean putCityEntity(String city);
}
