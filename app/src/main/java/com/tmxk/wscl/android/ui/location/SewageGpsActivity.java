package com.tmxk.wscl.android.ui.location;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.SewageMonitorBean;
import com.tmxk.wscl.android.mvp.presenter.MonitorPresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.CommonUtil;
import com.tmxk.wscl.android.util.MyLocationListener;

import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SewageGpsActivity extends MvpActivity<MonitorPresenter> implements SewageArchiveView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private MapView gpsBmapView;
    private BaiduMap mBaiduMap;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private ScrollView scrollView;
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
        setContentView(R.layout.activity_sewage_gps_monitor);
        ButterKnife.bind(this);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        //init toolbar
        toolbar.setTitle("站点GPS定位");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        gpsBmapView = (MapView) findViewById(R.id.gpsBmapView);
//        initLocationOption();
        initData();
    }


    @Override
    protected MonitorPresenter createPresenter() {
        return new MonitorPresenter(this);
    }

    private void initData() {
        mBaiduMap = gpsBmapView.getMap();
        mvpPresenter.getAllSewages();
    }

    private OverlayOptions positionForMap(double var1, double var2, int id, String name) {

        Bundle mBundle = new Bundle();
        mBundle.putInt("id", id);
        //定义Maker坐标点
        LatLng point = new LatLng(var1, var2);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_gcoding);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions mTextOptions = new MarkerOptions().position(point).icon(bitmap).draggable(true).extraInfo(mBundle).title(name);

        //构建TextOptions对象
//        OverlayOptions mTextOptions = new TextOptions()
//                .text(name) //文字内容
//                .bgColor(0xAAFFFF00) //背景色
//                .fontSize(24) //字号
//                .fontColor(0xFFFF00FF) //文字颜色
//                .rotate(-30) //旋转角度
//                .position(point);
        return mTextOptions;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView(View view) {
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        sewageName = (TextView) view.findViewById(R.id.sewageName);
        sewageControlId = (TextView) view.findViewById(R.id.sewageControlId);
        sewageOperateNo = (TextView) view.findViewById(R.id.sewageOperateNo);
        sewageTonnage = (TextView) view.findViewById(R.id.sewageTonnage);
        sewageNetState = (TextView) view.findViewById(R.id.sewageNetState);
        equiptState1 = (TextView) view.findViewById(R.id.equiptState1);
        equiptState2 = (TextView) view.findViewById(R.id.equiptState2);
        equiptState3 = (TextView) view.findViewById(R.id.equiptState3);
        equiptState4 = (TextView) view.findViewById(R.id.equiptState4);
        equiptState5 = (TextView) view.findViewById(R.id.equiptState5);
        equiptState6 = (TextView) view.findViewById(R.id.equiptState6);
        dailyWater = (TextView) view.findViewById(R.id.dailyWater);
        monthWater = (TextView) view.findViewById(R.id.monthWater);
        totalWater = (TextView) view.findViewById(R.id.totalWater);
        waterPH = (TextView) view.findViewById(R.id.waterPH);
        waterORP = (TextView) view.findViewById(R.id.waterORP);
        waterDO = (TextView) view.findViewById(R.id.waterDO);
        waterCOD = (TextView) view.findViewById(R.id.waterCOD);
        waterNH3N = (TextView) view.findViewById(R.id.waterNH3N);
        waterP = (TextView) view.findViewById(R.id.waterP);
        waterT = (TextView) view.findViewById(R.id.waterT);
        waterSS = (TextView) view.findViewById(R.id.waterSS);
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
        locationOption.setScanSpan(1000);
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
        locationOption.setOpenAutoNotifyMode(3000, 1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        mLocationClient.setLocOption(locationOption);
//开始定位
        mLocationClient.start();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void getDataSuccess(Object object, DataTypeEnum dataTypeEnum) {
        if (object instanceof SewageListBean) {
            SewageListBean sewageListBean = (SewageListBean) object;
            if (sewageListBean != null && sewageListBean.getObject().size() > 0) {
                for (SewageListBean.ObjectBean obj : sewageListBean.getObject()) {
                    //在地图上添加Marker，并显示
                    Marker marker = (Marker) mBaiduMap.addOverlay(positionForMap(obj.getCoordinateX(), obj.getCoordinateY(), obj.getId(), obj.getShortTitle()));
                    Bundle mBundle = new Bundle();
                    mBundle.putInt("id", obj.getId());
                    mBundle.putString("name", obj.getShortTitle());
                    mBundle.putDouble("xVal", obj.getCoordinateX());
                    mBundle.putDouble("yVal", obj.getCoordinateY());
                    marker.setExtraInfo(mBundle);
                    mBaiduMap.setOnMarkerClickListener(onMarkerClickListener);
                }
            }
        } else if (object instanceof SewageMonitorBean) {
            final SewageMonitorBean sewageMonitorBean = (SewageMonitorBean) object;
            Log.d("MonitorSewageActiviy", "sewageMonitorBean " + object.toString());
            if (sewageMonitorBean != null && sewageMonitorBean.getSewage() != null) {
                Log.d("SewageGpsActivity", "sewageMonitorBean:" + sewageMonitorBean.getSewage().getName());
                showDialog(SewageGpsActivity.this,sewageMonitorBean);
            } else {
                toastShow("站点监控信息异常");
            }
        }
    }


    BaiduMap.OnMarkerClickListener onMarkerClickListener = new BaiduMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            Bundle bundle = marker.getExtraInfo();
            final int id = bundle.getInt("id");
            String name = bundle.getString("name");
            double xVal = bundle.getDouble("xVal");
            double yVal = bundle.getDouble("yVal");
            Log.d("SewageGpsActivity", "Marker-id:" + id);
            Button button = new Button(SewageGpsActivity.this);
            button.setBackgroundResource(R.drawable.popup);
            button.setText(name);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mvpPresenter.getSewageMonitor(id);
                }
            });
            //定义用于显示该InfoWindow的坐标点
            LatLng pt = new LatLng(xVal, yVal);
            //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
            InfoWindow mInfoWindow = new InfoWindow(button, pt, -47);
            //在地图上显示 InfoWindow
            mBaiduMap.showInfoWindow(mInfoWindow);
            return false;
        }
    };

    @Override
    public void getDataFail(String msg) {

    }

    private void showDialog(Context context, SewageMonitorBean sewageMonitorBean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = View.inflate(context, R.layout.dialog_sewage_monitor, null);   // 布局文件，自定义
        builder.setIcon(R.drawable.icon_geo);//设置对话框icon
        builder.setTitle("站点监控");
        initView(view);
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


        AlertDialog dialog = builder.create();
        dialog.setView(view);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();//关闭对话框
            }
        });
        dialog.show();
        Window dialogWindow = dialog.getWindow();//获取window对象
        dialogWindow.setGravity(Gravity.TOP);//设置对话框位置
        dialogWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
    }
}