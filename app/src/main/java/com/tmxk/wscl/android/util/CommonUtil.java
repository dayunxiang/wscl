package com.tmxk.wscl.android.util;

import android.annotation.SuppressLint;

import com.tmxk.wscl.android.ui.device.DeviceDocActivity;
import com.tmxk.wscl.android.ui.device.DeviceDocCreateActivity;
import com.tmxk.wscl.android.ui.device.SewageArchiveActivity;
import com.tmxk.wscl.android.ui.device.SewageCreateActivity;
import com.tmxk.wscl.android.ui.user.AdminActivity;
import com.tmxk.wscl.android.ui.user.UserInfoActivity;
import com.tmxk.wscl.android.ui.user.UserLoginLogActivity;
import com.tmxk.wscl.android.ui.user.UserManageActivity;
import com.tmxk.wscl.android.ui.user.UserPwdActivity;

import java.text.ParseException;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.");
        // 时间戳转换成时间
        return sdf.format(date);
    }

    public static String stampToStr(long timeStamp) {
        //这个是你要转成后的时间的格式
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(timeStamp));
    }

    public static Date String2Date(String dateStr) throws ParseException {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(dateStr);
    }

    public static Class<?> getClassById(String classKey) {
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put("nav0_0", UserInfoActivity.class);
        classMap.put("nav0_1", UserPwdActivity.class);
        classMap.put("nav0_2", UserManageActivity.class);
        classMap.put("nav0_3", UserLoginLogActivity.class);

        classMap.put("nav1_0", SewageArchiveActivity.class);
//        classMap.put("nav1_2", DeviceDocCreateActivity.class);
        classMap.put("nav1_1", SewageCreateActivity.class);
        classMap.put("nav1_2", DeviceDocActivity.class);
        classMap.put("nav1_3", AdminActivity.class);

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