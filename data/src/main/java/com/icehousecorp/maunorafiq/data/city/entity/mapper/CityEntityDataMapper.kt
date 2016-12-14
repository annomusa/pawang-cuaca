package com.icehousecorp.maunorafiq.data.city.entity.mapper

import com.icehousecorp.maunorafiq.data.city.entity.CityEntity
import com.icehousecorp.maunorafiq.domain.city.City

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by maunorafiq on 12/5/16.
 */
@Singleton
class CityEntityDataMapper
@Inject
constructor() {

    fun transform(cityEntity: CityEntity): City {
        val city: City

        city = City(cityEntity.cityName)
        city.ordinal = cityEntity.ordinal

        return city
    }
}
