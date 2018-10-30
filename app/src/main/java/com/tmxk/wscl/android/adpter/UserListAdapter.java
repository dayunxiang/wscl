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

import java.io.Serializable;
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
        return userBeanList.get(position);
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
            holder.tvUserName = convertView.findViewById(R.id.userName);
            holder.tvLoginName = convertView.findViewById(R.id.loginName);
            holder.tvDepartment = convertView.findViewById(R.id.department);
            holder.tvTelephone = convertView.findViewById(R.id.telephone);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        UserBean userBean = userBeanList.get(position);
        if (userBean != null) {
            holder.tvLoginName.setText("用户名：".concat(userBean.getLoginName()));
            holder.tvUserName.setText("姓名：".concat(userBean.getUserName()));
            holder.tvDepartment.setText("部门：".concat(userBean.getDepartment()));
            holder.tvTelephone.setText("联系电话：".concat(userBean.getTelephone()));
        }
        return convertView;
    }

    public void updateUserList(UserBean userBean, int position) {
        if (userBean != null && position != -1 && userBeanList.size() > position) {
            userBeanList.set(position, userBean);
        }
    }

    private static class ViewHolder {
        private TextView tvLoginName, tvUserName, tvTelephone, tvDepartment;
    }
}