package com.tmxk.wscl.android.ui.location;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.CreateCarGpsBySysuserBean;
import com.tmxk.wscl.android.mvp.model.GpsRecordBean;
import com.tmxk.wscl.android.mvp.model.UserBean;
import com.tmxk.wscl.android.mvp.presenter.MonitorPresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.BaseActivity;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.Const;
import com.tmxk.wscl.android.util.MyLocationListener;

import java.util.Objects;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GpsActivity extends MvpActivity<MonitorPresenter> implements SewageArchiveView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private MapView gpsBmapView;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private BaiduMap mBaiduMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_gps_location);
        ButterKnife.bind(this);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        //init toolbar
        toolbar.setTitle("GPS定位");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        initView();
        initLocationOption();
        initData();
    }


    @Override
    protected MonitorPresenter createPresenter() {
        return new MonitorPresenter(this);
    }

    private void initData() {
        Timer timer = new Timer(true);
        timer.schedule(new java.util.TimerTask() {
            public void run() {
                //要操作的方法
                CreateCarGpsBySysuserBean gpsRecordBean = new CreateCarGpsBySysuserBean();
                gpsRecordBean.setCoordinateX(Const.LATITUDE);
                gpsRecordBean.setCoordinateY(Const.LONGITUDE);
                gpsRecordBean.setSysuserId(Const.OPERATE_USER_ID);
                gpsRecordBean.setActuallyDriver(Const.OPERATE_USER_NAME);
                Log.d("GpsActivity","LATITUDE:"+Const.LATITUDE+"");
                Log.d("GpsActivity","LONGITUDE:"+Const.LONGITUDE+"");
                Log.d("GpsActivity","USERID:"+Const.OPERATE_USER_ID+"");
                Log.d("GpsActivity","USERNAME:"+Const.OPERATE_USER_NAME+"");
                mvpPresenter.createGpsRecordBySysuser(gpsRecordBean);
            }
        }, 0, 5*60*1000);
        mBaiduMap = gpsBmapView.getMap();
        //定义Maker坐标点
        LatLng point = new LatLng(Const.LATITUDE, Const.LONGITUDE);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_gcoding);
        //构建MarkerOption，用于在地图上添加Marker
        mBaiduMap.addOverlay(new MarkerOptions().position(point).icon(bitmap).draggable(true).title(Const.OPERATE_USER_NAME));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        this.onPause();
    }

    private void initView() {
        gpsBmapView = (MapView) findViewById(R.id.gpsBmapView);
    }

    /**
     * 初始化定位参数配置
     */

    private void initLocationOption() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数

//声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("gcj02");
//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        locationOption.setScanSpan(30000);
//可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true);
//可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true);
//可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(false);
//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locationOption.setLocationNotify(true);
//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true);
//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true);
//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(true);
//可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false);
//可选，默认false，设置是否开启Gps定位
        locationOption.setOpenGps(true);
//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false);
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode();
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        locationOption.setOpenAutoNotifyMode(3000,1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        mLocationClient.setLocOption(locationOption);
//开始定位
        mLocationClient.start();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void getDataSuccess(Object model, DataTypeEnum dataTypeEnum) {

    }

    @Override
    public void getDataFail(String msg) {

    }
}