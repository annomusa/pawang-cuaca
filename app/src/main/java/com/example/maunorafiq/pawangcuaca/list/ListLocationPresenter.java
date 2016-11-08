package com.example.maunorafiq.pawangcuaca.list;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.example.maunorafiq.pawangcuaca.base.BasePresenterImpl;
import com.example.maunorafiq.pawangcuaca.di.CustomScope;
import com.example.maunorafiq.pawangcuaca.model.reamldb.RealmCity;
import com.example.maunorafiq.pawangcuaca.service.RestApi;
import com.example.maunorafiq.pawangcuaca.usecase.GetLocation;
import com.example.maunorafiq.pawangcuaca.usecase.contract.GetLocationContract;
import com.example.maunorafiq.pawangcuaca.usecase.GetWeather;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
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

    private RestApi restApi;
    private ListLocationContract.View mInterface;
    private GetLocationContract.RequestListener mInteractorLocation;

    private Realm mRealm;

    @Inject
    ListLocationPresenter(RestApi restApi) {
        this.restApi = restApi;
    }

    public void setView(ListLocationContract.View view) {
        mInterface = view;
    }

    public void setContext(Context context) {
        mInteractorLocation = new GetLocation(context);
        mInteractorLocation.setPresenter(this);
    }

    @Override
    public RealmResults<RealmCity> fetchCities() {
        return mRealm.where(RealmCity.class).findAll();
    }

    @Override
    public void addNewRealmCity(String id, String city) {
        boolean isNotExist = mRealm.where(RealmCity.class).equalTo("id", id).findAll().size() == 0;
        if ( isNotExist ) {
            mRealm.executeTransaction(realm -> {
                RealmCity realmCity = realm.createObject(RealmCity.class, id);
                realmCity.setName(city);
                findWeather(id, city, 0, 0);
            });
        }
        printRealm();
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
        mRealm.executeTransaction(realm -> {
            RealmCity realmResponse = new RealmCity();
            realmResponse.setId(response.getId());
            realmResponse.setName( response.getCity()!= null ? response.getCity() : response.getOWeatherResponse().getName() );
            int temperature = response.getOWeatherResponse().getMain().getTemp().intValue();
            Log.d(TAG, "onNext: " + temperature);
            realmResponse.setTemperature(Integer.toString(temperature));
            realmResponse.setImageUrl(response.getOWeatherResponse().getWeather().get(0).getIcon());
            realm.copyToRealmOrUpdate(realmResponse);
        });
    }

    public void findWeather(String id, String city, double lat, double lon) {
        GetWeather getWeather = new GetWeather(restApi);
        getWeather.setRequest(id, city, lat, lon);
        subscribe(getWeather.execute(), this);
    }

    @Override
    public void updateWeather() {
        RealmResults<RealmCity> realmCities = mRealm.where(RealmCity.class).findAll();
        for (RealmCity realmCity : realmCities) {
            if (!realmCity.getId().equals("current_location"))
                findWeather(realmCity.getId(), realmCity.getName(), 0, 0);
        }
    }

    @Override
    public void retrieveCurrentLocation(Location location) {
        findWeather("current_location", null, location.getLatitude(), location.getLongitude());
        mInteractorLocation.stopGetCurrentLocation();
    }

    @Override
    public void onCreate(Context ctx) {
        super.onCreate(ctx);
        mRealm = Realm.getDefaultInstance();
        setView((ListLocationActivity) ctx);
        setContext(ctx);
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
    public void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    @Override
    public void getCurrentLocation() {
        mInteractorLocation.onStartGApi();
    }

    @Override
    public void requestGps() {
        mInteractorLocation.requestGps();
    }

    void printRealm() {
        RealmResults<RealmCity> realmCities = mRealm.where(RealmCity.class).findAll();
        for (RealmCity realmCity : realmCities) {
            Log.d(TAG, "printRealmCities: " + realmCity.getId() + " " + realmCity.getName() + " " + realmCity.getImageUrl());
        }
    }
}
