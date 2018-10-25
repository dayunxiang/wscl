package com.tmxk.wscl.android.adpter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.bean.MenuItem;
import com.tmxk.wscl.android.util.JsonDataUtil;

import java.util.ArrayList;
import java.util.List;

public class NavPagerAdapter extends PagerAdapter {

    private List<MenuItem> mItems = new ArrayList<>();
    private Context context;

    public NavPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.layout_page, container, false);
        ListView listView = view.findViewById(R.id.listView);
        MenuListAdapter menuListAdapter = new MenuListAdapter(context, JsonDataUtil.loadMenuItems(context, position));
        listView.setAdapter(menuListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public String getPageTitle(int position) {
        return mItems.get(position).name;
    }

    public MenuItem getColorItem(int position) {
        return mItems.get(position);
    }

    public void addAll(List<MenuItem> items) {
        mItems = new ArrayList<>(items);
    }
}
