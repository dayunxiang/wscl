package com.tmxk.wscl.android.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.mvp.model.InspectionInfoListBean;
import com.tmxk.wscl.android.mvp.model.RepairmentBean;
import com.tmxk.wscl.android.util.CommonUtil;

import java.util.List;

public class InspectionInfoListAdapter extends BaseAdapter {
    private List<InspectionInfoListBean.ObjectBean> inspectionInfoBeans;
    private final LayoutInflater mInflater;

    public InspectionInfoListAdapter(Context context, List<InspectionInfoListBean.ObjectBean> inspectionInfoBeans) {
        this.inspectionInfoBeans = inspectionInfoBeans;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<InspectionInfoListBean.ObjectBean> getList() {
        return this.inspectionInfoBeans;
    }

    public void setList(List<InspectionInfoListBean.ObjectBean> inspectionInfoBeans) {
        this.inspectionInfoBeans = inspectionInfoBeans;
    }

    @Override
    public int getCount() {
        return inspectionInfoBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return inspectionInfoBeans.get(position);
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
            convertView = mInflater.inflate(R.layout.list_item_inspection_info, null);
            holder = new ViewHolder();
            holder.tvSewageName = convertView.findViewById(R.id.sewageName);
            holder.tvSewageOperateNo = convertView.findViewById(R.id.sewageOperateNo);
            holder.tvSewageControlId = convertView.findViewById(R.id.sewageControlId);
            holder.tvInspectionTeamWork = convertView.findViewById(R.id.inspectionTeamWork);
            holder.tvInspectionDate = convertView.findViewById(R.id.inspectionDate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        InspectionInfoListBean.ObjectBean inspectionInfo = inspectionInfoBeans.get(position);
        if (inspectionInfo != null) {
            if (inspectionInfo.getSewage() != null) {
//                站点名称 运营编号 报修人 报修人电话 报修时间 报修内容 处理状态 处理人 处理时间
                if(!CommonUtil.isEmpty(inspectionInfo.getSewage().getShortTitle())){
                    holder.tvSewageName.setText("站点名称：".concat(inspectionInfo.getSewage().getShortTitle()));
                }else {
                    holder.tvSewageName.setText("站点名称：无");
                }
                if(!CommonUtil.isEmpty(inspectionInfo.getSewage().getOperationNum())){
                    holder.tvSewageOperateNo.setText("运营编号：".concat(inspectionInfo.getSewage().getOperationNum()));
                }else {
                    holder.tvSewageOperateNo.setText("运营编号：无");
                }
                if(inspectionInfo.getSewage().getControlId()!=0){
                    holder.tvSewageControlId.setText("控制 ID：".concat(inspectionInfo.getSewage().getControlId()+""));
                }else {
                    holder.tvSewageControlId.setText("控制 ID：无");
                }
            }
            if(!CommonUtil.isEmpty(inspectionInfo.getInspectionTeamWork())){
                holder.tvInspectionTeamWork.setText("巡检班组：".concat(inspectionInfo.getInspectionTeamWork()));
            }else {
                holder.tvInspectionTeamWork.setText("巡检班组：无");
            }
            if(inspectionInfo.getInspectionDate()!=null && Long.parseLong(inspectionInfo.getInspectionDate())!=0){
                holder.tvInspectionDate.setText("巡检时间：".concat(CommonUtil.stampToStr2(Long.parseLong(inspectionInfo.getInspectionDate()))));
            }else {
                holder.tvInspectionDate.setText("巡检时间：无");
            }
        }
        return convertView;
    }

    private static class ViewHolder {
        private TextView tvSewageName, tvSewageOperateNo, tvSewageControlId, tvInspectionTeamWork, tvInspectionDate;
    }
}