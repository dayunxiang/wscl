package com.tmxk.wscl.android.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.nshmura.recyclertablayout.RecyclerTabLayout;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.adpter.MenuPagerAdapter;
import com.tmxk.wscl.android.bean.MenuItem;
import com.tmxk.wscl.android.util.MenuDataUtil;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        List<MenuItem> items = MenuDataUtil.loadNavigateItems(this);
        MenuPagerAdapter adapter = new MenuPagerAdapter(this);
        adapter.addAll(items);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        RecyclerTabLayout recyclerTabLayout = findViewById(R.id.recycler_tab_layout);
        recyclerTabLayout.setUpWithViewPager(viewPager);
    }
}