package com.example.maunorafiq.pawangcuaca.presentation.view.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.maunorafiq.pawangcuaca.presentation.AndroidApplication;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.ApplicationComponent;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.ActivityModule;
import com.example.maunorafiq.pawangcuaca.presentation.navigation.Navigator;

import javax.inject.Inject;

/**
 * Created by maunorafiq on 11/29/16.
 */

public class BaseActivity extends Activity {

    @Inject Navigator navigator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected ApplicationComponent getApplicationComponent () {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
