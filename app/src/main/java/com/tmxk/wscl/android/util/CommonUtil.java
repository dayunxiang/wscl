package com.tmxk.wscl.android.util;

import android.annotation.SuppressLint;
import android.util.Log;

import com.tmxk.wscl.android.ui.HomeActivity;
import com.tmxk.wscl.android.ui.UserInfoActivity;
import com.tmxk.wscl.android.ui.UserLoginLogActivity;
import com.tmxk.wscl.android.ui.UserManageActivity;
import com.tmxk.wscl.android.ui.UserPwdActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommonUtil {
    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String dateToStr(Date date) {
        //这个是你要转成后的时间的格式
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 时间戳转换成时间
        return sdf.format(date);
    }

    public static String stampToStr(long timeStamp) {
        //这个是你要转成后的时间的格式
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(timeStamp));
    }

    public static Class<?> getClassById(String classKey) {
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put("nav0_0", UserInfoActivity.class);
        classMap.put("nav0_1", UserPwdActivity.class);
        classMap.put("nav0_2", UserManageActivity.class);
        classMap.put("nav0_3", UserLoginLogActivity.class);
        if (classKey.contains(classKey)) {
            return classMap.get(classKey);
        }
        return null;
    }

    public static String getTime(Date date) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}