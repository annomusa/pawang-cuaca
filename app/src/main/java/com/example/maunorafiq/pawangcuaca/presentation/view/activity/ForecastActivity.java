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
import com.example.maunorafiq.pawangcuaca.presentation.view.fragment.ForecastFragment;

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

    private String city;
    private WeatherComponent weatherComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        Log.d(TAG, "onCreate: ");

        this.initializeActivity();
        this.initializeInjector();
    }

    private void initializeActivity() {
        this.city = getIntent().getStringExtra(INTENT_EXTRA_PARAM_CITY);
        addFragment(R.id.fragmentContainer, new ForecastFragment());
    }

    private void initializeInjector() {
        this.weatherComponent = DaggerWeatherComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .weatherModule(new WeatherModule(this.city))
                .build();
    }

    @Override
    public WeatherComponent getComponent() {
        return this.weatherComponent;
    }
}
