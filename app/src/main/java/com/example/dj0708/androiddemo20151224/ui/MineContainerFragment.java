package com.example.dj0708.androiddemo20151224.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dj0708.androiddemo20151224.R;

/**
 * Created by dj0708 on 15/12/24.
 */
public class MineContainerFragment extends BaseContainerFragment {
    private boolean viewInited;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_container_fragment, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if ( !viewInited ) {
            viewInited = true;
            initView();
        }
    }

    private void initView() {
        replaceFragment(new MineFragment(), false);
    }

}
