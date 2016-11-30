package com.example.maunorafiq.pawangcuaca.presentation.internal.di.component;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.ActivityModule;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.WeatherModule;
import com.example.maunorafiq.pawangcuaca.presentation.view.fragment.ForecastFragment;
import com.example.maunorafiq.pawangcuaca.presentation.view.fragment.WeatherFragment;

import dagger.Component;

/**
 * Created by maunorafiq on 11/29/16.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {WeatherModule.class, ActivityModule.class})
public interface WeatherComponent extends ActivityComponent {
    void inject(WeatherFragment weatherFragment);
    void inject(ForecastFragment forecastFragment);
}