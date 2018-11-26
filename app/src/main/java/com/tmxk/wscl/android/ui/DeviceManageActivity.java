package com.tmxk.wscl.android.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.adpter.GirdDropDownAdapter;
import com.tmxk.wscl.android.adpter.ListDropDownAdapter;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.tmxk.wscl.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceManageActivity extends AppCompatActivity {
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String headers[] = {"城市", "污水站"};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter regionAdapter;
    private ListDropDownAdapter stationAdapter;

    private String regions[] = {"不限", "惠山区"};
    private String stations[] = {"不限", "001堰桥街道姑里社区陈家巷", "001堰桥街道姑里社区惠家巷"};

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_device_manage);
        ButterKnife.bind(this);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initView();
    }

    private void initView() {
        //init toolbar
        toolbar.setTitle("站点档案");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //init region menu
        ListView regionView = new ListView(this);
        regionAdapter = new GirdDropDownAdapter(this, Arrays.asList(regions));
        regionView.setDividerHeight(0);
        regionView.setAdapter(regionAdapter);

        //init station menu
        ListView stationView = new ListView(this);
        stationView.setDividerHeight(0);
        stationAdapter = new ListDropDownAdapter(this, Arrays.asList(regions));
        stationView.setAdapter(stationAdapter);

        //init popupViews
        popupViews.add(regionView);
        popupViews.add(stationView);

        //add item click event
        regionView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                regionAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : regions[position]);
                mDropDownMenu.closeMenu();
            }
        });

        stationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                stationAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : stations[position]);
                mDropDownMenu.closeMenu();
            }
        });

        //init context view
        TextView contentView = new TextView(this);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentView.setText("内容显示区域");
        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        //init dropDownView
        if (mDropDownMenu != null) {
            mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
        }
    }

    @Override
    public void onBackPressed() {
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }
}