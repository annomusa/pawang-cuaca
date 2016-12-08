package com.example.maunorafiq.pawangcuaca.presentation.internal.di.module

import android.app.Activity

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity

import dagger.Module
import dagger.Provides

/**
 * Created by maunorafiq on 11/29/16.
 */

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    internal fun provideActivity(): Activity {
        return this.activity
    }
}
