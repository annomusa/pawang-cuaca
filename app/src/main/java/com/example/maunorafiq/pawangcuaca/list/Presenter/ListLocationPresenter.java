package com.example.maunorafiq.pawangcuaca.list.Presenter;

import com.example.maunorafiq.pawangcuaca.Constant;
import com.example.maunorafiq.pawangcuaca.base.BasePresenterImpl;
import com.example.maunorafiq.pawangcuaca.di.CustomScope;
import com.example.maunorafiq.pawangcuaca.list.ListLocationContract;
import com.example.maunorafiq.pawangcuaca.model.openweather.OWeatherResponse;
import com.example.maunorafiq.pawangcuaca.service.RestApi;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;

/**
 * Created by maunorafiq on 10/28/16.
 */
@CustomScope
public class ListLocationPresenter extends BasePresenterImpl implements
        Observer<OWeatherResponse>,
        ListLocationContract.UserActionListener {
    private ListLocationContract.View mInterface;
    private RestApi restApi;

    @Inject
    public ListLocationPresenter(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public void setView(ListLocationContract.View view) {
        mInterface = view;
    }

    @Override
    public void onCompleted() {
        mInterface.showComplete();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if(e instanceof java.net.SocketTimeoutException || e instanceof HttpException) {
            mInterface.showError("Timeout!");
        } else {
            mInterface.showError(e.getMessage());
        }
    }

    @Override
    public void onNext(OWeatherResponse response) {
        mInterface.showResult(response);
    }

    public void fetchWeatherByCity(String city){
        subscribe(restApi.getWeatherByCity(city, Constant.oWeatherApi), this);
    }

    public void fetchWeatherByCoordinates(double lat, double lon) {
        subscribe(restApi.getWeatherByCoordinates(lat,lon, Constant.oWeatherApi), this);
    }


}
