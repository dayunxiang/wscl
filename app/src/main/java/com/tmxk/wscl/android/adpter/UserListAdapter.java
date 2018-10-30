package com.tmxk.wscl.android.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.mvp.model.UserBean;

import java.util.List;

public class UserListAdapter extends BaseAdapter {
    private final List<UserBean> userBeanList;
    private final LayoutInflater mInflater;

    public UserListAdapter(Context context, List<UserBean> userBeanList) {
        this.userBeanList = userBeanList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return userBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.simple_list_item_2, null);
            holder = new ViewHolder();
            holder.tvUserName = convertView.findViewById(R.id.username);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvUserName.setText(userBeanList.get(position).getUserName());
        return convertView;
    }

    private static class ViewHolder {
        private TextView tvUserName;
    }
}