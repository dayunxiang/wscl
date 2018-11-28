package com.tmxk.wscl.android.util;

import android.content.Context;

import com.tmxk.wscl.android.entity.MenuItemEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonDataUtil {

    private JsonDataUtil() {

    }

    public static List<MenuItemEntity> loadNavItems(Context context) {
        List<MenuItemEntity> items = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context, "nav.json"));
            Iterator<String> keys = obj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject value = obj.getJSONObject(key);
                MenuItemEntity menuItemEntity = new MenuItemEntity();
                menuItemEntity.name = value.getString("name");
                menuItemEntity.id = value.getInt("id");
                items.add(menuItemEntity);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static List<MenuItemEntity> loadMenuItems(Context context, int position) {
        List<MenuItemEntity> items = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context, "menu.json"));
            if (obj.has("menu_" + position)) {
                JSONArray arrays = obj.getJSONArray("menu_" + position);
                for (int i = 0; i < arrays.length(); i++) {
                    JSONObject subObj = arrays.getJSONObject(i);
                    MenuItemEntity menuItemEntity = new MenuItemEntity();
                    menuItemEntity.name = subObj.getString("name");
                    menuItemEntity.id = subObj.getInt("id");
                    items.add(menuItemEntity);
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
