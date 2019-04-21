package com.tmxk.wscl.android.application;

import android.app.Application;
import android.os.StrictMode;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.tmxk.wscl.android.mvp.model.UserBean;

import lombok.Getter;
import lombok.Setter;

import static android.os.Debug.isDebuggerConnected;

public class MainApplication extends Application {
    @Getter @Setter
    private UserBean userBean;
    @Override
    public void onCreate() {
        super.onCreate();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(getApplicationContext());
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        if (isDebuggerConnected()) {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }
}
