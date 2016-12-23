package com.example.maunorafiq.pawangcuaca.presentation.internal.di.component;

import android.content.Context;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.ApplicationModule;
import com.example.maunorafiq.pawangcuaca.presentation.base.BaseActivity;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.NetworkModule;
import com.icehousecorp.maunorafiq.domain.city.repository.CityRepository;
import com.icehousecorp.maunorafiq.domain.forecast.repository.ForecastRepository;
import com.icehousecorp.maunorafiq.domain.weather.repository.WeatherRepository;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by maunorafiq on 11/29/16.
 */

@Singleton
@Component (modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    void inject (BaseActivity baseActivity);

    Context context();
    WeatherRepository weatherRepository();
    ForecastRepository forecastRepository();
    CityRepository cityRepository();

    Retrofit retrofit();
}
