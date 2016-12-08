package com.example.maunorafiq.pawangcuaca.presentation

import android.app.Application

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.ApplicationComponent
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.DaggerApplicationComponent
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.ApplicationModule

import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by maunorafiq on 11/29/16.
 */

class AndroidApplication : Application() {

    var applicationComponent: ApplicationComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        this.initializeRealm()
        this.initializeInjector()
    }

    private fun initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    private fun initializeRealm() {
        Realm.init(this)
        val configuration = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(configuration)
    }
}
