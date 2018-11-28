package com.tmxk.wscl.android.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.entity.MenuItemEntity;

import java.util.List;

public class MenuListAdapter extends BaseAdapter {
    private final List<MenuItemEntity> list;
    private final LayoutInflater mInflater;

    public MenuListAdapter(Context context, List<MenuItemEntity> list) {
        this.list = list;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position).name;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.simple_list_item_1, null);
            holder = new ViewHolder();
            holder.tvName = convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(getItem(position));
        return convertView;
    }

    private static class ViewHolder {
        private TextView tvName;
    }
}