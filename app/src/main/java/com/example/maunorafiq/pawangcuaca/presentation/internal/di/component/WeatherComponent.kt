package com.example.maunorafiq.pawangcuaca.presentation.internal.di.component

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.ActivityModule
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.WeatherModule
import com.example.maunorafiq.pawangcuaca.presentation.citylist.CityListFragment
import com.example.maunorafiq.pawangcuaca.presentation.forecast.ForecastFragment

import dagger.Component

/**
 * Created by maunorafiq on 11/29/16.
 */

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(WeatherModule::class, ActivityModule::class))
interface WeatherComponent : ActivityComponent {
    fun inject(cityListFragment: CityListFragment)
    fun inject(forecastFragment: ForecastFragment)
}
