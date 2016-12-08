package com.example.maunorafiq.pawangcuaca.presentation.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.example.maunorafiq.pawangcuaca.presentation.mapper.CityModelDataMapper;
import com.example.maunorafiq.pawangcuaca.presentation.mapper.WeatherModelDataMapper;
import com.example.maunorafiq.pawangcuaca.presentation.model.CityModel;
import com.example.maunorafiq.pawangcuaca.presentation.view.CityListView;
import com.icehousecorp.maunorafiq.domain.city.City;
import com.icehousecorp.maunorafiq.domain.city.interactor.GetCity;
import com.icehousecorp.maunorafiq.domain.weather.Weather;
import com.icehousecorp.maunorafiq.domain.weather.interactor.GetWeather;
import com.icehousecorp.maunorafiq.domain.city.interactor.PutCity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by maunorafiq on 11/29/16.
 */

@PerActivity
public class CitiesPresenter implements Presenter {

    private final String TAG = this.getClass().getSimpleName();

    private CityListView cityListView;

    private final GetWeather getWeatherUseCase;
    private final PutCity putCityUseCase;
    private final GetCity getCityUseCase;
    private final CityModelDataMapper cityModelDataMapper;
    private final WeatherModelDataMapper weatherModelDataMapper;

    @Inject
    public CitiesPresenter(GetWeather getWeatherUseCase,
                           PutCity putCityUseCase,
                           GetCity getCityUseCase,
                           CityModelDataMapper cityModelDataMapper,
                           WeatherModelDataMapper weatherModelDataMapper) {
        this.getWeatherUseCase = getWeatherUseCase;
        this.putCityUseCase = putCityUseCase;
        this.getCityUseCase = getCityUseCase;
        this.cityModelDataMapper = cityModelDataMapper;
        this.weatherModelDataMapper = weatherModelDataMapper;
    }

    public void setView(@NonNull CityListView cityListView) {
        this.cityListView = cityListView;
    }

    @Override
    public void resume() { }

    @Override
    public void pause() { }

    @Override
    public void destroy() {
        getWeatherUseCase.unsubscribe();
        putCityUseCase.unsubscribe();
        getCityUseCase.unsubscribe();
        cityListView = null;
    }

    public void initialize() {
        loadListWeather();
    }

    private void loadListWeather() {
        showViewLoading();
        hideViewRetry();
        getListWeather();
    }

    public void addCity(String city) {
        cityListView.addNewCity(new CityModel(city));
        fetchWeather(city);
    }

    private void fetchWeather(String city) {
        putCityUseCase.setCity(city);
        putCityUseCase.execute(new WeatherSubscriber());
    }

    public void onCityClicked(CityModel cityModel) {
        cityListView.viewCity(cityModel);
    }

    private void showViewLoading() {
        cityListView.showLoading();
    }

    private void hideViewLoading() {
        cityListView.hideLoading();
    }

    private void showViewRetry() {
        cityListView.showRetry();
    }

    private void hideViewRetry() {
        cityListView.hideRetry();
    }

    private void showViewError(String message) {
        cityListView.showError(message);
    }

    private void showListCityInView(List<City> cityList) {
        cityListView.renderCityList(cityModelDataMapper.transform(cityList));
    }

    private void updateCityWeatherInVIew(Weather weather) {
        cityListView.updateCity(weatherModelDataMapper.transform(weather));
    }

    private void getListWeather() {
        getCityUseCase.execute(new ListCitySubscriber());
    }

    private final class ListCitySubscriber extends Subscriber<List<City>> {
        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            hideViewLoading();
            showViewRetry();
            showViewError(e.getMessage());
        }

        @Override
        public void onNext(List<City> cities) {
            for (City city : cities) {
                getWeatherUseCase.setCity(city.getCityName());
                getWeatherUseCase.execute(new WeatherSubscriber());
            }
            showListCityInView(cities);
        }
    }

    private final class WeatherSubscriber extends Subscriber<Weather> {
        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            hideViewLoading();
            showViewRetry();
            showViewError(e.getMessage());
        }

        @Override
        public void onNext(Weather weather) {
            if (weather == null) {
                Log.d(TAG, "Weather: City already in list");
                return;
            }
            updateCityWeatherInVIew(weather);
        }
    }
}
