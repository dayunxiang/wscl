package com.tmxk.wscl.android.application;

import android.app.Application;

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
        if (isDebuggerConnected()) {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
