package com.icehousecorp.maunorafiq.data.city.disk

import com.icehousecorp.maunorafiq.data.city.entity.CityEntity

/**
 * Created by maunorafiq on 12/2/16.
 */

interface RealmService {

    fun get(): List<CityEntity>

    fun put(cityName: String): Boolean

    fun update(cityName: String, ordinal: Int)
}
