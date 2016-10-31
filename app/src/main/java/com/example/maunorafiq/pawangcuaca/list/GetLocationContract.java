package com.example.maunorafiq.pawangcuaca.list;


import android.location.Location;

/**
 * Created by maunorafiq on 10/31/16.
 */

public interface GetLocationContract {

    interface View {
        void showLastLocation(Location lastLocation);
        void showAccessGpsNotGranted();
    }

    interface UserActionListener {
        void fetchLocation();
        void connectGApi();
        void disconnectGApi();
    }
}
