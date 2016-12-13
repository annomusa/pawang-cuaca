package com.example.maunorafiq.pawangcuaca.presentation.citylist

import android.util.Log
import com.example.maunorafiq.pawangcuaca.presentation.base.Presenter

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity
import com.example.maunorafiq.pawangcuaca.presentation.mapper.CityModelDataMapper
import com.example.maunorafiq.pawangcuaca.presentation.mapper.WeatherModelDataMapper
import com.example.maunorafiq.pawangcuaca.presentation.model.CityModel
import com.example.maunorafiq.pawangcuaca.presentation.citylist.CityListView
import com.icehousecorp.maunorafiq.domain.city.City
import com.icehousecorp.maunorafiq.domain.city.interactor.GetCity
import com.icehousecorp.maunorafiq.domain.weather.Weather
import com.icehousecorp.maunorafiq.domain.weather.interactor.GetWeather
import com.icehousecorp.maunorafiq.domain.city.interactor.PutCity

import java.util.ArrayList

import javax.inject.Inject

import rx.Subscriber

/**
 * Created by maunorafiq on 11/29/16.
 */

@PerActivity
class CityListPresenter
@Inject
constructor(private val getWeatherUseCase: GetWeather,
            private val putCityUseCase: PutCity,
            private val getCityUseCase: GetCity,
            private val cityModelDataMapper: CityModelDataMapper,
            private val weatherModelDataMapper: WeatherModelDataMapper) : Presenter {

    private val TAG = this.javaClass.simpleName

    private var cityListView: CityListView? = null

    fun setView(cityListView: CityListView) {
        this.cityListView = cityListView
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
        getWeatherUseCase.unsubscribe()
        putCityUseCase.unsubscribe()
        getCityUseCase.unsubscribe()
        cityListView = null
    }

    fun initialize() {
        loadListWeather()
    }

    private fun loadListWeather() {
        showViewLoading()
        hideViewRetry()
        getListWeather()
    }

    fun addCity(city: String) {
        cityListView!!.addNewCity(CityModel(city))
        fetchWeather(city)
    }

    private fun fetchWeather(city: String) {
        putCityUseCase.setCity(city)
        putCityUseCase.execute(WeatherSubscriber())
    }

    fun onCityClicked(cityModel: CityModel) {
        cityListView!!.viewCity(cityModel)
    }

    private fun showViewLoading() {
        cityListView!!.showLoading()
    }

    private fun hideViewLoading() {
        cityListView!!.hideLoading()
    }

    private fun showViewRetry() {
        cityListView!!.showRetry()
    }

    private fun hideViewRetry() {
        cityListView!!.hideRetry()
    }

    private fun showViewError(message: String?) {
        cityListView!!.showError(message)
    }

    private fun showListCityInView(cityList: List<City>) {
        cityListView!!.renderCityList(cityModelDataMapper.transform(cityList))
    }

    private fun updateCityWeatherInVIew(weather: Weather) {
        cityListView!!.updateCity(weatherModelDataMapper.transform(weather))
    }

    private fun getListWeather() {
        getCityUseCase.execute(ListCitySubscriber())
    }

    private inner class ListCitySubscriber : Subscriber<List<City>>() {
        override fun onCompleted() {
            hideViewLoading()
        }

        override fun onError(e: Throwable) {
            hideViewLoading()
            showViewRetry()
            showViewError(e.message)
        }

        override fun onNext(cities: List<City>) {
            for (city in cities) {
                getWeatherUseCase.setCity(city.cityName)
                getWeatherUseCase.execute(WeatherSubscriber())
            }
            showListCityInView(cities)
        }
    }

    private inner class WeatherSubscriber : Subscriber<Weather>() {
        override fun onCompleted() {
            hideViewLoading()
        }

        override fun onError(e: Throwable) {
            hideViewLoading()
            showViewRetry()
            showViewError(e.message)
        }

        override fun onNext(weather: Weather?) {
            if (weather == null) {
                Log.d(TAG, "Weather: City already in list")
                return
            }
            updateCityWeatherInVIew(weather)
        }
    }
}
