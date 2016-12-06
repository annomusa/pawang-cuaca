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
import com.icehousecorp.maunorafiq.domain.weathers.Weather;
import com.icehousecorp.maunorafiq.domain.weathers.interactor.GetWeather;
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

    private List<CityModel> cityModelList;

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

        cityModelList = new ArrayList<>();
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
        cityListView = null;
    }

    public void initialize() {
        loadListWeather();
    }

    public void addCity(String city) {
        Log.d(TAG, "addCity: " + city);
        cityListView.addNewCity(new CityModel(city));
        fetchWeather(city);
    }

    private void fetchWeather(String city) {
        putCityUseCase.setCity(city);
        putCityUseCase.execute(new WeatherSubscriber());
    }

    private void loadListWeather() {
        showViewLoading();
        hideViewRetry();
        getListWeather();
    }

    public void onWeatherClicked(CityModel cityModel) {
        cityListView.viewCity(cityModel);
    }

    public void showViewLoading() {
        cityListView.showLoading();
    }

    public void hideViewLoading() {
        cityListView.hideLoading();
    }

    public void showViewRetry() {
        cityListView.showRetry();
    }

    public void hideViewRetry() {
        cityListView.hideRetry();
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
            Log.d(TAG, "onError: " + e.getMessage());
            showViewRetry();
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

        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError: " + e.getMessage());
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
