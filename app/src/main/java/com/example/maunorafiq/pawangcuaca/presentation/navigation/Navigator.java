package com.example.maunorafiq.pawangcuaca.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.example.maunorafiq.pawangcuaca.presentation.view.activity.ForecastActivity;
import com.example.maunorafiq.pawangcuaca.presentation.view.activity.WeatherActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by maunorafiq on 11/29/16.
 */

@Singleton
public class Navigator {

    @Inject
    public Navigator () { }

    public void navigateToWeather (Context context) {
        if (context != null) {
            Intent intent = WeatherActivity.getCallingIntent(context);
            context.startActivity(intent);
        }
    }

    public void navigateToForecast (Context context, String city) {
        if (context != null) {
            Intent intent = ForecastActivity.getCallingIntent(context, city);
            context.startActivity(intent);
        }
    }
}
