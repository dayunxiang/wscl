package com.tmxk.wscl.android.ui.monitor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.adpter.GirdDropDownAdapter;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.ControlMethodBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.SewageMonitorBean;
import com.tmxk.wscl.android.mvp.presenter.MonitorPresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.CommonUtil;
import com.yyydjk.library.DropDownMenu;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MonitorSewageActivity extends MvpActivity<MonitorPresenter> implements SewageArchiveView {
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String headers[] = {};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter regionAdapter;
    private GirdDropDownAdapter stationAdapter;

    //    private String regions[] = {"不限", "惠山区"};
    private List<String> regions = new ArrayList<>();
    private List<String> sewages = new ArrayList<>();
    ListView stationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private TextView sewageName;
    private TextView sewageControlId;
    private TextView sewageOperateNo;
    private TextView sewageTonnage;
    private TextView sewageNetState;
    private TextView equiptState1;
    private TextView equiptState2;
    private TextView equiptState3;
    private TextView equiptState4;
    private TextView equiptState5;
    private TextView equiptState6;
    private TextView dailyWater;
    private TextView monthWater;
    private TextView totalWater;
    private TextView waterPH;
    private TextView waterORP;
    private TextView waterDO;
    private TextView waterCOD;
    private TextView waterNH3N;
    private TextView waterP;
    private TextView waterT;
    private TextView waterSS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_monitor_sewage);
        ButterKnife.bind(this);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initView();
    }

    @Override
    protected MonitorPresenter createPresenter() {
        return new MonitorPresenter(this);
    }

    private void initView() {
        scrollView.setVisibility(View.INVISIBLE);
        //init toolbar
        toolbar.setTitle("站点监控");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //init station menu
        stationView = new ListView(this);
        sewageName = (TextView) findViewById(R.id.sewageName);
        sewageControlId = (TextView) findViewById(R.id.sewageControlId);
        sewageOperateNo = (TextView) findViewById(R.id.sewageOperateNo);
        sewageTonnage = (TextView) findViewById(R.id.sewageTonnage);
        sewageNetState = (TextView) findViewById(R.id.sewageNetState);
        equiptState1 = (TextView) findViewById(R.id.equiptState1);
        equiptState2 = (TextView) findViewById(R.id.equiptState2);
        equiptState3 = (TextView) findViewById(R.id.equiptState3);
        equiptState4 = (TextView) findViewById(R.id.equiptState4);
        equiptState5 = (TextView) findViewById(R.id.equiptState5);
        equiptState6 = (TextView) findViewById(R.id.equiptState6);
        dailyWater = (TextView) findViewById(R.id.dailyWater);
        monthWater = (TextView) findViewById(R.id.monthWater);
        totalWater = (TextView) findViewById(R.id.totalWater);
        waterPH = (TextView) findViewById(R.id.waterPH);
        waterORP = (TextView) findViewById(R.id.waterORP);
        waterDO = (TextView) findViewById(R.id.waterDO);
        waterCOD = (TextView) findViewById(R.id.waterCOD);
        waterNH3N = (TextView) findViewById(R.id.waterNH3N);
        waterP = (TextView) findViewById(R.id.waterP);
        waterT = (TextView) findViewById(R.id.waterT);
        waterSS = (TextView) findViewById(R.id.waterSS);
        //获取区域节点
        mvpPresenter.getAllAreas();
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
    public void getDataSuccess(Object object, DataTypeEnum dataTypeEnum) {
        if (object instanceof AreaListBean) {
            Log.d("MonitorSewageActiviy", "AreaListBean " + object.toString());
            AreaListBean areaListBean = (AreaListBean) object;
            //init region menu
            final List<Integer> regionIds = new ArrayList<>();
            if (areaListBean != null && areaListBean.getObject().size() > 0) {
                for (AreaListBean.AreaBean areaBean : areaListBean.getObject()) {
                    regions.add(areaBean.getName());
                    regionIds.add(areaBean.getId());
                }
            }
            ListView regionView = new ListView(this);
            regionAdapter = new GirdDropDownAdapter(this, regions);
            regionView.setDividerHeight(0);
            regionView.setAdapter(regionAdapter);
            popupViews.add(regionView);
            stationView.setDividerHeight(0);
            stationAdapter = new GirdDropDownAdapter(this, sewages);
            stationView.setAdapter(stationAdapter);
            //init popupViews
            popupViews.add(stationView);
            headers = new String[]{"请选择区县", "请选择站点"};

            //init context view
            TextView contentView = new TextView(this);
            contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            contentView.setGravity(Gravity.CENTER);
            contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            //init dropDownView
            if (mDropDownMenu != null) {
                mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
            }

            regionView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("MonitorSewageActiviy", "onItemClick " + position);
                    Log.d("MonitorSewageActiviy", "onItemClick-regions " + regions.get(position));
                    regionAdapter.setCheckItem(position);
                    mDropDownMenu.setTabText(regions.get(position));
                    //根据区域获取站点
                    mvpPresenter.getSewages(true, regionIds.get(position));
                    mDropDownMenu.closeMenu();
                }
            });
        } else if (object instanceof SewageListBean) {
            final SewageListBean sewageListBean = (SewageListBean) object;
            Log.d("MonitorSewageActiviy", "SewageListBean " + object.toString());
            final List<Integer> sewageIds = new ArrayList<>();
            if (sewageListBean != null && sewageListBean.getObject().size() > 0) {
                sewages.clear();
                for (SewageListBean.ObjectBean sewageBean : sewageListBean.getObject()) {
                    sewages.add(sewageBean.getName());
                    sewageIds.add(sewageBean.getId());
                }
            }

            //init context view
            TextView contentView = new TextView(this);
            contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            contentView.setGravity(Gravity.CENTER);
            contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            //init dropDownView
            if (mDropDownMenu != null) {
                mDropDownMenu.refreshDrawableState();
//                mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
            }
            stationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    stationAdapter.setCheckItem(position);
                    mDropDownMenu.setTabText(sewages.get(position));
                    mDropDownMenu.closeMenu();
                    mvpPresenter.getSewageMonitor(sewageIds.get(position));
                }
            });
        }else if(object instanceof SewageMonitorBean){
            final SewageMonitorBean sewageMonitorBean = (SewageMonitorBean) object;
            Log.d("MonitorSewageActiviy", "sewageMonitorBean " + object.toString());
            if(sewageMonitorBean!=null && sewageMonitorBean.getSewage()!=null){
                    scrollView.setVisibility(View.VISIBLE);
                    //区县、站点名称、运营编号、控制ID、设计吨位、网络状态（在线/离线）
                    sewageName.setText("站点名称: "+sewageMonitorBean.getSewage().getName());
                    sewageOperateNo.setText("运营编号: "+sewageMonitorBean.getSewage().getOperationNum());
                    sewageControlId.setText("控制ID: "+sewageMonitorBean.getSewage().getControlId());
                    sewageTonnage.setText("设计吨位: "+sewageMonitorBean.getSewage().getTonnage());
                    Log.d("MonitorSewageActiviy","TestingTime:"+sewageMonitorBean.getTestingTime());
                    long diffDay = CommonUtil.diffDay(new Date(), CommonUtil.stamp2Date(sewageMonitorBean.getTestingTime()));
                    Log.d("MonitorSewageActiviy","diffDay:"+diffDay);
                    if(diffDay>=0 && diffDay<3){
                        sewageNetState.setText("网络状态: 在线");
                    }else {
                        sewageNetState.setText("网络状态: 离线");
                    }

    //                设备状态：曝气泵、潜污泵、回流泵、出水泵、加药泵、提升泵
                    if(sewageMonitorBean.getEquipName()!=null&&sewageMonitorBean.getEquipName().size()>0){
                        equiptState1.setText(sewageMonitorBean.getEquipName().get(0));
                        equiptState2.setText(sewageMonitorBean.getEquipName().get(1));
                        equiptState3.setText(sewageMonitorBean.getEquipName().get(2));
                        equiptState4.setText(sewageMonitorBean.getEquipName().get(3));
                        equiptState5.setText(sewageMonitorBean.getEquipName().get(4));
                        equiptState6.setText(sewageMonitorBean.getEquipName().get(5));
                    }
    //                水量分析：日处理水量、月处理水量、表显累计水量
                    if(sewageMonitorBean.getDailyWater()!=null){
                        dailyWater.setText("日处理水量: " + sewageMonitorBean.getDailyWater());
                    }else{
                        dailyWater.setText("日处理水量: 空");
                    }
                    if(sewageMonitorBean.getMonthWater()!=null){
                        monthWater.setText("月处理水量: " + sewageMonitorBean.getMonthWater());
                    }else{
                        monthWater.setText("月处理水量: 空");
                    }
                    if(sewageMonitorBean.getTotalWater()!=null){
                        totalWater.setText("表显累计水量: " + sewageMonitorBean.getTotalWater());
                    }else{
                        totalWater.setText("表显累计水量: 空");
                    }
    //                水质监测：PH、ORP、DO、T、COD、NH3-N、P、SS
                    if(sewageMonitorBean.getWaterDetectPh()!=null){
                        waterPH.setText("PH: " + sewageMonitorBean.getWaterDetectPh());
                    }else {
                        waterPH.setText("PH: 空");
                    }
                    if(sewageMonitorBean.getWaterDetectOrp()!=null){
                        waterORP.setText("ORP: " + sewageMonitorBean.getWaterDetectOrp());
                    }else {
                        waterORP.setText("ORP: 空");
                    }
                    if(sewageMonitorBean.getWaterDetectDo()!=null){
                        waterDO.setText("DO: " + sewageMonitorBean.getWaterDetectDo());
                    }else {
                        waterDO.setText("DO: 空");
                    }
                    if(sewageMonitorBean.getWaterDetectT()!=null){
                        waterT.setText("T: " + sewageMonitorBean.getWaterDetectT());
                    }else {
                        waterT.setText("T: 空");
                    }
                    if(sewageMonitorBean.getWaterDetectCod()!=null){
                        waterCOD.setText("COD: " + sewageMonitorBean.getWaterDetectCod());
                    }else {
                        waterCOD.setText("COD: 空");
                    }
                    if(sewageMonitorBean.getWaterDetectNh3n()!=null){
                        waterNH3N.setText("NH3-N: " + sewageMonitorBean.getWaterDetectNh3n());
                    }else {
                        waterNH3N.setText("NH3-N: 空");
                    }
                    if(sewageMonitorBean.getWaterDetectP()!=null){
                        waterP.setText("P: " + sewageMonitorBean.getWaterDetectP());
                    }else {
                        waterP.setText("P: 空");
                    }
                    if(sewageMonitorBean.getWaterDetectSs()!=null){
                        waterSS.setText("SS: " + sewageMonitorBean.getWaterDetectSs());
                    }else {
                        waterSS.setText("SS: 空");
                    }
            }else {
                scrollView.setVisibility(View.INVISIBLE);
                toastShow("站点监控信息异常");
            }
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }

    @Override
    public void onRefresh() {
        sewageName.setText("站点" + sewageName.getText().toString() + "-已删除");
        mDropDownMenu.refreshDrawableState();
    }
}