package com.example.maunorafiq.pawangcuaca.presentation.mapper

import android.util.Log

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity
import com.example.maunorafiq.pawangcuaca.presentation.model.ForecastModel
import com.icehousecorp.maunorafiq.domain.forecast.Forecast

import javax.inject.Inject

/**
 * Created by maunorafiq on 11/29/16.
 */

@PerActivity
class ForecastModelDataMapper
@Inject
constructor(private val weatherModelDataMapper: WeatherModelDataMapper) {

    private val TAG = this.javaClass.simpleName

    fun transform(forecast: Forecast): ForecastModel {
        val forecastModel = ForecastModel(forecast.cityName)
        forecastModel.forecastList = weatherModelDataMapper.transform(forecast.forecastList)
        return forecastModel
    }
}
