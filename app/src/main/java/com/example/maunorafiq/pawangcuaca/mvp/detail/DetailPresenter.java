package com.example.maunorafiq.pawangcuaca.detail;

import android.util.Log;

import com.example.maunorafiq.pawangcuaca.mvp.Constant;
import com.example.maunorafiq.pawangcuaca.base.BasePresenterImpl;
import com.example.maunorafiq.pawangcuaca.di.CustomScope;
import com.example.maunorafiq.pawangcuaca.model.openweather.forecast.OWeatherResponse2;
import com.example.maunorafiq.pawangcuaca.mvp.service.RestApi;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by maunorafiq on 11/10/16.
 */

@CustomScope
public class DetailPresenter extends BasePresenterImpl implements
        DetailContract.UserActionListener,
        Observer<OWeatherResponse2> {

    private RestApi restApi;
    private DetailContract.View mInterface;

    private static final String TAG = "Detail Presenter";

    @Inject
    public DetailPresenter(RestApi restApi) {
        this.restApi = restApi;
    }

    public void setView (DetailContract.View mInterface) {
        this.mInterface = mInterface;
    }

    @Override
    public void requestDetail(String city) {
        Log.d(TAG, "requestDetail: " + city);
        subscribe(restApi.getWeatherForecast(city, "metric", Constant.oWeatherApi), this);
    }

    @Override
    public void onCompleted() {
        mInterface.showCompletion();
    }

    @Override
    public void onError(Throwable e) {
        mInterface.showError(e.getMessage());
    }

    @Override
    public void onNext(OWeatherResponse2 OWeatherResponse2) {
        mInterface.showResult(OWeatherResponse2);
    }
}
