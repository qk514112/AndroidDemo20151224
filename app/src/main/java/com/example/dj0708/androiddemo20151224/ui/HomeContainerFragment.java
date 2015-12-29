package com.example.dj0708.androiddemo20151224.ui;

/**
 * Created by dj0708 on 15/12/24.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dj0708.androiddemo20151224.R;

public class HomeContainerFragment extends BaseContainerFragment {
    private boolean viewInit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return inflater.inflate(R.layout.base_container_fragment, null);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);

        if (!viewInit) {
            viewInit = true;
            initView();
        }
    }

    private void initView() {
        replaceFragment(new HomeFragment(), false);
    }

}
