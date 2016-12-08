package com.example.maunorafiq.pawangcuaca.presentation.internal.di.component

import android.app.Activity

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.ActivityModule

import dagger.Component

/**
 * Created by maunorafiq on 11/29/16.
 */

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun activity(): Activity
}
