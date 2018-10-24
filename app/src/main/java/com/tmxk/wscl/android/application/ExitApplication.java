/**
 * author：wjf
 * date：2018/10/21
 * desc：manage all activities
 */
package com.tmxk.wscl.android.application;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

public class ExitApplication extends Application {

    private List activityList = new LinkedList();
    private static ExitApplication instance;

    private ExitApplication() {
    }

    //单例模式中获取唯一的ExitApplication实例
    public static ExitApplication getInstance() {
        if (null == instance) {
            instance = new ExitApplication();
        }
        return instance;

    }

    //添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void exit() {
        try {
            for (Object activity : activityList) {
                ((Activity) activity).finish();
            }
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}