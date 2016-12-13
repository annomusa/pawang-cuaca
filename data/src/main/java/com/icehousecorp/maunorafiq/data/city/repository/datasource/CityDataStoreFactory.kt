package com.icehousecorp.maunorafiq.data.city.repository.datasource

import com.icehousecorp.maunorafiq.data.city.disk.RealmService

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by maunorafiq on 12/1/16.
 */
@Singleton
class CityDataStoreFactory
@Inject
constructor(private val realmService: RealmService) {

    fun create(): CityDataStore {
        val cityDataStore: CityDataStore

        cityDataStore = DiskCityDataStore(this.realmService)

        return cityDataStore
    }
}
