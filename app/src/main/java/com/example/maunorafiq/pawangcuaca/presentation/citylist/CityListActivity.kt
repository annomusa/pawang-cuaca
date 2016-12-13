package com.example.maunorafiq.pawangcuaca.presentation.citylist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

import com.example.maunorafiq.pawangcuaca.R
import com.example.maunorafiq.pawangcuaca.presentation.base.BaseActivity
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.HasComponent
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.DaggerWeatherComponent
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.WeatherComponent
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.WeatherModule
import com.example.maunorafiq.pawangcuaca.presentation.model.CityModel

/**
 * Created by maunorafiq on 11/29/16.
 */

class CityListActivity : BaseActivity(), HasComponent<WeatherComponent>, CityListFragment.CityListListener {

    private val TAG = this.javaClass.simpleName

    override lateinit var component: WeatherComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)

        Log.d(TAG, "onCreate: ")

        this.initializeInjector()
        addFragment(R.id.fragmentContainer, CityListFragment())
    }

    private fun initializeInjector() {
        this.component = DaggerWeatherComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(activityModule)
                .weatherModule(WeatherModule())
                .build()
    }

    override fun onCityClicked(cityModel: CityModel) {
        navigator.navigateToForecast(this, cityModel.cityName)
    }

    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, CityListActivity::class.java)
        }
    }
}
