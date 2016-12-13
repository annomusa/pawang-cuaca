package com.example.maunorafiq.pawangcuaca.presentation.forecast

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.example.maunorafiq.pawangcuaca.R
import com.example.maunorafiq.pawangcuaca.presentation.base.BaseActivity
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.HasComponent
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.DaggerWeatherComponent
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.WeatherComponent
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.WeatherModule

/**
 * Created by maunorafiq on 11/29/16.
 */

class ForecastActivity : BaseActivity(), HasComponent<WeatherComponent> {

    private val TAG = this.javaClass.simpleName

    override lateinit var component: WeatherComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        this.initializeActivity()
        this.initializeInjector()
    }

    private fun initializeActivity() {
        val city = intent.getStringExtra(INTENT_EXTRA_PARAM_CITY)
        val bundle = Bundle()
        val fragment = ForecastFragment()
        bundle.putString("city", city)
        fragment.arguments = bundle
        addFragment(R.id.fragmentContainer, fragment)
    }

    private fun initializeInjector() {
        this.component = DaggerWeatherComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(activityModule)
                .weatherModule(WeatherModule())
                .build()
    }

    companion object {

        private val INTENT_EXTRA_PARAM_CITY = "icehousecorp.INTENT_PARAM_USER_ID"

        fun getCallingIntent(context: Context, city: String): Intent {
            val intent = Intent(context, ForecastActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_CITY, city)
            return intent
        }
    }
}
