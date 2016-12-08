package com.example.maunorafiq.pawangcuaca.presentation.internal.di.component

import android.content.Context

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.ApplicationModule
import com.example.maunorafiq.pawangcuaca.presentation.view.activity.BaseActivity
import com.icehousecorp.maunorafiq.domain.city.repository.CityRepository
import com.icehousecorp.maunorafiq.domain.forecast.repository.ForecastRepository
import com.icehousecorp.maunorafiq.domain.weather.repository.WeatherRepository

import javax.inject.Singleton

import dagger.Component

/**
 * Created by maunorafiq on 11/29/16.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(baseActivity: BaseActivity)

    fun context(): Context
    fun weatherRepository(): WeatherRepository
    fun forecastRepositoy(): ForecastRepository
    fun cityRepository(): CityRepository
}
