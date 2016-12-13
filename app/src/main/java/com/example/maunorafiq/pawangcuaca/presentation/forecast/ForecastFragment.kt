package com.example.maunorafiq.pawangcuaca.presentation.forecast

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import com.example.maunorafiq.pawangcuaca.R
import com.example.maunorafiq.pawangcuaca.presentation.base.BaseFragment
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.WeatherComponent
import com.example.maunorafiq.pawangcuaca.presentation.model.ForecastModel
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel
import com.example.maunorafiq.pawangcuaca.presentation.forecast.adapter.ForecastAdapter
import com.example.maunorafiq.pawangcuaca.presentation.forecast.adapter.ForecastLayoutManager
import com.squareup.picasso.Picasso

import javax.inject.Inject

import butterknife.ButterKnife

/**
 * Created by maunorafiq on 11/29/16.
 */

class ForecastFragment : BaseFragment(), ForecastView {

    private val TAG = this.javaClass.simpleName

    @Inject internal lateinit var forecastPresenter: ForecastPresenter
    @Inject internal lateinit var forecastAdapter: ForecastAdapter

    internal lateinit var rlForecast: RelativeLayout
    internal lateinit var tvPressureVal: TextView
    internal lateinit var tvHumidityVal: TextView
    internal lateinit var tvTemperatureVal: TextView
    internal lateinit var ivWeatherIcon: ImageView
    internal lateinit var tvWeatherDescription: TextView
    internal lateinit var tvLocation: TextView
    internal lateinit var rvForecastList: RecyclerView
    internal lateinit var rlProgress: RelativeLayout
    internal lateinit var rlRetry: RelativeLayout
    internal lateinit var btnRetry: Button

    init {
        retainInstance = true
    }

    private fun findViewById(fragmentView: View) {
        rlForecast = fragmentView.findViewById(R.id.rl_forecast) as RelativeLayout
        tvPressureVal = fragmentView.findViewById(R.id.tvPressureVal) as TextView
        tvHumidityVal = fragmentView.findViewById(R.id.tvHumidityVal) as TextView
        tvTemperatureVal = fragmentView.findViewById(R.id.tvTemperatureVal) as TextView
        ivWeatherIcon = fragmentView.findViewById(R.id.ivIcon) as ImageView
        tvWeatherDescription = fragmentView.findViewById(R.id.tvDescription) as TextView
        tvLocation = fragmentView.findViewById(R.id.tvLocation) as TextView
        rvForecastList = fragmentView.findViewById(R.id.rvWeatherForecast) as RecyclerView
        rlProgress = fragmentView.findViewById(R.id.rl_progress) as RelativeLayout
        rlRetry = fragmentView.findViewById(R.id.rl_retry) as RelativeLayout
        btnRetry = fragmentView.findViewById(R.id.bt_retry) as Button
        btnRetry.setOnClickListener { v -> loadForecast() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent(WeatherComponent::class.java).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_forecast, container, false)
        ButterKnife.bind(this, fragmentView)
        findViewById(fragmentView)
        setUpRecyclerView()
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forecastPresenter.setView(this)
        loadForecast()
    }

    override fun onResume() {
        super.onResume()
        forecastPresenter.resume()
    }

    override fun onPause() {
        super.onPause()
        forecastPresenter.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rvForecastList.adapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        forecastPresenter.destroy()
    }

    override fun renderForecastWeather(forecastModel: ForecastModel) {
        rlForecast.visibility = View.VISIBLE
        forecastAdapter.setWeatherModelList(forecastModel.forecastList!!)
    }

    override fun renderForecastWeather(weatherModel: WeatherModel) {
        rlForecast.visibility = View.VISIBLE
        tvPressureVal.text = weatherModel.pressure
        tvHumidityVal.text = weatherModel.humidity
        tvTemperatureVal.text = weatherModel.temperature
        Picasso.with(context()).load(weatherModel.weatherIcon).into(ivWeatherIcon)
        tvWeatherDescription.text = weatherModel.weatherDescription
        tvLocation.text = weatherModel.cityName
    }

    override fun showLoading() {
        rlProgress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rlProgress.visibility = View.GONE
    }

    override fun showRetry() {
        rlRetry.visibility = View.VISIBLE
    }

    override fun hideRetry() {
        rlProgress.visibility = View.GONE
    }

    override fun showError(message: String?) {
    }

    override fun context(): Context {
        return activity.applicationContext
    }

    private fun setUpRecyclerView() {
        rvForecastList.layoutManager = ForecastLayoutManager(context())
        rvForecastList.adapter = forecastAdapter
    }

    private fun loadForecast() {
        val city = arguments.getString("city", "Amsterdam")
        forecastPresenter.initialize(city)
    }
}
