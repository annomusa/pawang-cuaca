package com.example.maunorafiq.pawangcuaca.presentation.internal.di.module;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.icehousecorp.maunorafiq.domain.weather.interactor.GetCurrentWeather;
import com.icehousecorp.maunorafiq.domain.UseCase;
import com.icehousecorp.maunorafiq.domain.weather.repository.WeatherRepository;
import com.icehousecorp.maunorafiq.domain.forecast.interactor.GetForecast;
import com.icehousecorp.maunorafiq.domain.forecast.repository.ForecastRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maunorafiq on 11/29/16.
 */

@Module
public class WeatherModule {

    private String cityName = "Amsterdam";

    public WeatherModule() {
    }

    public WeatherModule(String cityName) {
        this.cityName = cityName;
    }

    @Provides
    @PerActivity
    @Named("weatherList")
    UseCase provideGetCurrentWeatherUseCase (WeatherRepository weatherRepository) {
        return new GetCurrentWeather(cityName, weatherRepository);
    }

    @Provides
    @PerActivity
    @Named("weatherDetail")
    UseCase provideGetForecastWeatherUseCase (ForecastRepository forecastRepository) {
        return new GetForecast(cityName, forecastRepository);
    }

}
