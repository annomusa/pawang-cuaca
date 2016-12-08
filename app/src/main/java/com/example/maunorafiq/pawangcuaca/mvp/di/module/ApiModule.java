package com.example.maunorafiq.pawangcuaca.di.module;

import com.example.maunorafiq.pawangcuaca.di.CustomScope;
import com.example.maunorafiq.pawangcuaca.mvp.service.RestApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by maunorafiq on 10/28/16.
 */
@Module
public class ApiModule {
    @Provides
    @CustomScope
    RestApi provideRestApi(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }
}
