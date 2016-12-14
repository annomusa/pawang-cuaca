package com.icehousecorp.maunorafiq.domain.city.repository

import com.icehousecorp.maunorafiq.domain.city.City

import rx.Observable

/**
 * Created by maunorafiq on 12/2/16.
 */

interface CityRepository {

    fun putCity(city: String): Boolean

    fun getCity(): Observable<List<City>>
}
