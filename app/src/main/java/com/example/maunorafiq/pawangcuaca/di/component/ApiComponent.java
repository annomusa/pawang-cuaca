package com.example.maunorafiq.pawangcuaca.di.component;

import com.example.maunorafiq.pawangcuaca.di.CustomScope;
import com.example.maunorafiq.pawangcuaca.di.module.ApiModule;
import com.example.maunorafiq.pawangcuaca.list.ListLocationActivity;

import dagger.Component;

/**
 * Created by maunorafiq on 10/28/16.
 */
@CustomScope
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {
    ListLocationActivity inject(ListLocationActivity activity);
}
