package com.example.maunorafiq.pawangcuaca.presentation.forecast

import com.example.maunorafiq.pawangcuaca.presentation.base.LoadDataView
import com.example.maunorafiq.pawangcuaca.presentation.model.CityModel
import com.example.maunorafiq.pawangcuaca.presentation.model.ForecastModel
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel

/**
 * Created by maunorafiq on 11/29/16.
 */

interface ForecastView : LoadDataView {

    fun renderForecastWeather(forecastModel: ForecastModel)

    fun renderForecastWeather(weatherModel: WeatherModel)
}
