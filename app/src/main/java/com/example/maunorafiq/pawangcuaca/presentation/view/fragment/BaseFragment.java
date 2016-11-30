package com.example.maunorafiq.pawangcuaca.presentation.view.fragment;

import android.app.Fragment;
import android.widget.Toast;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.HasComponent;

/**
 * Created by maunorafiq on 11/29/16.
 */

public class BaseFragment extends Fragment {

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

}
