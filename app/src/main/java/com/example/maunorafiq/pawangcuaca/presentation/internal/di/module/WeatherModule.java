package com.example.maunorafiq.pawangcuaca.presentation.internal.di.module;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.icehousecorp.maunorafiq.domain.weathers.interactor.GetWeather;
import com.icehousecorp.maunorafiq.domain.UseCase;
import com.icehousecorp.maunorafiq.domain.weathers.interactor.GetWeathers;
import com.icehousecorp.maunorafiq.domain.weathers.repository.WeathersRepository;
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
    UseCase provideGetWeatherUseCase (WeathersRepository weathersRepository) {
        return new GetWeather(cityName, weathersRepository);
    }

    @Provides
    @PerActivity
    @Named("forecast")
    UseCase provideGetForecastUseCase (ForecastRepository forecastRepository) {
        return new GetForecast(cityName, forecastRepository);
    }

    @Provides
    @PerActivity
    @Named("weathers")
    UseCase provideGetWeathersUseCase (WeathersRepository weathersRepository) {
        return new GetWeathers(weathersRepository);
    }

}
