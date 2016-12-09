package com.example.maunorafiq.pawangcuaca.presentation.view

import com.example.maunorafiq.pawangcuaca.presentation.model.CityModel
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel

/**
 * Created by maunorafiq on 11/29/16.
 */

interface CityListView : LoadDataView {

    fun renderCityList(cityModelList: List<CityModel>)

    fun viewCity(cityModel: CityModel)

    fun updateCity(weatherModel: WeatherModel)

    fun addNewCity(cityModel: CityModel)
}
