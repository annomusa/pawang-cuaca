package com.example.maunorafiq.pawangcuaca.presentation.forecast

import android.util.Log
import com.example.maunorafiq.pawangcuaca.presentation.base.Presenter

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity
import com.example.maunorafiq.pawangcuaca.presentation.mapper.ForecastModelDataMapper
import com.example.maunorafiq.pawangcuaca.presentation.mapper.WeatherModelDataMapper
import com.example.maunorafiq.pawangcuaca.presentation.model.ForecastModel
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel
import com.example.maunorafiq.pawangcuaca.presentation.forecast.ForecastView
import com.icehousecorp.maunorafiq.domain.forecast.Forecast
import com.icehousecorp.maunorafiq.domain.forecast.interactor.GetForecast
import com.icehousecorp.maunorafiq.domain.weather.Weather
import com.icehousecorp.maunorafiq.domain.weather.interactor.GetWeather

import javax.inject.Inject

import rx.Subscriber

/**
 * Created by maunorafiq on 11/29/16.
 */
@PerActivity
class ForecastPresenter
@Inject
constructor(private val getForecastUseCase: GetForecast,
            private val getWeatherUseCase: GetWeather,
            private val weatherModelDataMapper: WeatherModelDataMapper,
            private val forecastModelDataMapper: ForecastModelDataMapper) : Presenter {

    private val TAG = this.javaClass.simpleName

    private var forecastView: ForecastView? = null

    fun setView(forecastView: ForecastView) {
        this.forecastView = forecastView
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
        getForecastUseCase.unsubscribe()
        getWeatherUseCase.unsubscribe()
        forecastView = null
    }

    fun initialize(city: String) {
        loadForecast(city)
    }

    private fun loadForecast(city: String) {
        showViewLoading()
        hideViewRetry()
        getForecast(city)
    }

    private fun showViewLoading() {
        forecastView!!.showLoading()
    }

    private fun hideViewLoading() {
        forecastView!!.hideLoading()
    }

    private fun showViewRetry() {
        forecastView!!.showRetry()
    }

    private fun hideViewRetry() {
        forecastView!!.hideRetry()
    }

    private fun showViewError(message: String?) {
        forecastView!!.showError(message)
    }

    private fun showForecastInView(forecastModel: ForecastModel) {
        forecastView!!.renderForecastWeather(forecastModel)
    }

    private fun showForecastInView(weatherModel: WeatherModel) {
        forecastView!!.renderForecastWeather(weatherModel)
    }

    private fun getForecast(city: String) {
        getForecastUseCase.setCity(city)
        getForecastUseCase.execute(ForecastSubscriber())

        getWeatherUseCase.setCity(city)
        getWeatherUseCase.execute(WeatherSubscriber())
    }

    private inner class ForecastSubscriber : Subscriber<Forecast>() {
        override fun onCompleted() {
            hideViewLoading()
        }

        override fun onError(e: Throwable) {
            showViewError(e.message)
            hideViewLoading()
            showViewRetry()
        }

        override fun onNext(forecast: Forecast) {
            showForecastInView(forecastModelDataMapper.transform(forecast))
        }
    }

    private inner class WeatherSubscriber : Subscriber<Weather>() {
        override fun onCompleted() {
            hideViewLoading()
        }

        override fun onError(e: Throwable) {
            showViewError(e.message)
            hideViewLoading()
            showViewRetry()
        }

        override fun onNext(weather: Weather) {
            showForecastInView(weatherModelDataMapper.transform(weather))
        }
    }
}
