package com.example.dj0708.androiddemo20151224.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.example.dj0708.androiddemo20151224.R;
import com.example.dj0708.androiddemo20151224.custom.CustomFragmentTabHost;

public class MainActivity extends FragmentActivity {
    private static final String TAB_HOME_TAG = "tab_home";
    private static final String TAB_LIST_SECTION_TAG = "tab_listSection";
    private static final String TAB_MINE_TAG = "tab_mine";

    private CustomFragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTabHost = (CustomFragmentTabHost)findViewById(R.id.tab_host);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.real_tab_content);
        mTabHost.getTabWidget().setDividerDrawable(null);

        LayoutInflater inflater = LayoutInflater.from(this);
        TabSpec homeTabSpec = mTabHost.newTabSpec(TAB_HOME_TAG).setIndicator(getTabIndicatorView(R.string.main_tab_home, inflater, R.drawable.tab_home));
        mTabHost.addTab(homeTabSpec, HomeContainerFragment.class, null);

        TabSpec listSectionSpec = mTabHost.newTabSpec(TAB_LIST_SECTION_TAG).setIndicator(getTabIndicatorView(R.string.main_tab_invest_finance, inflater, R.drawable.tab_listsection));
        mTabHost.addTab(listSectionSpec, ListSectionContainerFragment.class, null);

        TabSpec mineSpec = mTabHost.newTabSpec(TAB_MINE_TAG).setIndicator(getTabIndicatorView(R.string.main_tab_my_account, inflater, R.drawable.tab_mine));
        mTabHost.addTab(mineSpec, MineContainerFragment.class, null);

        mTabHost.setCurrentTab(0);
    }

    @Override
    public void onBackPressed() {
        boolean popFragment = false;
        String currentTabTag = mTabHost.getCurrentTabTag();

        if ( currentTabTag.equals(TAB_HOME_TAG) ) {
            popFragment = ((BaseContainerFragment)getSupportFragmentManager().findFragmentByTag(TAB_HOME_TAG)).popFragment();
        } else if ( currentTabTag.equals(TAB_LIST_SECTION_TAG) ) {
            popFragment = ((BaseContainerFragment)getSupportFragmentManager().findFragmentByTag(TAB_LIST_SECTION_TAG)).popFragment();
        } else  if ( currentTabTag.equals(TAB_MINE_TAG) ) {
            popFragment = ((BaseContainerFragment)getSupportFragmentManager().findFragmentByTag(TAB_MINE_TAG)).popFragment();
        }

        if ( !popFragment ) {
            finish();
        }
    }

    private View getTabIndicatorView(int stringId, LayoutInflater inflater, int drawableId) {
        View tabView = inflater.inflate(R.layout.main_tab_indicator_view, null);
        ImageView imgView = (ImageView)tabView.findViewById(R.id.tabIv);
        TextView textView = (TextView)tabView.findViewById(R.id.tabTitleTv);
        imgView.setBackgroundResource(drawableId);

        if (stringId != 0) {
            textView.setText(stringId);
        } else {
            textView.setVisibility(View.GONE);
        }
        return tabView;
    }
}
