package com.example.maunorafiq.pawangcuaca.presentation.base

import android.app.Fragment
import android.widget.Toast

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.HasComponent

/**
 * Created by maunorafiq on 11/29/16.
 */

open class BaseFragment : Fragment() {

    protected fun showToastMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    protected fun <C> getComponent(componentType: Class<C>): C {
        return componentType.cast((activity as HasComponent<*>).component)
    }

}
