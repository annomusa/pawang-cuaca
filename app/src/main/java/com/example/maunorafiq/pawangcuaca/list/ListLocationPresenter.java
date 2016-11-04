package com.example.maunorafiq.pawangcuaca.list;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.example.maunorafiq.pawangcuaca.base.BasePresenterImpl;
import com.example.maunorafiq.pawangcuaca.di.CustomScope;
import com.example.maunorafiq.pawangcuaca.model.City;
import com.example.maunorafiq.pawangcuaca.service.RestApi;
import com.example.maunorafiq.pawangcuaca.usecase.GetLocation;
import com.example.maunorafiq.pawangcuaca.usecase.contract.GetLocationContract;
import com.example.maunorafiq.pawangcuaca.usecase.GetWeather;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;

/**
 * Created by maunorafiq on 10/28/16.
 */
@CustomScope
public class ListLocationPresenter extends BasePresenterImpl implements
        Observer<GetWeather.CityWeather>,
        ListLocationContract.UserActionListener,
        GetLocationContract.Presenter {

    private static final String TAG = "List Location Presenter";

    private ArrayList<City> mCities;

    private RestApi restApi;
    private ListLocationContract.View mInterface;
    private GetLocationContract.RequestListener mInteractorLocation;

    @Inject
    public ListLocationPresenter(RestApi restApi) {
        this.restApi = restApi;

        mCities = new ArrayList<>();
        mCities.add(new City("Your City", "-"));
        mCities.add(new City("Makassar", "-"));
        mCities.add(new City("Surabaya", "-"));
        mCities.add(new City("Cikarang Barat", "-"));
    }

    @Override
    public void setView(ListLocationContract.View view) {
        mInterface = view;
    }

    @Override
    public ArrayList<City> fetchCities() {
        return mCities;
    }

    @Override
    public void addNewCity(String city) {
        mCities.add(new City(city, "-"));
    }

    @Override
    public void setContext(Context context) {
        mInteractorLocation = new GetLocation(context);
        mInteractorLocation.setPresenter(this);
    }

    @Override
    public void requestLocation() {
        mInteractorLocation.startLocationUpdate();
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
    public void onNext(GetWeather.CityWeather response) {
        mInterface.showResult(response);
    }

    @Override
    public void fetchWeather(int number, String city, double lat, double lon) {
        GetWeather getWeather = new GetWeather(restApi);
        getWeather.setRequest(number, city, lat, lon);
        getWeather.printRequest();
        subscribe(getWeather.execute(), this);
    }

    @Override
    public void retrieveCurrentLocation(Location location) {
        Log.d(TAG, "retrieveCurrentLocation: " + location.getLatitude() + " " + location.getLongitude());
        fetchWeather(0, null, location.getLatitude(), location.getLongitude());
        mInteractorLocation.stopGetCurrentLocation();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mInteractorLocation.onStopGApi();
    }

    @Override
    public void getCurrentLocation() {
        mInteractorLocation.onStartGApi();
    }

    @Override
    public void requestGps() {
        mInteractorLocation.requestGps();
    }
}
