package com.example.maunorafiq.pawangcuaca.presentation.internal.di.module;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.icehousecorp.maunorafiq.domain.city.interactor.GetCity;
import com.icehousecorp.maunorafiq.domain.gpslocation.interactor.GetGpsLocation;
import com.icehousecorp.maunorafiq.domain.gpslocation.repository.GpsLocationRepository;
import com.icehousecorp.maunorafiq.domain.weather.interactor.GetWeather;
import com.icehousecorp.maunorafiq.domain.city.interactor.PutCity;
import com.icehousecorp.maunorafiq.domain.city.repository.CityRepository;
import com.icehousecorp.maunorafiq.domain.weather.repository.WeatherRepository;
import com.icehousecorp.maunorafiq.domain.forecast.interactor.GetForecast;
import com.icehousecorp.maunorafiq.domain.forecast.repository.ForecastRepository;

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
    PutCity providePutCityUseCase(CityRepository cityRepository, WeatherRepository weatherRepository) {
        return new PutCity(cityRepository, weatherRepository);
    }

    @Provides
    @PerActivity
    GetCity provideGetCityUseCase(CityRepository cityRepository) {
        return new GetCity(cityRepository);
    }

    @Provides
    @PerActivity
    GetGpsLocation provideGetGpsLocationUseCase(GpsLocationRepository gpsLocationRepository) {
        return new GetGpsLocation(gpsLocationRepository);
    }
}
