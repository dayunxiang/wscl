package com.tmxk.wscl.android.application;

import android.app.Application;

import static android.os.Debug.isDebuggerConnected;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebuggerConnected()) {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
