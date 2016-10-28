package com.example.maunorafiq.pawangcuaca.location;

import com.example.maunorafiq.pawangcuaca.base.BasePresenter;
import com.example.maunorafiq.pawangcuaca.base.BasePresenterImpl;
import com.example.maunorafiq.pawangcuaca.model.openweather.OWeatherResponse;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;

/**
 * Created by maunorafiq on 10/28/16.
 */

public class ListLocationPresenter extends BasePresenterImpl implements Observer<OWeatherResponse> {
    private ListLocationInterface mInterface;

    public ListLocationPresenter(ListLocationInterface mInterface) {
        this.mInterface = mInterface;
    }

    @Override
    public void onCompleted() {
        mInterface.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if(e instanceof java.net.SocketTimeoutException || e instanceof HttpException) {
            mInterface.onError("Timeout!");
        } else {
            mInterface.onError(e.getMessage());
        }
    }

    @Override
    public void onNext(OWeatherResponse response) {
        mInterface.onResponse(response);
    }

    public void fetchWeather(){
        unSubscribeAll();
        subscribe(mInterface.getWeather(), this);
    }
}
