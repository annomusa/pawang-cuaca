package com.example.maunorafiq.pawangcuaca.presentation.internal.di.module;

import android.content.Context;

import com.example.maunorafiq.pawangcuaca.presentation.AndroidApplication;
import com.icehousecorp.maunorafiq.data.city.disk.RealmService;
import com.icehousecorp.maunorafiq.data.city.disk.RealmServiceImpl;
import com.icehousecorp.maunorafiq.data.city.repository.CityDataRepository;
import com.icehousecorp.maunorafiq.data.weather.repository.WeatherDataRepository;
import com.icehousecorp.maunorafiq.data.weather.repository.WeathersDataRepository;
import com.icehousecorp.maunorafiq.domain.weathers.repository.CityRepository;
import com.icehousecorp.maunorafiq.domain.weathers.repository.WeatherRepository;
import com.icehousecorp.maunorafiq.domain.weathers.repository.WeathersRepository;

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
    WeathersRepository provideWeathersRepository(WeathersDataRepository weathersDataRepository) {
        return weathersDataRepository;
    }

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(WeatherDataRepository weathersDataRepository) {
        return weathersDataRepository;
    }

    @Provides
    @Singleton
    CityRepository provideCityRepository(CityDataRepository cityDataRepository) {
        return cityDataRepository;
    }

    @Provides
    @Singleton
    RealmService provideRealmService(RealmServiceImpl realmService) {
        return realmService;
    }
}
