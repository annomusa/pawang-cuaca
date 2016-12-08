package com.example.maunorafiq.pawangcuaca.presentation.internal.di.module

import android.content.Context

import com.example.maunorafiq.pawangcuaca.presentation.AndroidApplication
import com.icehousecorp.maunorafiq.data.city.disk.RealmService
import com.icehousecorp.maunorafiq.data.city.disk.RealmServiceImpl
import com.icehousecorp.maunorafiq.data.city.repository.CityDataRepository
import com.icehousecorp.maunorafiq.data.forecast.repository.ForecastDataRepository
import com.icehousecorp.maunorafiq.data.weather.repository.WeatherDataRepository
import com.icehousecorp.maunorafiq.domain.city.repository.CityRepository
import com.icehousecorp.maunorafiq.domain.forecast.repository.ForecastRepository
import com.icehousecorp.maunorafiq.domain.weather.repository.WeatherRepository

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by maunorafiq on 11/29/16.
 */

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    internal fun provideApplicationContext(): Context {
        return this.application
    }

    @Provides
    @Singleton
    internal fun provideWeatherRepository(weatherDataRepository: WeatherDataRepository): WeatherRepository {
        return weatherDataRepository
    }

    @Provides
    @Singleton
    internal fun provideForecastRepository(forecastDataRepository: ForecastDataRepository): ForecastRepository {
        return forecastDataRepository
    }

    @Provides
    @Singleton
    internal fun provideCityRepository(cityDataRepository: CityDataRepository): CityRepository {
        return cityDataRepository
    }

    @Provides
    @Singleton
    internal fun provideRealmService(realmService: RealmServiceImpl): RealmService {
        return realmService
    }
}
