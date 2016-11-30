package com.example.maunorafiq.pawangcuaca.presentation.internal.di.component;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.ActivityModule;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.WeatherModule;

import dagger.Component;

/**
 * Created by maunorafiq on 11/29/16.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {WeatherModule.class, ActivityModule.class})
public interface CurrentWeatherComponent extends ActivityComponent {
//    void inject();
}
