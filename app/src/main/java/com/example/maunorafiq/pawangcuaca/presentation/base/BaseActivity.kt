package com.example.maunorafiq.pawangcuaca.presentation.base

import android.app.Activity
import android.app.Fragment
import android.app.FragmentTransaction
import android.os.Bundle

import com.example.maunorafiq.pawangcuaca.presentation.AndroidApplication
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.ApplicationComponent
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.module.ActivityModule
import com.example.maunorafiq.pawangcuaca.presentation.navigation.Navigator

import javax.inject.Inject

/**
 * Created by maunorafiq on 11/29/16.
 */

open class BaseActivity : Activity() {

    @Inject lateinit var navigator: Navigator

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.applicationComponent.inject(this)
    }

    protected fun addFragment(containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = this.fragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.commit()
    }

    protected val applicationComponent: ApplicationComponent
        get() = (application as AndroidApplication).applicationComponent

    protected val activityModule: ActivityModule
        get() = ActivityModule(this)
}
