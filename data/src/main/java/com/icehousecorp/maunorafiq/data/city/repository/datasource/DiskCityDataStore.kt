package com.icehousecorp.maunorafiq.data.city.repository.datasource

import android.util.Log

import com.icehousecorp.maunorafiq.data.city.disk.RealmService
import com.icehousecorp.maunorafiq.data.city.entity.CityEntity

/**
 * Created by maunorafiq on 12/1/16.
 */

class DiskCityDataStore(private val realmService: RealmService) : CityDataStore {

    private val TAG = this.javaClass.simpleName

    override val listCityEntity: List<CityEntity>
        get() = this.realmService.get()

    override fun putCityEntity(city: String): Boolean {
        return this.realmService.put(city)
    }
}
