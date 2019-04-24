package com.tmxk.wscl.android.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.mvp.model.DeviceReplaceListBean;
import com.tmxk.wscl.android.util.CommonUtil;

import java.util.List;

public class DeviceReplaceListAdapter extends BaseAdapter {
    private List<DeviceReplaceListBean.ObjectBean> deviceReplaceBeans;
    private final LayoutInflater mInflater;

    public DeviceReplaceListAdapter(Context context, List<DeviceReplaceListBean.ObjectBean> deviceReplaceBeans) {
        this.deviceReplaceBeans = deviceReplaceBeans;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<DeviceReplaceListBean.ObjectBean> getList() {
        return this.deviceReplaceBeans;
    }

    public void setList(List<DeviceReplaceListBean.ObjectBean> deviceReplaceBeans) {
        this.deviceReplaceBeans = deviceReplaceBeans;
    }

    @Override
    public int getCount() {
        return deviceReplaceBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return deviceReplaceBeans.get(position);
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
            convertView = mInflater.inflate(R.layout.list_item_device_replace, null);
            holder = new ViewHolder();
            holder.tvSewageName = convertView.findViewById(R.id.sewageName);
            holder.tvSewageOperateNo = convertView.findViewById(R.id.sewageOperateNo);
            holder.tvDeviceName = convertView.findViewById(R.id.deviceName);
            holder.tvDeviceType = convertView.findViewById(R.id.deviceType);
            holder.tvProductDate = convertView.findViewById(R.id.produceDate);
            holder.tvDeviceNo = convertView.findViewById(R.id.deviceNo);
            holder.tvReplaceDate = convertView.findViewById(R.id.replaceDate);
            holder.tvBrokerage = convertView.findViewById(R.id.brokerage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DeviceReplaceListBean.ObjectBean deviceReplaceBean = deviceReplaceBeans.get(position);
        if (deviceReplaceBean != null) {
            if (deviceReplaceBean.getSewage() != null) {
//                站点名称 运营编号 报修人 报修人电话 报修时间 报修内容 处理状态 处理人 处理时间
                if(!CommonUtil.isEmpty(deviceReplaceBean.getSewage().getShortTitle())){
                    holder.tvSewageName.setText("站点名称：".concat(deviceReplaceBean.getSewage().getShortTitle()));
                }else {
                    holder.tvSewageName.setText("站点名称：无");
                }
                if(!CommonUtil.isEmpty(deviceReplaceBean.getSewage().getOperationNum())){
                    holder.tvSewageOperateNo.setText("运营编号：".concat(deviceReplaceBean.getSewage().getOperationNum()));
                }else {
                    holder.tvSewageOperateNo.setText("运营编号：无");
                }
            }
            if(!CommonUtil.isEmpty(deviceReplaceBean.getDeviceName())){
                holder.tvDeviceName.setText("设备名称：".concat(deviceReplaceBean.getDeviceName()));
            }else {
                holder.tvDeviceName.setText("设备名称：无");
            }
//            <!--污水站点名称 运营编号 设备名称 设备类型 生产日期 设备编号 替换时间 经手人-->
            if(!CommonUtil.isEmpty(deviceReplaceBean.getDeviceType())){
                holder.tvDeviceType.setText("设备类型：".concat(deviceReplaceBean.getDeviceType()));
            }else {
                holder.tvDeviceType.setText("设备类型：无");
            }
            if(deviceReplaceBean.getProductionDate()!=0){
                holder.tvProductDate.setText("生产日期：".concat(CommonUtil.stampToStr2(deviceReplaceBean.getProductionDate())));
            }else {
                holder.tvProductDate.setText("生产日期：---");
            }
            if(!CommonUtil.isEmpty(deviceReplaceBean.getDeviceNo())){
                holder.tvDeviceNo.setText("设备编号：".concat(deviceReplaceBean.getDeviceNo()));
            }else {
                holder.tvDeviceNo.setText("设备编号：无");
            }
            if(deviceReplaceBean.getReplaceDate()!=0){
                holder.tvReplaceDate.setText("替换时间：".concat(CommonUtil.stampToStr2(deviceReplaceBean.getReplaceDate())));
            }else {
                holder.tvReplaceDate.setText("替换时间： ---");
            }
            if(!CommonUtil.isEmpty(deviceReplaceBean.getBrokerage())){
                holder.tvBrokerage.setText("经手人：".concat(deviceReplaceBean.getBrokerage()));
            }else {
                holder.tvBrokerage.setText("经手人：无");
            }
        }
        return convertView;
    }

    private static class ViewHolder {
        private TextView tvSewageName, tvSewageOperateNo, tvDeviceName, tvDeviceType, tvProductDate,
                tvDeviceNo, tvReplaceDate, tvBrokerage;
    }
}