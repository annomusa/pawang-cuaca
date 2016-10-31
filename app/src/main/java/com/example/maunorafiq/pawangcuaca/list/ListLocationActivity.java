package com.example.maunorafiq.pawangcuaca.list;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.maunorafiq.pawangcuaca.App;
import com.example.maunorafiq.pawangcuaca.Constant;
import com.example.maunorafiq.pawangcuaca.R;
import com.example.maunorafiq.pawangcuaca.list.Presenter.GetLocationPresenter;
import com.example.maunorafiq.pawangcuaca.list.Presenter.ListLocationPresenter;
import com.example.maunorafiq.pawangcuaca.list.adapter.ItemLocationAdapter;
import com.example.maunorafiq.pawangcuaca.model.City;
import com.example.maunorafiq.pawangcuaca.model.openweather.OWeatherResponse;
import com.example.maunorafiq.pawangcuaca.service.RestApi;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

public class ListLocationActivity extends AppCompatActivity implements ListLocationContract.View, GetLocationContract.View {

    @Inject RestApi restApi;
    @Bind(R.id.list_location) RecyclerView rvListLocation;

    private ItemLocationAdapter mAdapter;
    private ArrayList<City> cities;
    private ListLocationPresenter mPresenter;
    private GetLocationPresenter mPresenterGetLocation;
    private String TAG = "List location activity";
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_location);

        ButterKnife.bind(this);
        App.getApiComponent(this, Constant.oWeatherUrl).inject(this);
        mPresenter = new ListLocationPresenter(this);
        mPresenterGetLocation = new GetLocationPresenter(this, this);

        cities = new ArrayList<>();

        initToolbarAndFloating();
        configRecyclerView();
    }

    private void configRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvListLocation.setLayoutManager(linearLayoutManager);
        mAdapter = new ItemLocationAdapter(cities);
        rvListLocation.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenterGetLocation.connectGApi();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
        mPresenter.fetchWeather("Kebayoran Baru");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenterGetLocation.disconnectGApi();
    }



    /*------------ CONTRACT List Location ------------*/
    @Override
    public void showComplete() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Log.d(TAG, "showError: " + message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(OWeatherResponse response) {
        Log.d(TAG, "showResult: " + response.getName() + " " + response.getMain().getTemp().toString());
        City city = new City(response.getName(), response.getMain().getTemp().toString());
        cities.add(city);
    }

    @Override
    public Observable<OWeatherResponse> getWeather(String city) {
        //http://api.openweathermap.org/data/2.5/weather?q=kebayoran%20baru&appid=90faa1669716319e787ca1ab5da48cbc
        return restApi.getWeatherByUrl(Constant.oWeatherUrl + "weather?q=" + city + "&appid=" + Constant.oWeatherApi);
    }
    /*------------ CONTRACT List Location ------------*/



    /*------------ Get Location View Contract ------------*/
    @Override
    public void showLastLocation(Location lastLocation) {
        Log.d(TAG, "showLastLocation: " + lastLocation.getLatitude() + " "  + lastLocation.getLongitude());
    }

    @Override
    public void showAccessGpsNotGranted() {
        Log.d(TAG, "showAccessGpsNotGranted: hey");
    }
    /*------------ Get Location View Contract ------------*/


    /*----- Open Search auto complete activity -----*/
    private void openSearchCity(View view){
        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
    /*----- Open Search auto complete activity -----*/


    /*----- Handle Activity Result -----*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            handlePlaceAutoComplete(resultCode, data);
        }

        else if (requestCode == GetLocationPresenter.REQUEST_CHECK_SETTINGS) {
            if (resultCode == RESULT_OK) {
                Log.d(TAG, "onActivityResult: Location enabled by user!");
            } else if (resultCode == RESULT_CANCELED) {
                Log.d(TAG, "onActivityResult: Location not enabled, user cancelled");
            }
        }
    }

    private void handlePlaceAutoComplete(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Place place = PlaceAutocomplete.getPlace(this, data);
            Log.i(TAG, "Place: " + place.getId()
                    + " " + place.getName()
                    + " " + place.getLatLng()
                    + " " + place.getAddress()
                    + " " + place.getLocale());
        } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
            Status status = PlaceAutocomplete.getStatus(this, data);
            Log.i(TAG, status.getStatusMessage());
        } else if (resultCode == RESULT_CANCELED) {
            Log.i(TAG, "handlePlaceAutoComplete: Canceled");
        }
    }
    /*----- Handle Activity Result -----*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Initialize toolbar and floating button
    private void initToolbarAndFloating() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this::openSearchCity);
    }
}
