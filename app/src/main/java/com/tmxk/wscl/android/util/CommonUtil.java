package com.tmxk.wscl.android.util;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 时间戳转换成时间
        return sdf.format(date);
    }

    public static String stampToStr(long timeStamp) {
        //这个是你要转成后的时间的格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(timeStamp));
    }
}