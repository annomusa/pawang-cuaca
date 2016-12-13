package com.icehousecorp.maunorafiq.data.city.repository.datasource

import com.icehousecorp.maunorafiq.data.city.entity.CityEntity

import rx.Observable

/**
 * Created by maunorafiq on 12/1/16.
 */

interface CityDataStore {

    val listCityEntity: List<CityEntity>

    fun putCityEntity(city: String): Boolean
}
