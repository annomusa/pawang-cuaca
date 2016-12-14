package com.example.maunorafiq.pawangcuaca.presentation.mapper

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity
import com.example.maunorafiq.pawangcuaca.presentation.model.CityModel
import com.icehousecorp.maunorafiq.domain.city.City

import java.util.ArrayList
import java.util.Collections

import javax.inject.Inject

/**
 * Created by maunorafiq on 12/5/16.
 */
@PerActivity
class CityModelDataMapper
@Inject
constructor() {

    fun transform(city: City): CityModel {
        val cityModel = CityModel(city.cityName ?: "Amsterdam")
        cityModel.ordinal = city.ordinal

        return cityModel
    }

    fun transform(cities: List<City>): List<CityModel> {
        val result: List<CityModel>
        if (cities.isEmpty()) {
            result = emptyList<CityModel>()
        } else {
            result = cities.map { transform(it) }
        }

        return result
    }
}
