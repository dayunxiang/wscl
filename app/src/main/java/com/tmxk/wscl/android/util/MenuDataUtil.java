package com.tmxk.wscl.android.util;

import android.content.Context;

import com.tmxk.wscl.android.bean.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MenuDataUtil {

    private MenuDataUtil() {

    }

    public static List<MenuItem> loadNavigateItems(Context context) {
        List<MenuItem> items = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context, "navigate.json"));
            Iterator<String> keys = obj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject value = obj.getJSONObject(key);
                MenuItem menuItem = new MenuItem();
                menuItem.name = value.getString("name");
                menuItem.id = value.getInt("id");
                items.add(menuItem);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static List<MenuItem> loadMenuItems(Context context, int position) {
        List<MenuItem> items = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context, "menu.json"));
            if (obj.has("menu_" + position)) {
                JSONArray arrays = obj.getJSONArray("menu_" + position);
                for (int i = 0; i < arrays.length(); i++) {
                    JSONObject subObj = arrays.getJSONObject(i);
                    MenuItem menuItem = new MenuItem();
                    menuItem.name = subObj.getString("name");
                    menuItem.id = subObj.getInt("id");
                    items.add(menuItem);
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return items;
    }

    private static String loadJSONFromAsset(Context context, String filename) throws IOException {
        InputStream is = context.getAssets().open(filename);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        return new String(buffer, "UTF-8");
    }
}
