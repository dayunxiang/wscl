package com.tmxk.wscl.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.tmxk.wscl.android.R;

import butterknife.BindView;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        initTabList();
//        mAdapter = new TabLayoutFragmentAdapter(getChildFragmentManager(), mTabList, getActivity(), mFragments, mTabImgs);
//        viewPager.setAdapter(mAdapter);
//        viewPager.setCurrentItem(0);
//        tabLayout.setupWithViewPager(mViewPager);
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置TabLayout的模式
//        for (int i = 0; i < tabLayout.getTabCount(); i++) {
//            tabLayout.getTabAt(i).setCustomView(mAdapter.getTabView(i));
//        }
//        tabLayout.addOnTabSelectedListener(this);//设置TabLayout的选中监听
    }
}