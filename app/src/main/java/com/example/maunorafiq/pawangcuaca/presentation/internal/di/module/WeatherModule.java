package com.example.maunorafiq.pawangcuaca.presentation.internal.di.module;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.icehousecorp.maunorafiq.domain.weathers.interactor.GetWeather;
import com.icehousecorp.maunorafiq.domain.UseCase;
import com.icehousecorp.maunorafiq.domain.weathers.interactor.GetWeathers;
import com.icehousecorp.maunorafiq.domain.weathers.interactor.PutCity;
import com.icehousecorp.maunorafiq.domain.weathers.repository.CityRepository;
import com.icehousecorp.maunorafiq.domain.weathers.repository.WeatherRepository;
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

    public WeatherModule() {
    }

    @Provides
    @PerActivity
    GetWeather provideGetWeatherUseCase (WeatherRepository weatherRepository) {
        return new GetWeather(weatherRepository);
    }

    @Provides
    @PerActivity
    GetForecast provideGetForecastUseCase (ForecastRepository forecastRepository) {
        return new GetForecast(forecastRepository);
    }

    @Provides
    @PerActivity
    GetWeathers provideGetWeathersUseCase (WeathersRepository weathersRepository) {
        return new GetWeathers(weathersRepository);
    }

    @Provides
    @PerActivity
    PutCity providePutWeatherUseCase (CityRepository cityRepository) {
        return new PutCity(cityRepository);
    }
}
