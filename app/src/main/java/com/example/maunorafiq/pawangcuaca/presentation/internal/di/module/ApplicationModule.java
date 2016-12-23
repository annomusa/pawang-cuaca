package com.example.maunorafiq.pawangcuaca.presentation.internal.di.module;

import android.content.Context;

import com.example.maunorafiq.pawangcuaca.presentation.AndroidApplication;
import com.icehousecorp.maunorafiq.data.city.disk.RealmService;
import com.icehousecorp.maunorafiq.data.city.disk.RealmServiceImpl;
import com.icehousecorp.maunorafiq.data.city.repository.CityDataRepository;
import com.icehousecorp.maunorafiq.data.forecast.repository.ForecastDataRepository;
import com.icehousecorp.maunorafiq.data.gpslocation.GpsLocationDataRepository;
import com.icehousecorp.maunorafiq.data.weather.repository.WeatherDataRepository;
import com.icehousecorp.maunorafiq.domain.city.repository.CityRepository;
import com.icehousecorp.maunorafiq.domain.forecast.repository.ForecastRepository;
import com.icehousecorp.maunorafiq.domain.gpslocation.repository.GpsLocationRepository;
import com.icehousecorp.maunorafiq.domain.weather.repository.WeatherRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maunorafiq on 11/29/16.
 */

@Module
public class ApplicationModule {

    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext () {
        return this.application;
    }

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(WeatherDataRepository weatherDataRepository) {
        return weatherDataRepository;
    }

    @Provides
    @Singleton
    ForecastRepository provideForecastRepository(ForecastDataRepository forecastDataRepository) {
        return forecastDataRepository;
    }

    @Provides
    @Singleton
    CityRepository provideCityRepository(CityDataRepository cityDataRepository) {
        return cityDataRepository;
    }

    @Provides
    @Singleton
    GpsLocationRepository provideGpsLocationRepository(GpsLocationDataRepository gpsLocationDataRepository) {
        return gpsLocationDataRepository;
    }

    @Provides
    @Singleton
    RealmService provideRealmService(RealmServiceImpl realmService) {
        return realmService;
    }
}
