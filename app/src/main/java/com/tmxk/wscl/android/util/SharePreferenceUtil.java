package com.tmxk.wscl.android.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * author：wang jie feng
 * date：2018/10/21
 * desc：SharePreference读写
 */
public class SharePreferenceUtil {
    public static final String SHARED_PREFERENCE_NAME = "zhiliao";
    SharedPreferences mSharedPreferences;

    public SharePreferenceUtil(Context context) {
        mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void savePreferences(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void saveBooleanPreferences(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void saveArrayPreferences(String key, String value) {
        String longHistory = mSharedPreferences.getString(key, "");
        if (!longHistory.contains(value + " ")) {
            StringBuilder sb = new StringBuilder(longHistory);
            sb.insert(0, value + " ");
            mSharedPreferences.edit().putString(key, sb.toString()).apply();
        }
    }

    public ArrayList<String> getArrayPreferences(String key) {
        ArrayList<String> list = new ArrayList<>();
        if (!mSharedPreferences.getString(key, "").equals("")) {
            String longHistory = mSharedPreferences.getString(key, null);
            String[] hisArrays = longHistory.split(" ");
            for (String a : hisArrays) {
                list.add(a);
            }
        }
        return list;
    }

    public boolean getBooleanPreferences(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public String getPreferences(String key) {
        return mSharedPreferences.getString(key, "");
    }

    public void deletePreferences(String key) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public void deleteArrayPreferences(String key, String value) {
        ArrayList<String> list = new ArrayList<>();
        if (!mSharedPreferences.getString(key, "").equals("")) {
            String longHistory = mSharedPreferences.getString(key, null);
            String[] hisArrays = longHistory.split(" ");
            for (String a : hisArrays) {
                list.add(a);
            }
        }
        if (list.contains(value)) {
            list.remove(value);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                sb.insert(0, list.get(i) + " ");
            }
            mSharedPreferences.edit().putString(key, sb.toString()).apply();
        }
    }
}
