package com.example.maunorafiq.pawangcuaca.presentation;

import android.app.Application;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.ApplicationComponent;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.DaggerApplicationComponent;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.ApplicationModule;

/**
 * Created by maunorafiq on 11/29/16.
 */

public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
