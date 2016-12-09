package com.example.maunorafiq.pawangcuaca.presentation.forecast;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.maunorafiq.pawangcuaca.R;
import com.example.maunorafiq.pawangcuaca.presentation.base.BaseActivity;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.HasComponent;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.DaggerWeatherComponent;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.WeatherComponent;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.WeatherModule;

/**
 * Created by maunorafiq on 11/29/16.
 */

public class ForecastActivity extends BaseActivity implements HasComponent<WeatherComponent> {

    private final String TAG = this.getClass().getSimpleName();

    private static final String INTENT_EXTRA_PARAM_CITY = "icehousecorp.INTENT_PARAM_USER_ID";

    public static Intent getCallingIntent (Context context, String city) {
        Intent intent = new Intent(context, ForecastActivity.class);
        intent.putExtra(INTENT_EXTRA_PARAM_CITY, city);
        return intent;
    }

    private WeatherComponent weatherComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        this.initializeActivity();
        this.initializeInjector();
    }

    private void initializeActivity() {
        String city = getIntent().getStringExtra(INTENT_EXTRA_PARAM_CITY);
        Bundle bundle = new Bundle();
        Fragment fragment = new ForecastFragment();
        bundle.putString("city", city);
        fragment.setArguments(bundle);
        addFragment(R.id.fragmentContainer, fragment);
    }

    private void initializeInjector() {
        this.weatherComponent = DaggerWeatherComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .weatherModule(new WeatherModule())
                .build();
    }

    @Override
    public WeatherComponent getComponent() {
        return this.weatherComponent;
    }
}
