package com.tmxk.wscl.android.ui.location;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.adpter.GirdDropDownAdapter;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.CarGpsRecordResBean;
import com.tmxk.wscl.android.mvp.model.CarInfoResBean;
import com.tmxk.wscl.android.mvp.presenter.MonitorPresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.CommonUtil;
import com.tmxk.wscl.android.widget.DropDownMenuView;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;

public class CarGpsActivity extends MvpActivity<MonitorPresenter> implements SewageArchiveView {

    private FrameLayout parentView;
    private MapView gpsBmapView;
    private Toolbar toolbar;
    private Button btnFilter;
    private LinearLayout ll1;
    private TextView tv1;
    private DropDownMenu dropDownMenu;
    private LinearLayout ll2;
    private TextView tv3;
    private Button btnStartDate;
    private LinearLayout ll3;
    private TextView tv4;
    private Button btnEndDate;
    private Button btnSearch;
    private BaiduMap mBaiduMap;
    private DropDownMenuView dropDownMenuView;
    private TimePickerView pvTime;
    private int timerPickerPos = -1;
    private String headers[] = new String[]{"请选择车辆"};
    private List<View> popupViews = new ArrayList<>();
    private GirdDropDownAdapter carAdapter;
    private List<String> cars = new ArrayList<>();
    private static String CAR_NAME;
    private static boolean CALL_CAR_INFO=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_car_gps_record);
        ButterKnife.bind(this);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initView();
        initData();
        initTimePicker();
        //init toolbar
        toolbar.setTitle("车辆GPS定位");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }


    @Override
    protected MonitorPresenter createPresenter() {
        return new MonitorPresenter(this);
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

    private void initData() {
        mBaiduMap = gpsBmapView.getMap();
        mvpPresenter.getCarGpsRecentOnce();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CAR_NAME=null;
        CALL_CAR_INFO=true;
    }

    private void initView() {
        gpsBmapView = (MapView) findViewById(R.id.gpsBmapView);
        parentView = (FrameLayout) findViewById(R.id.parentView);
        gpsBmapView = (MapView) findViewById(R.id.gpsBmapView);
        toolbar = (Toolbar) findViewById(R.id.viewTop);
        btnFilter = (Button) findViewById(R.id.btnFilter);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        tv1 = (TextView) findViewById(R.id.tv1);
        dropDownMenu = (DropDownMenu) findViewById(R.id.dropDownMenu);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        tv3 = (TextView) findViewById(R.id.tv3);
        btnStartDate = (Button) findViewById(R.id.btnStartDate);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        tv4 = (TextView) findViewById(R.id.tv4);
        btnEndDate = (Button) findViewById(R.id.btnEndDate);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        dropDownMenuView = (DropDownMenuView) findViewById(R.id.dropDownMenuView);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void getDataSuccess(Object model, DataTypeEnum dataTypeEnum) {
        Log.d("CarGpsActivity", "dataTypeEnum:"+dataTypeEnum);
        if (DataTypeEnum.TYPE02.equals(dataTypeEnum) && (model instanceof CarGpsRecordResBean)) {
            Log.d("CarGpsActivity", "车辆");
            CarGpsRecordResBean gpsRecordResBean = (CarGpsRecordResBean) model;
            if (gpsRecordResBean != null && gpsRecordResBean.getObject().size() > 0) {
                for (CarGpsRecordResBean.ObjectBean obj : gpsRecordResBean.getObject()) {
                    //在地图上添加Marker，并显示
                    Marker marker = (Marker) mBaiduMap.addOverlay(positionForMap(obj.getCoordinateX(), obj.getCoordinateY(), obj.getId(), obj.getCarInfo().getCarNo()));
                    Bundle mBundle = new Bundle();
                    mBundle.putInt("id", obj.getId());
                    mBundle.putString("name", obj.getCarInfo().getCarNo());
                    mBundle.putDouble("xVal", obj.getCoordinateX());
                    mBundle.putDouble("yVal", obj.getCoordinateY());
                    marker.setExtraInfo(mBundle);
                    mBaiduMap.setOnMarkerClickListener(onMarkerClickListener);
                }
            }
        }else if (DataTypeEnum.TYPE01.equals(dataTypeEnum)&&(model instanceof CarGpsRecordResBean )) {
            CarGpsRecordResBean gpsRecordResBean = (CarGpsRecordResBean) model;
            Log.d("CarGpsActivity", "轨迹");
            mBaiduMap.clear();
            if (gpsRecordResBean != null && gpsRecordResBean.getObject().size() > 1) {
                //画轨迹
                Log.d("CarGpsActivity", "画轨迹 点数:"+gpsRecordResBean.getObject().size());
                List<LatLng> points = new ArrayList<>();
                for(int i = gpsRecordResBean.getObject().size()-1;i>=0;i--){
                    //构建折线点坐标
                    points.add(new LatLng(gpsRecordResBean.getObject().get(i).getCoordinateX(),
                            gpsRecordResBean.getObject().get(i).getCoordinateY()));
                }
                //添加纹理图片

                BitmapDescriptor mRedTexture = BitmapDescriptorFactory.fromResource(R.drawable.icon_road_blue_arrow);
//                BitmapDescriptor mBlueTexture = BitmapDescriptorFactory.fromResource(R.drawable.icon_road_green_arrow);
//                BitmapDescriptor mGreenTexture = BitmapDescriptorFactory.fromResource(R.drawable.icon_road_red_arrow);
                List<BitmapDescriptor> textureList = new ArrayList<>();
                textureList.add(mRedTexture);
//                textureList.add(mBlueTexture);
//                textureList.add(mGreenTexture);

                //添加纹理索引
                List<Integer> indexList = new ArrayList<>();
                indexList.add(0);
                indexList.add(1);
                indexList.add(2);
                //设置折线的属性
                OverlayOptions mOverlayOptions = new PolylineOptions()
                        .width(20)
                        .dottedLine(true)
                        .points(points)
                        .customTextureList(textureList)
                        .textureIndex(indexList);

                //在地图上绘制折线
                //mPloyline 折线对象
                Overlay mPolyline = mBaiduMap.addOverlay(mOverlayOptions);
            }else{
                Toast.makeText(this, "车辆GPS数据较少，无法绘制轨迹!", Toast.LENGTH_SHORT).show();
            }
        }else if(model instanceof CarInfoResBean){
            CALL_CAR_INFO=false;
            headers = new String[]{"请选择车辆"};
            popupViews = new ArrayList<>();
            CarInfoResBean carInfoResBean = (CarInfoResBean) model;
            if(carInfoResBean!=null&&carInfoResBean.getObject().size()>0){
                for(CarInfoResBean.ObjectBean carBean:carInfoResBean.getObject()){
                    cars.add(carBean.getCarNo());
                }
            }
            ListView carView = new ListView(this);
            carAdapter = new GirdDropDownAdapter(this, cars);
            carView.setDividerHeight(0);
            carView.setAdapter(carAdapter);
            popupViews.add(carView);

            //init context view
            TextView contentView = new TextView(this);
            contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            contentView.setGravity(Gravity.CENTER);
            contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            //init dropDownView
            if (dropDownMenu != null) {
                dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
            }

            carView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("CarGpsActivity","onItemClick "+position);
                    carAdapter.setCheckItem(position);
                    dropDownMenu.setTabText(cars.get(position));
                    CAR_NAME = cars.get(position);
                    dropDownMenu.closeMenu();
                }
            });
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
            Log.d("CarGpsActivity", "Marker-id:" + id);
            Button button = new Button(CarGpsActivity.this);
            button.setBackgroundResource(R.drawable.popup);
            button.setText(name);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                }
