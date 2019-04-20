package com.tmxk.wscl.android.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.mvp.model.AssignmentOrderListBean;
import com.tmxk.wscl.android.mvp.model.RepairmentBean;
import com.tmxk.wscl.android.util.CommonUtil;

import java.util.List;

public class RepairmentListAdapter extends BaseAdapter {
    private List<RepairmentBean> repairmentBeans;
    private final LayoutInflater mInflater;

    public RepairmentListAdapter(Context context, List<RepairmentBean> repairmentBeans) {
        this.repairmentBeans = repairmentBeans;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<RepairmentBean> getList() {
        return this.repairmentBeans;
    }

    public void setList(List<RepairmentBean> repairmentBeans) {
        this.repairmentBeans = repairmentBeans;
    }

    @Override
    public int getCount() {
        return repairmentBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return repairmentBeans.get(position);
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
            convertView = mInflater.inflate(R.layout.list_item_repairment_list, null);
            holder = new ViewHolder();
            holder.tvSewageName = convertView.findViewById(R.id.sewageName);
            holder.tvSewageOperateNo = convertView.findViewById(R.id.sewageOperateNo);
            holder.tvRepairman = convertView.findViewById(R.id.repairman);
            holder.tvRepairmanTel = convertView.findViewById(R.id.repairmanTel);
            holder.tvRepairDate = convertView.findViewById(R.id.repairDate);
            holder.tvRepairContent = convertView.findViewById(R.id.repairContent);
            holder.tvHandleStatus = convertView.findViewById(R.id.handleStatus);
            holder.tvHandleMan = convertView.findViewById(R.id.handleMan);
            holder.tvHandleDate = convertView.findViewById(R.id.handleDate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RepairmentBean repairmentBean = repairmentBeans.get(position);
        if (repairmentBean != null) {
            if (repairmentBean.getSewage() != null) {
//                站点名称 运营编号 报修人 报修人电话 报修时间 报修内容 处理状态 处理人 处理时间
                if(!CommonUtil.isEmpty(repairmentBean.getSewage().getShortTitle())){
                    holder.tvSewageName.setText("站点名称：".concat(repairmentBean.getSewage().getShortTitle()));
                }else {
                    holder.tvSewageName.setText("站点名称：无");
                }
                if(!CommonUtil.isEmpty(repairmentBean.getSewage().getOperationNum())){
                    holder.tvSewageOperateNo.setText("运营编号：".concat(repairmentBean.getSewage().getOperationNum()));
                }else {
                    holder.tvSewageOperateNo.setText("运营编号：无");
                }
            }
            if(!CommonUtil.isEmpty(repairmentBean.getRepairman())){
                holder.tvRepairman.setText("报修人：".concat(repairmentBean.getRepairman()));
            }else {
                holder.tvRepairman.setText("报修人：无");
            }
            if(!CommonUtil.isEmpty(repairmentBean.getRepairmanTel())){
                holder.tvRepairmanTel.setText("报修人电话：".concat(repairmentBean.getRepairmanTel()));
            }else {
                holder.tvRepairmanTel.setText("报修人电话：无");
            }
            if(repairmentBean.getRepairTime()!=null && Long.parseLong(repairmentBean.getRepairTime())!=0){
                holder.tvRepairDate.setText("报修时间：".concat(CommonUtil.stampToStr2(Long.parseLong(repairmentBean.getRepairTime()))));
            }else {
                holder.tvRepairDate.setText("报修时间：无");
            }
            if(!CommonUtil.isEmpty(repairmentBean.getRepairContent())){
                holder.tvRepairContent.setText("报修内容：".concat(repairmentBean.getRepairContent()));
            }else {
                holder.tvRepairContent.setText("报修内容：无");
            }
            if(!CommonUtil.isEmpty(repairmentBean.getStatus())){
                holder.tvHandleStatus.setText("处理状态：".concat(repairmentBean.getStatus()));
            }else {
                holder.tvHandleStatus.setText("处理状态：无");
            }
            if(!CommonUtil.isEmpty(repairmentBean.getHandlman())){
                holder.tvHandleMan.setText("处理人：".concat(repairmentBean.getHandlman()));
            }else {
                holder.tvHandleMan.setText("处理人： ---");
            }
            if(repairmentBean.getHandltime()!=null && Long.parseLong(repairmentBean.getHandltime())!=0){
                holder.tvHandleDate.setText("处理时间：".concat(CommonUtil.stampToStr2(Long.parseLong(repairmentBean.getHandltime()))));
            }else {
                holder.tvHandleDate.setText("处理时间： ---");
            }
        }
        return convertView;
    }

    private static class ViewHolder {
        private TextView tvSewageName, tvSewageOperateNo, tvRepairman, tvRepairmanTel, tvRepairDate, tvRepairContent, tvHandleStatus, tvHandleMan, tvHandleDate;
    }
}