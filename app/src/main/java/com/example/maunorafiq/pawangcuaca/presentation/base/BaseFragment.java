package com.example.maunorafiq.pawangcuaca.presentation.base;

import android.app.Fragment;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.HasComponent;

/**
 * Created by maunorafiq on 11/29/16.
 */

public class BaseFragment extends Fragment {

    private final String TAG = this.getClass().getSimpleName();

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ");
    }
}
