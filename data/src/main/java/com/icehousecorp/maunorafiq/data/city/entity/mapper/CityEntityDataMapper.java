package com.icehousecorp.maunorafiq.data.city.entity.mapper;

import com.icehousecorp.maunorafiq.data.city.entity.CityEntity;
import com.icehousecorp.maunorafiq.domain.city.City;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by maunorafiq on 12/5/16.
 */
@Singleton
public class CityEntityDataMapper {

    @Inject
    public CityEntityDataMapper() {
    }

    public City transform(CityEntity cityEntity) {
        City city = null;
        if (cityEntity != null) {
            city = new City(cityEntity.getCityName());
            city.setOrdinal(cityEntity.getOrdinal());
        }
        return city;
    }
}
