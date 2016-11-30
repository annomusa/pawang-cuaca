package com.example.maunorafiq.pawangcuaca.list;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.maunorafiq.pawangcuaca.AndroidApplication;
import com.example.maunorafiq.pawangcuaca.Constant;
import com.example.maunorafiq.pawangcuaca.R;
import com.example.maunorafiq.pawangcuaca.detail.DetailActivity;
import com.example.maunorafiq.pawangcuaca.list.adapter.ItemListAdapter;
import com.example.maunorafiq.pawangcuaca.list.decorator.DividerItemDecoration;
import com.example.maunorafiq.pawangcuaca.usecase.GetWeather;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListLocationActivity extends AppCompatActivity implements
        ListLocationContract.View {

    @Inject ListLocationPresenter mPresenter;
    @Bind(R.id.list_location) RecyclerView rvListLocation;
    @Bind(R.id.content_list_location) SwipeRefreshLayout refreshListLocation;

    private String TAG = "ListTime location activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_location);

        ButterKnife.bind(this);

        AndroidApplication.getApiComponent(this, Constant.oWeatherUrl).inject(this);

        mPresenter.onCreate(this);

        initListData();
        initFloatingButton();
        configRecyclerView();
        configSwipeLayout();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
        mPresenter.updateWeather();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }


    /*----------- Init -----------*/
    private void initListData () {
        mPresenter.addNewRealmCity("current_location", "Locating . . .");
    }

    private void initFloatingButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this::openSearchCity);
    }

    private void configRecyclerView () {
        rvListLocation.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvListLocation.setAdapter(new ItemListAdapter(this, mPresenter.fetchCities(), true));
        rvListLocation.setHasFixedSize(true);
        rvListLocation.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createRecyclerCallback());
        itemTouchHelper.attachToRecyclerView(rvListLocation);
    }

    private ItemTouchHelper.Callback createRecyclerCallback () {
        return new ItemTouchHelper.SimpleCallback(
                //ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                mPresenter.changeOrder(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    Intent detail = new Intent(getApplicationContext(), DetailActivity.class);
                    detail.putExtra("location", mPresenter.getCityByOrdinal(viewHolder.getAdapterPosition()));
                    startActivity(detail);
                } else {
                    mPresenter.deleteItem(viewHolder.getAdapterPosition());
                }
            }
        };
    }

    private void configSwipeLayout() {
        refreshListLocation.setOnRefreshListener(() -> {
            mPresenter.updateWeather();
        });
    }
    /*----------- Init -----------*/



    /*------------ Contract ------------*/
    @Override
    public void showCompletion() {
        refreshListLocation.setRefreshing(false);
    }

    @Override
    public void showError(String message) {
        Log.d(TAG, "showError: " + message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        refreshListLocation.setRefreshing(false);
    }

    @Override
    public void showResult(GetWeather.CityWeather response) {

    }

    /*------------ Contract ------------*/



    /*----- Open Search auto complete activity -----*/
    private void openSearchCity(View view){
        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .build(this);
            startActivityForResult(intent, Constant.PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
    /*----- Open Search auto complete activity -----*/



    /*----- Handle Permission & Activity Result -----*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constant.PLACE_AUTOCOMPLETE_REQUEST_CODE:
                handlePlaceAutoComplete(resultCode, data);
                break;
            case Constant.REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        mPresenter.requestLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        // TODO : Implement handler if user cancel activate GPS
                        break;
                }
                break;
            default:
                break;
        }
    }

    private void handlePlaceAutoComplete(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Place place = PlaceAutocomplete.getPlace(this, data);
            mPresenter.addNewRealmCity(place.getId(), place.getName().toString());
        }

        else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
            Status status = PlaceAutocomplete.getStatus(this, data);
        }

        else if (resultCode == RESULT_CANCELED) {
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case Constant.PERMISSION_ACCESS_COURSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mPresenter.requestGps();
                }
                else {
                    // TODO : Implement handler if permission not granted
                }
                break;
        }
    }
    /*----- Handle Permission & Activity Result -----*/



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
}
