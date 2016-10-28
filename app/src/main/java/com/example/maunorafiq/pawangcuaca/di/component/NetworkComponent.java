package com.example.maunorafiq.pawangcuaca.di.component;

import com.example.maunorafiq.pawangcuaca.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by maunorafiq on 10/28/16.
 */
@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent {
    Retrofit retrofit();
}
