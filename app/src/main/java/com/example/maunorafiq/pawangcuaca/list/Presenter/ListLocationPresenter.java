package com.example.maunorafiq.pawangcuaca.list.Presenter;

import com.example.maunorafiq.pawangcuaca.base.BasePresenterImpl;
import com.example.maunorafiq.pawangcuaca.list.ListLocationContract;
import com.example.maunorafiq.pawangcuaca.model.openweather.OWeatherResponse;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;

/**
 * Created by maunorafiq on 10/28/16.
 */

public class ListLocationPresenter extends BasePresenterImpl implements Observer<OWeatherResponse>, ListLocationContract.UserActionListener {
    private ListLocationContract.View mInterface;

    public ListLocationPresenter(ListLocationContract.View mInterface) {
        this.mInterface = mInterface;
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

    public void fetchWeather(String city){
        unSubscribeAll();
        subscribe(mInterface.getWeather(city), this);
    }
}
