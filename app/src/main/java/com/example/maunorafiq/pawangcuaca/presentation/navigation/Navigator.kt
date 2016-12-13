package com.example.maunorafiq.pawangcuaca.presentation.navigation

import android.content.Context
import android.content.Intent

import com.example.maunorafiq.pawangcuaca.presentation.forecast.ForecastActivity
import com.example.maunorafiq.pawangcuaca.presentation.citylist.CityListActivity

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by maunorafiq on 11/29/16.
 */

@Singleton
class Navigator
@Inject
constructor() {

    fun navigateToCityList(context: Context?) {
        if (context != null) {
            val intent = CityListActivity.getCallingIntent(context)
            context.startActivity(intent)
        }
    }

    fun navigateToForecast(context: Context?, city: String) {
        if (context != null) {
            val intent = ForecastActivity.getCallingIntent(context, city)
            context.startActivity(intent)
        }
    }
}
