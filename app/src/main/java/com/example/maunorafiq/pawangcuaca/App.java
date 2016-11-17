package com.example.maunorafiq.pawangcuaca;

import android.app.Application;
import android.content.Context;

import com.example.maunorafiq.pawangcuaca.di.component.ApiComponent;
import com.example.maunorafiq.pawangcuaca.di.component.DaggerApiComponent;
import com.example.maunorafiq.pawangcuaca.di.component.DaggerNetworkComponent;
import com.example.maunorafiq.pawangcuaca.di.component.NetworkComponent;
import com.example.maunorafiq.pawangcuaca.di.module.NetworkModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by maunorafiq on 10/28/16.
 */

public class App extends Application{
    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
    }

    public NetworkComponent getNetworkComponent(String url) {
        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(url))
                .build();
    }

    public static ApiComponent getApiComponent(Context context, String url){
        App app = (App) context.getApplicationContext();
        if(app.mApiComponent == null){
            app.mApiComponent = DaggerApiComponent.builder()
                    .networkComponent(app.getNetworkComponent(url))
                    .build();
        }
        return app.mApiComponent;
    }
}
