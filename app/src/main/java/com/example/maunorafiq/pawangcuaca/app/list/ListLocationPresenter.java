package com.example.maunorafiq.pawangcuaca.list;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.example.maunorafiq.pawangcuaca.app.base.BasePresenterImpl;
import com.example.maunorafiq.pawangcuaca.di.CustomScope;
import com.example.maunorafiq.pawangcuaca.model.reamldb.RealmCity;
import com.example.maunorafiq.pawangcuaca.service.RestApi;
import com.example.maunorafiq.pawangcuaca.usecase.GetLocation;
import com.example.maunorafiq.pawangcuaca.usecase.contract.GetLocationContract;
import com.example.maunorafiq.pawangcuaca.usecase.GetWeather;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
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

    private static final String TAG = "ListTime Location Presenter";

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
        return mRealm.where(RealmCity.class).findAll().sort("ordinal", Sort.ASCENDING);
    }

    @Override
    public void deleteItem(int position) {
        if (position == 0) return;
        final RealmResults<RealmCity> cities = mRealm.where(RealmCity.class).findAll().sort("ordinal", Sort.ASCENDING);
        RealmCity city = cities.where().equalTo("ordinal", position).findFirst();

        mRealm.executeTransaction(realm -> {
            if (city != null) city.deleteFromRealm();
        });

        for (int i=position+1; i<cities.size(); i++) {
            int movedOrdinal = i;
            mRealm.executeTransaction(realm -> {
                RealmCity movedItem = new RealmCity();
                movedItem = cities.get(movedOrdinal);
                movedItem.setOrdinal(movedOrdinal-1);
                realm.copyToRealmOrUpdate( movedItem );
            });
        }
    }

    @Override
    public void addNewRealmCity(String id, String city) {
        boolean isNotExist = mRealm.where(RealmCity.class).equalTo("id", id).findAll().size() == 0;
        RealmResults<RealmCity> realmCities = mRealm.where(RealmCity.class).findAll();
        if ( isNotExist ) {
            mRealm.executeTransaction(realm -> {
                RealmCity realmCity = realm.createObject(RealmCity.class, id);
                realmCity.setName(city);
                findWeather(realmCities.size(), id, city, 0, 0);
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
        mInterface.showCompletion();
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
            realmResponse.setId ( response.getId() );
            realmResponse.setOrdinal ( response.getOrdinal() );
            realmResponse.setName ( response.getCity()!= null ? response.getCity() : response.getOWeatherResponse().getName() );
            realmResponse.setTemperature ( Integer.toString(response.getOWeatherResponse().getMain().getTemp().intValue()) );
            realmResponse.setImageUrl ( response.getOWeatherResponse().getWeather().get(0).getIcon() );
            realm.copyToRealmOrUpdate ( realmResponse );
        });
    }

    public String getCityByOrdinal(int position) {
        return mRealm.where(RealmCity.class).equalTo("ordinal", position).findFirst().getName();
    }

    public void findWeather(int ordinal, String id, String city, double lat, double lon) {
        GetWeather getWeather = new GetWeather(restApi);
        getWeather.setRequest(ordinal, id, city, lat, lon);
        subscribe(getWeather.execute(), this);
    }

    @Override
    public void updateWeather() {
        RealmResults<RealmCity> realmCities = mRealm.where(RealmCity.class).findAll();
        for (int i=0; i<realmCities.size(); i++) {
            if (!realmCities.get(i).getId().equals("current_location"))
                findWeather(i, realmCities.get(i).getId(), realmCities.get(i).getName(), 0, 0);
        }
        getCurrentLocation();
    }

    @Override
    public void retrieveCurrentLocation(Location location) {
        findWeather(0, "current_location", null, location.getLatitude(), location.getLongitude());
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
