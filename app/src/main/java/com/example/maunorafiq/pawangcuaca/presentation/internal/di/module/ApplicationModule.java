package com.example.maunorafiq.pawangcuaca.presentation.internal.di.module;

import android.content.Context;

import com.example.maunorafiq.pawangcuaca.presentation.AndroidApplication;
import com.icehousecorp.maunorafiq.data.weather.repository.WeatherDataRepository;
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
    WeatherRepository provideCurrentWeatherRepository (WeatherDataRepository weatherDataRepository) {
        return weatherDataRepository;
    }
}
