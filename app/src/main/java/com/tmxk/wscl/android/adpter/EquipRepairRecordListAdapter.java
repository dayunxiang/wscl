package com.tmxk.wscl.android.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.mvp.model.EquipRepairRecordListBean;
import com.tmxk.wscl.android.util.CommonUtil;

import java.util.List;

public class EquipRepairRecordListAdapter extends BaseAdapter {
    private List<EquipRepairRecordListBean.ObjectBean> equipRepairRecordBeans;
    private final LayoutInflater mInflater;

    public EquipRepairRecordListAdapter(Context context, List<EquipRepairRecordListBean.ObjectBean> equipRepairRecordBeans) {
        this.equipRepairRecordBeans = equipRepairRecordBeans;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<EquipRepairRecordListBean.ObjectBean> getList() {
        return this.equipRepairRecordBeans;
    }

    public void setList(List<EquipRepairRecordListBean.ObjectBean> equipRepairRecordBeans) {
        this.equipRepairRecordBeans = equipRepairRecordBeans;
    }

    @Override
    public int getCount() {
        return equipRepairRecordBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return equipRepairRecordBeans.get(position);
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
            convertView = mInflater.inflate(R.layout.list_item_equip_repair_record, null);
            holder = new ViewHolder();
            holder.tvSewageName = convertView.findViewById(R.id.sewageName);
            holder.tvSewageOperateNo = convertView.findViewById(R.id.sewageOperateNo);
            holder.tvRepairReason = convertView.findViewById(R.id.repairReason);
            holder.tvRepairContent = convertView.findViewById(R.id.repairContent);
            holder.tvConsumeMaterial = convertView.findViewById(R.id.consumeMaterial);
            holder.tvRepairMan = convertView.findViewById(R.id.repairMan);
            holder.tvCompleteTime = convertView.findViewById(R.id.completeTime);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        EquipRepairRecordListBean.ObjectBean equipRepairRecordBean = equipRepairRecordBeans.get(position);
        if (equipRepairRecordBean != null) {
            if (equipRepairRecordBean.getSewage() != null) {
//              所属污水站点 运营编号 保养原因 保养内容 消耗材料 维修人员 维护时间
                if(!CommonUtil.isEmpty(equipRepairRecordBean.getSewage().getShortTitle())){
                    holder.tvSewageName.setText("站点名称：".concat(equipRepairRecordBean.getSewage().getShortTitle()));
                }else {
                    holder.tvSewageName.setText("站点名称：无");
                }
                if(!CommonUtil.isEmpty(equipRepairRecordBean.getSewage().getOperationNum())){
                    holder.tvSewageOperateNo.setText("运营编号：".concat(equipRepairRecordBean.getSewage().getOperationNum()));
                }else {
                    holder.tvSewageOperateNo.setText("运营编号：无");
                }
            }
            if(!CommonUtil.isEmpty(equipRepairRecordBean.getRepairReason())){
                holder.tvRepairReason.setText("保养原因：".concat(equipRepairRecordBean.getRepairReason()));
            }else {
                holder.tvRepairReason.setText("保养原因：无");
            }
            if(!CommonUtil.isEmpty(equipRepairRecordBean.getRepairContent())){
                holder.tvRepairContent.setText("保养内容：".concat(equipRepairRecordBean.getRepairContent()));
            }else {
                holder.tvRepairContent.setText("保养内容：无");
            }
            if(!CommonUtil.isEmpty(equipRepairRecordBean.getConsumeMaterial())){
                holder.tvConsumeMaterial.setText("消耗材料：".concat(equipRepairRecordBean.getConsumeMaterial()));
            }else {
                holder.tvConsumeMaterial.setText("消耗材料：无");
            }
            if(!CommonUtil.isEmpty(equipRepairRecordBean.getRepairMan())){
                holder.tvRepairMan.setText("维修人员：".concat(equipRepairRecordBean.getRepairMan()));
            }else {
                holder.tvRepairMan.setText("维修人员：无");
            }
            if(equipRepairRecordBean.getCompleteTime()!=0){
                holder.tvCompleteTime.setText("维护时间：".concat(CommonUtil.stampToStr2(equipRepairRecordBean.getCompleteTime())));
            }else {
                holder.tvCompleteTime.setText("维护时间： ---");
            }
        }
        return convertView;
    }

    private static class ViewHolder {
        private TextView tvSewageName, tvSewageOperateNo, tvRepairReason, tvRepairContent, tvConsumeMaterial,
                tvRepairMan, tvCompleteTime;
    }
}