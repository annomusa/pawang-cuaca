package com.example.maunorafiq.pawangcuaca.list;


import android.location.Location;
import android.os.Bundle;

/**
 * Created by maunorafiq on 10/31/16.
 */

public interface GetLocationContract {

    interface View {
        void showLocation(Location location);
        void showAccessGpsNotGranted();
    }

    interface UserActionListener {
        void fetchLocation();
        void connectGApi();
        void disconnectGApi();
        void stopLocationUpdate();
    }
}
