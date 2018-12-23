package com.tmxk.wscl.android.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.mvp.model.AdminListBean;
import com.tmxk.wscl.android.mvp.model.UserLoginLogBean;

import java.util.List;

public class LoginLogListAdapter extends BaseAdapter {
    private List<UserLoginLogBean> userLoginLogBeans;
    private final LayoutInflater mInflater;

    public LoginLogListAdapter(Context context, List<UserLoginLogBean> userLoginLogBeans) {
        this.userLoginLogBeans = userLoginLogBeans;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<UserLoginLogBean> getList() {
        return this.userLoginLogBeans;
    }

    public void setList(List<UserLoginLogBean> userLoginLogBeans) {
        this.userLoginLogBeans = userLoginLogBeans;
    }

    @Override
    public int getCount() {
        return userLoginLogBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return userLoginLogBeans.get(position);
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
            convertView = mInflater.inflate(R.layout.simple_list_item_3, null);
            holder = new ViewHolder();
            holder.tvUserName = convertView.findViewById(R.id.userName);
            holder.tvLoginName = convertView.findViewById(R.id.loginName);
            holder.tvDepartment = convertView.findViewById(R.id.department);
            holder.tvTelephone = convertView.findViewById(R.id.telephone);
            holder.tvEmail = convertView.findViewById(R.id.email);
            holder.tvLoginTime = convertView.findViewById(R.id.loginTime);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        UserLoginLogBean userLoginLogBean = userLoginLogBeans.get(position);
        if (userLoginLogBean != null) {
            if (userLoginLogBean.getSysuser() != null) {
                holder.tvLoginName.setText("用户名：".concat(userLoginLogBean.getSysuser().getLoginName()));
                holder.tvUserName.setText("姓名：".concat(userLoginLogBean.getSysuser().getUserName()));
                holder.tvDepartment.setText("部门：".concat(userLoginLogBean.getSysuser().getDepartment()));
                holder.tvTelephone.setText("联系电话：".concat(userLoginLogBean.getSysuser().getTelephone()));
                holder.tvEmail.setText("邮箱：".concat(userLoginLogBean.getSysuser().getUserEmail()));
            }
            holder.tvLoginTime.setText("登录时间：".concat(userLoginLogBean.getUserlogintime() + ""));
        }
        return convertView;
    }

    private static class ViewHolder {
        private TextView tvLoginName, tvUserName, tvTelephone, tvDepartment, tvEmail, tvLoginTime;
    }
}