//            });
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

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnFilter:
                if (!dropDownMenuView.isOpen()) {
                    dropDownMenuView.open();
                    if(CALL_CAR_INFO){
                        mvpPresenter.getAllCarInfo();
                    }
                } else {
                    dropDownMenuView.close();
                    hideInputMethod();
                }
                break;
            case R.id.btnSearch:
                if (dropDownMenuView.isOpen()) {
                    //搜索车辆轨迹
                    Log.d("CarGpsActivity","carName:"+CAR_NAME);
                    Log.d("CarGpsActivity","btnStartDate:"+btnStartDate.getText().toString().trim());
                    Log.d("CarGpsActivity","btnEndDate:"+btnEndDate.getText().toString().trim());
                    mvpPresenter.getCarGpsByIdAndPeriod(CAR_NAME, btnStartDate.getText().toString().trim(), btnEndDate.getText().toString().trim());
                    dropDownMenuView.close();
                    hideInputMethod();
                }
                break;
            case R.id.btnStartDate:
                if (pvTime != null) {
                    timerPickerPos = 0;
                    pvTime.show(view);
                }
                break;
            case R.id.btnEndDate:
                if (pvTime != null) {
                    timerPickerPos = 1;
                    pvTime.show(view);
                }
                break;
        }
    }


    private void initTimePicker() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (timerPickerPos == 0) {
                    btnStartDate.setText(CommonUtil.getTime(date));
                } else if (timerPickerPos == 1) {
                    btnEndDate.setText(CommonUtil.getTime(date));
                }
                timerPickerPos = -1;
                Log.i("pvTime", "onTimeSelect");
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setOutSideCancelable(true)
                .setCancelColor(Color.parseColor("#333333"))
                .setSubmitColor(Color.parseColor("#333333"))
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true)
                .build();
        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);
                dialogWindow.setGravity(Gravity.BOTTOM);
            }
        }
    }

    private void hideInputMethod() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && inputMethodManager.isActive()) {
            inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onBackPressed() {
        if (dropDownMenu.isShowing()) {
            dropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }
}