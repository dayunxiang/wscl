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
import com.tmxk.wscl.android.mvp.model.SiteDeviceDocBean;
import com.tmxk.wscl.android.util.CommonUtil;

import java.util.List;

public class AdminListAdapter extends BaseAdapter {
    private List<AdminListBean.ObjectBean> adminBeans;
    private final LayoutInflater mInflater;

    public AdminListAdapter(Context context, List<AdminListBean.ObjectBean> adminBeans) {
        this.adminBeans = adminBeans;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<AdminListBean.ObjectBean> getList() {
        return this.adminBeans;
    }

    public void setList(List<AdminListBean.ObjectBean> adminBeans) {
        this.adminBeans = adminBeans;
    }

    @Override
    public int getCount() {
        return adminBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return adminBeans.get(position);
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
            convertView = mInflater.inflate(R.layout.admin_list_item, null);
            holder = new ViewHolder();
            holder.tvAdminName = convertView.findViewById(R.id.adminName);
            holder.tvAdminTelephone = convertView.findViewById(R.id.adminTelephone);
            holder.tvAdminEmail = convertView.findViewById(R.id.adminEmail);
            holder.tvAdminAddress = convertView.findViewById(R.id.adminAddress);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AdminListBean.ObjectBean adminBean = adminBeans.get(position);
        if (adminBean != null) {
//          设备名称、设备编号、设备类型、设备功率、安装时间（选择）
            if(adminBean.getName()!=null){
                holder.tvAdminName.setText("姓名：".concat(adminBean.getName()));
            }
            if(adminBean.getTelephone()!=null){
                holder.tvAdminTelephone.setText("手机：".concat(adminBean.getTelephone()));
            }
            if(adminBean.getEmail()!=null){
                holder.tvAdminEmail.setText("邮箱：".concat(adminBean.getEmail()));
            }
            if(adminBean.getAddress()!=null){
                holder.tvAdminAddress.setText("地址：".concat(adminBean.getAddress()));
            }
        }
        return convertView;
    }

    private static class ViewHolder {
        private TextView tvAdminName, tvAdminTelephone, tvAdminAddress, tvAdminEmail;
    }
}