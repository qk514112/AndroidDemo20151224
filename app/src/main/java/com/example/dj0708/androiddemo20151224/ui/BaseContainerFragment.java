package com.example.dj0708.androiddemo20151224.ui;

/**
 * Created by dj0708 on 15/12/24.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.example.dj0708.androiddemo20151224.R;

public class BaseContainerFragment extends Fragment{
    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        if ( addToBackStack ) {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.base_container_fragment, fragment);
        transaction.commit();
        getChildFragmentManager().executePendingTransactions();
    }

    public boolean popFragment() {
        boolean pop = false;

        if ( getChildFragmentManager().getBackStackEntryCount() > 0 ) {
            pop = true;
            getChildFragmentManager().popBackStack();
        }
        return pop;
    }
}
