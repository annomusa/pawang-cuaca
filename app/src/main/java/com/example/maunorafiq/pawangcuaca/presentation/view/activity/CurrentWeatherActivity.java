package com.example.maunorafiq.pawangcuaca.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.HasComponent;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.CurrentWeatherComponent;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.DaggerCurrentWeatherComponent;

/**
 * Created by maunorafiq on 11/29/16.
 */

public class CurrentWeatherActivity extends BaseActivity implements HasComponent<CurrentWeatherComponent> {

    public static Intent getCallingIntent (Context context) {
        return new Intent(context, CurrentWeatherActivity.class);
    }

    private CurrentWeatherComponent currentWeatherComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.initializeInjector();

    }

    private void initializeInjector () {
        this.currentWeatherComponent = DaggerCurrentWeatherComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public CurrentWeatherComponent getComponent() {
        return currentWeatherComponent;
    }
}
