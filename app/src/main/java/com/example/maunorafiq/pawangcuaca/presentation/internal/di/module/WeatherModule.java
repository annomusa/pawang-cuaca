package com.example.maunorafiq.pawangcuaca.presentation.internal.di.module;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.icehousecorp.maunorafiq.domain.weather.interactor.GetWeather;
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
    @Named("weather")
    UseCase provideGetWeatherUseCase (WeatherRepository weatherRepository) {
        return new GetWeather(cityName, weatherRepository);
    }

    @Provides
    @PerActivity
    @Named("forecast")
    UseCase provideGetForecastUseCase (ForecastRepository forecastRepository) {
        return new GetForecast(cityName, forecastRepository);
    }

}
