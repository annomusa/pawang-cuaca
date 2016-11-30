package com.example.maunorafiq.pawangcuaca.presentation.internal.di.module;

import android.app.Activity;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maunorafiq on 11/29/16.
 */

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return this.activity;
    }
}
