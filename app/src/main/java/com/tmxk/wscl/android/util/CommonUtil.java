package com.tmxk.wscl.android.util;

import android.annotation.SuppressLint;

import com.tmxk.wscl.android.ui.NullActivity;
import com.tmxk.wscl.android.ui.alert.AlertDataTransferActivity;
import com.tmxk.wscl.android.ui.alert.AlertPowerOffActivity;
import com.tmxk.wscl.android.ui.alert.AlertSewageActivity;
import com.tmxk.wscl.android.ui.alert.AlertWaterQulityActivity;
import com.tmxk.wscl.android.ui.device.DeviceDocActivity;
import com.tmxk.wscl.android.ui.device.SewageArchiveActivity;
import com.tmxk.wscl.android.ui.device.SewageCreateActivity;
import com.tmxk.wscl.android.ui.location.CarGpsActivity;
import com.tmxk.wscl.android.ui.location.GpsActivity;
import com.tmxk.wscl.android.ui.location.SewageGpsActivity;
import com.tmxk.wscl.android.ui.monitor.MonitorSewageActivity;
import com.tmxk.wscl.android.ui.operates.AssignOrderActivity;
import com.tmxk.wscl.android.ui.operates.DealOrderActivity;
import com.tmxk.wscl.android.ui.operates.DeviceReplaceActivity;
import com.tmxk.wscl.android.ui.operates.DeviceReplcaeCreateActivity;
import com.tmxk.wscl.android.ui.operates.EquipRepairRecordActivity;
import com.tmxk.wscl.android.ui.operates.GatherProblemCreateActivity;
import com.tmxk.wscl.android.ui.operates.InspectionInfoActivity;
import com.tmxk.wscl.android.ui.operates.RepairDealActivity;
import com.tmxk.wscl.android.ui.operates.RepairmentCreateActivity;
import com.tmxk.wscl.android.ui.user.AdminActivity;
import com.tmxk.wscl.android.ui.user.UserInfoActivity;
import com.tmxk.wscl.android.ui.user.UserLoginLogActivity;
import com.tmxk.wscl.android.ui.user.UserManageActivity;
import com.tmxk.wscl.android.ui.user.UserPwdActivity;
import com.tmxk.wscl.android.ui.water.WaterAnalysisMonthActivity;
import com.tmxk.wscl.android.ui.water.WaterAnalysisYearActivity;
import com.tmxk.wscl.android.ui.water.WaterUpByMonthActivity;
import com.tmxk.wscl.android.ui.water.WaterUpBySewageActivity;
import com.tmxk.wscl.android.ui.water.WaterUpByYearActivity;
import com.tmxk.wscl.android.ui.water.testManager.WaterTestCreateActivity;
import com.tmxk.wscl.android.ui.water.testManager.WaterTestManagerActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class CommonUtil {
    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    /*
     * 将时间戳转换为时间
     */
    public static Date stamp2Date(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        long lt = new Long(s);
        return new Date(lt);
    }

    public static String dateToStr(Date date) {
        //这个是你要转成后的时间的格式
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        // 时间戳转换成时间
        return sdf.format(date);
    }

    public static String stampToStr(long timeStamp) {
        //这个是你要转成后的时间的格式
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sdf.format(new Date(timeStamp));
    }

    public static String stampToStr2(long timeStamp) {
        //这个是你要转成后的时间的格式
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sdf.format(new Date(timeStamp));
    }

    public static Date String2Date(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
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

        classMap.put("nav2_0", MonitorSewageActivity.class);
        classMap.put("nav2_1", GpsActivity.class);
        classMap.put("nav2_2", SewageGpsActivity.class);
        classMap.put("nav2_3", CarGpsActivity.class);

        classMap.put("nav3_0", AlertSewageActivity.class);
        classMap.put("nav3_1", AlertWaterQulityActivity.class);
        classMap.put("nav3_2", AlertPowerOffActivity.class);
        classMap.put("nav3_3", AlertDataTransferActivity.class);

        classMap.put("nav4_0", AssignOrderActivity.class);
        classMap.put("nav4_1", DealOrderActivity.class);
        classMap.put("nav4_2", DeviceReplcaeCreateActivity.class);
        classMap.put("nav4_3", NullActivity.class);
        classMap.put("nav4_4", InspectionInfoActivity.class);
        classMap.put("nav4_5", GatherProblemCreateActivity.class);
        classMap.put("nav4_6", RepairmentCreateActivity.class);
        classMap.put("nav4_7", RepairDealActivity.class);

        classMap.put("nav5_0", WaterTestCreateActivity.class);
        classMap.put("nav5_1", NullActivity.class);
        classMap.put("nav5_2", WaterTestManagerActivity.class);

        classMap.put("nav6_0", DeviceReplaceActivity.class);
        classMap.put("nav6_1", InspectionInfoActivity.class);
        classMap.put("nav6_2", EquipRepairRecordActivity.class);

        classMap.put("nav7_0", WaterAnalysisMonthActivity.class);
        classMap.put("nav7_1", WaterAnalysisYearActivity.class);
        classMap.put("nav7_2", WaterUpBySewageActivity.class);
        classMap.put("nav7_3", WaterUpByMonthActivity.class);
        classMap.put("nav7_4", WaterUpByYearActivity.class);
//        classMap.put("nav7_5", NullActivity.class);
//        classMap.put("nav7_6", NullActivity.class);

        classMap.put("nav8_0", NullActivity.class);
        classMap.put("nav8_1", NullActivity.class);
        classMap.put("nav8_2", NullActivity.class);
        classMap.put("nav8_3", NullActivity.class);
        classMap.put("nav8_4", NullActivity.class);
        classMap.put("nav8_5", NullActivity.class);
        classMap.put("nav8_6", NullActivity.class);
        classMap.put("nav8_7", NullActivity.class);
        classMap.put("nav8_8", NullActivity.class);

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

    public static String getTimeByDay(Date date) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String getTimeByMonth(Date date) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }

    public static String getTimeByYear(Date date) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    public static boolean isEmpty(String object) {
        if(object==null||("").equals(object)){
            return true;
        }
        return false;
    }

    public static long diffDay(Date d1,Date d2) {
        return (d2.getTime()-d1.getTime()+1000000)/(60*60*24*1000);
    }

    public static String generateFileName() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String timeStamp = sdf.format(new Date());
        return "/WSCL_" + timeStamp;
    }

    public static String generateFileName1() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String timeStamp = sdf.format(new Date());
        return "operate_" + timeStamp;
    }

    public static String generateDay() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sdf.format(new Date());
    }
}