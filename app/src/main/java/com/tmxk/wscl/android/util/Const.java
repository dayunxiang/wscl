package com.tmxk.wscl.android.util;

import java.util.HashMap;
import java.util.Map;

public class Const {
    public static double LONGITUDE = 0;//经度
    public static double LATITUDE = 0;//纬度
    public static int OPERATE_USER_ID = 0;//操作用户id
    public static String OPERATE_USER_NAME = "";//操作用户名
    public static final int CAMERA_WITH_DATA = 0x01;  //跳转相机并拍照返回
    public static final int ALBUM_CHOOSE_IMG = 0x02; //跳转相册获取照片返回
    public static final int FLAG_MODIFY_FINISH = 0x03; //裁剪照片完成
    public static Map<String, String> INSPECTION_ENTRY = new HashMap<>(); //巡检录入entry
    public static boolean IS_GPS_COLLECTED = true;//是否已记录gps
}
