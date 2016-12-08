package com.example.maunorafiq.pawangcuaca.presentation.internal.di.module

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity
import com.icehousecorp.maunorafiq.domain.city.interactor.GetCity
import com.icehousecorp.maunorafiq.domain.weather.interactor.GetWeather
import com.icehousecorp.maunorafiq.domain.city.interactor.PutCity
import com.icehousecorp.maunorafiq.domain.city.repository.CityRepository
import com.icehousecorp.maunorafiq.domain.weather.repository.WeatherRepository
import com.icehousecorp.maunorafiq.domain.forecast.interactor.GetForecast
import com.icehousecorp.maunorafiq.domain.forecast.repository.ForecastRepository

import dagger.Module
import dagger.Provides

/**
 * Created by maunorafiq on 11/29/16.
 */

@Module
class WeatherModule {

    @Provides
    @PerActivity
    internal fun provideGetWeatherUseCase(weatherRepository: WeatherRepository): GetWeather {
        return GetWeather(weatherRepository)
    }

    @Provides
    @PerActivity
    internal fun provideGetForecastUseCase(forecastRepository: ForecastRepository): GetForecast {
        return GetForecast(forecastRepository)
    }

    @Provides
    @PerActivity
    internal fun providePutCityUseCase(cityRepository: CityRepository, weatherRepository: WeatherRepository): PutCity {
        return PutCity(cityRepository, weatherRepository)
    }

    @Provides
    @PerActivity
    internal fun provideGetCityUseCase(cityRepository: CityRepository): GetCity {
        return GetCity(cityRepository)
    }
}
