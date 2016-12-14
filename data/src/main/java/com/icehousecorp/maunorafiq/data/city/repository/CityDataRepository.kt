package com.icehousecorp.maunorafiq.data.city.repository

import com.icehousecorp.maunorafiq.data.city.entity.CityEntity
import com.icehousecorp.maunorafiq.data.city.entity.mapper.CityEntityDataMapper
import com.icehousecorp.maunorafiq.data.city.repository.datasource.CityDataStore
import com.icehousecorp.maunorafiq.data.city.repository.datasource.CityDataStoreFactory
import com.icehousecorp.maunorafiq.domain.city.City
import com.icehousecorp.maunorafiq.domain.city.repository.CityRepository

import java.util.ArrayList

import javax.inject.Inject
import javax.inject.Singleton

import rx.Observable

/**
 * Created by maunorafiq on 12/2/16.
 */
@Singleton
class CityDataRepository
@Inject
constructor(private val cityDataStoreFactory: CityDataStoreFactory,
            private val cityEntityDataMapper: CityEntityDataMapper) : CityRepository {

    private val TAG = this.javaClass.simpleName

    override fun putCity(city: String): Boolean {
        val cityDataStore = this.cityDataStoreFactory.create()

        return cityDataStore.putCityEntity(city)
    }

    override fun getCity(): Observable<List<City>> {
        val cityDataStore = this.cityDataStoreFactory.create()

        val listCityEntity = cityDataStore.listCityEntity

        val result = listCityEntity.map { cityEntityDataMapper.transform(it) }

        return Observable.just(result)
    }
}
