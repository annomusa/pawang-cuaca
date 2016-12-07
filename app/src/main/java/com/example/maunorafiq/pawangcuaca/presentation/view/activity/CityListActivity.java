package com.example.maunorafiq.pawangcuaca.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.maunorafiq.pawangcuaca.R;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.HasComponent;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.DaggerWeatherComponent;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.WeatherComponent;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.WeatherModule;
import com.example.maunorafiq.pawangcuaca.presentation.model.CityModel;
import com.example.maunorafiq.pawangcuaca.presentation.view.fragment.CityListFragment;

/**
 * Created by maunorafiq on 11/29/16.
 */

public class CityListActivity extends BaseActivity implements HasComponent<WeatherComponent>,
        CityListFragment.CityListListener {

    private final String TAG = this.getClass().getSimpleName();

    public static Intent getCallingIntent (Context context) {
        return new Intent(context, CityListActivity.class);
    }

    private WeatherComponent weatherComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        Log.d(TAG, "onCreate: ");

        this.initializeInjector();

        addFragment(R.id.fragmentContainer, new CityListFragment());
    }

    private void initializeInjector () {
        this.weatherComponent = DaggerWeatherComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .weatherModule(new WeatherModule())
                .build();
    }

    @Override
    public WeatherComponent getComponent() {
        return weatherComponent;
    }

    @Override
    public void onCityClicked(CityModel cityModel) {
        navigator.navigateToForecast(this, cityModel.getCityName());
    }
}
