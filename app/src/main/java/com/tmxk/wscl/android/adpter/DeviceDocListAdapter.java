package com.tmxk.wscl.android.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.mvp.model.SiteDeviceDocBean;
import com.tmxk.wscl.android.mvp.model.SiteDeviceDocListBean;
import com.tmxk.wscl.android.mvp.model.UserBean;
import com.tmxk.wscl.android.mvp.model.UserLoginLogBean;
import com.tmxk.wscl.android.util.CommonUtil;

import java.util.List;

public class DeviceDocListAdapter extends BaseAdapter {
    private List<SiteDeviceDocBean> siteDeviceDocBeans;
    private final LayoutInflater mInflater;

    public DeviceDocListAdapter(Context context, List<SiteDeviceDocBean> siteDeviceDocBeans) {
        this.siteDeviceDocBeans = siteDeviceDocBeans;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<SiteDeviceDocBean> getList() {
        return this.siteDeviceDocBeans;
    }

    public void setList(List<SiteDeviceDocBean> siteDeviceDocBeans) {
        this.siteDeviceDocBeans = siteDeviceDocBeans;
    }

    @Override
    public int getCount() {
        return siteDeviceDocBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return siteDeviceDocBeans.get(position);
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
            convertView = mInflater.inflate(R.layout.device_doc_list_item, null);
            holder = new ViewHolder();
            holder.tvDeviceName = convertView.findViewById(R.id.deviceName);
            holder.tvDeviceNumber = convertView.findViewById(R.id.deviceNumber);
            holder.tvDevicePower = convertView.findViewById(R.id.devicePower);
            holder.tvDeviceType = convertView.findViewById(R.id.deviceType);
            holder.tvSetupTime = convertView.findViewById(R.id.setupTime);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SiteDeviceDocBean siteDeviceDocBean = siteDeviceDocBeans.get(position);
        if (siteDeviceDocBean != null) {
//          设备名称、设备编号、设备类型、设备功率、安装时间（选择）
            if(siteDeviceDocBean.getDeviceName()!=null){
            }
            holder.tvDeviceName.setText("设备名称：".concat(siteDeviceDocBean.getDeviceName()));
            if(siteDeviceDocBean.getDeviceNo()!=null){
                holder.tvDeviceNumber.setText("设备编号：".concat(siteDeviceDocBean.getDeviceNo()));
            }else {
                holder.tvDeviceNumber.setText("设备编号：无");
            }
            holder.tvDevicePower.setText("设备类型：".concat(siteDeviceDocBean.getDevicePower()+""));
            if(siteDeviceDocBean.getDeviceType()!=null){
                holder.tvDeviceType.setText("设备功率：".concat(siteDeviceDocBean.getDeviceType()));
            }else {
                holder.tvDeviceType.setText("设备功率：无");
            }
            if(siteDeviceDocBean.getSetupTime()!=null){
                holder.tvSetupTime.setText("安装时间：".concat(CommonUtil.stampToDate(siteDeviceDocBean.getSetupTime()).substring(0,10)));
            }else {
                holder.tvSetupTime.setText("安装时间：---");
            }
        }
        return convertView;
    }

    private static class ViewHolder {
        private TextView tvDeviceName, tvDeviceNumber, tvDeviceType, tvDevicePower, tvSetupTime;
    }
}