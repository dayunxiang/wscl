package com.tmxk.wscl.android.ui.device;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.adpter.GirdDropDownAdapter;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.emuns.SewageStationEnum;
import com.tmxk.wscl.android.mvp.presenter.DeviceArchivePresenter;
import com.tmxk.wscl.android.mvp.view.DeviceArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.tmxk.wscl.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceArchiveActivity extends MvpActivity<DeviceArchivePresenter> implements DeviceArchiveView {
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String headers[] = {"请选择区县", "请选择污水站", "运营编号", "控制ID"};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter regionAdapter;
    private GirdDropDownAdapter stationAdapter;

    private String regions[] = {"不限", "惠山区"};

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

    @Override
    protected DeviceArchivePresenter createPresenter() {
        return new DeviceArchivePresenter(this);
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
        stationAdapter = new GirdDropDownAdapter(this, SewageStationEnum.getSewageNameList());
        stationView.setAdapter(stationAdapter);

        //operate num
        EditText operateNumEditText = new EditText(this);
        operateNumEditText.setHint("请输入运营编号");
        operateNumEditText.setHintTextColor(Color.parseColor("#CCCCCC"));
        operateNumEditText.setBackgroundResource(R.drawable.dl_bg);
        operateNumEditText.setPadding(20, 20, 20, 20);
        operateNumEditText.setTextSize(15);
        operateNumEditText.setTextColor(Color.parseColor("#666666"));

        //control id
        EditText controlIdEditText = new EditText(this);
        controlIdEditText.setHint("控制id");
        controlIdEditText.setHintTextColor(Color.parseColor("#CCCCCC"));
        controlIdEditText.setBackgroundResource(R.drawable.dl_bg);
        controlIdEditText.setPadding(20, 20, 20, 20);
        controlIdEditText.setTextSize(15);
        controlIdEditText.setTextColor(Color.parseColor("#666666"));

        //init popupViews
        popupViews.add(regionView);
        popupViews.add(stationView);
        popupViews.add(operateNumEditText);
        popupViews.add(controlIdEditText);

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
                mDropDownMenu.setTabText(position == 0 ? headers[1] : SewageStationEnum.getSewageNameList().get(position));
                mDropDownMenu.closeMenu();
            }
        });

        //init context view
        TextView contentView = new TextView(this);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));;
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

    @Override
    public void getDataSuccess(Object model, DataTypeEnum dataTypeEnum) {

    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }
}