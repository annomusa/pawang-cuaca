package com.example.maunorafiq.pawangcuaca.presentation.citylist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.maunorafiq.pawangcuaca.R;
import com.example.maunorafiq.pawangcuaca.presentation.base.BaseActivity;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.HasComponent;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.DaggerWeatherComponent;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.WeatherComponent;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.WeatherModule;
import com.example.maunorafiq.pawangcuaca.presentation.model.CityModel;

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
    private CityListFragment cityListFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        this.initializeInjector();

        cityListFragment = new CityListFragment();
        addFragment(R.id.fragmentContainer, cityListFragment);
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
        if (weatherComponent != null) return weatherComponent;
        else {
            initializeInjector();
            return weatherComponent;
        }
    }

    @Override
    public void onCityClicked(CityModel cityModel) {
        navigator.navigateToForecast(this, cityModel.getCityName());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ");
        cityListFragment.onActivityResult(requestCode, resultCode, data);
    }
}
