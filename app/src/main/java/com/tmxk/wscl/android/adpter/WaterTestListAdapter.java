package com.tmxk.wscl.android.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.mvp.model.WaterTestListBean;
import com.tmxk.wscl.android.util.CommonUtil;

import java.util.List;

public class WaterTestListAdapter extends BaseAdapter {
    private List<WaterTestListBean.ObjectBean> waterTestBeans;
    private final LayoutInflater mInflater;

    public WaterTestListAdapter(Context context, List<WaterTestListBean.ObjectBean> waterTestBeans) {
        this.waterTestBeans = waterTestBeans;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<WaterTestListBean.ObjectBean> getList() {
        return this.waterTestBeans;
    }

    public void setList(List<WaterTestListBean.ObjectBean> waterTestBeans) {
        this.waterTestBeans = waterTestBeans;
    }

    @Override
    public int getCount() {
        return waterTestBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return waterTestBeans.get(position);
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
            convertView = mInflater.inflate(R.layout.list_item_water_test, null);
            holder = new ViewHolder();
            holder.tvSewageName = convertView.findViewById(R.id.sewageName);
            holder.tvSewageOperateNo = convertView.findViewById(R.id.sewageOperateNo);
            holder.tvSewageControlId = convertView.findViewById(R.id.sewageControlId);
            holder.tvReportNo = convertView.findViewById(R.id.reportNo);
            holder.tvInCod = convertView.findViewById(R.id.inCod);
            holder.tvInNh3n = convertView.findViewById(R.id.inNh3n);
            holder.tvInP = convertView.findViewById(R.id.inP);
            holder.tvOutCod = convertView.findViewById(R.id.outCod);
            holder.tvOutNh3n = convertView.findViewById(R.id.outNh3n);
            holder.tvOutP = convertView.findViewById(R.id.outP);
            holder.tvTestingTime = convertView.findViewById(R.id.testingTime);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        WaterTestListBean.ObjectBean waterTestBean = waterTestBeans.get(position);
        if (waterTestBean != null) {
            if (waterTestBean.getSewage() != null) {
                if(!CommonUtil.isEmpty(waterTestBean.getSewage().getShortTitle())){
                    holder.tvSewageName.setText("站点名称：".concat(waterTestBean.getSewage().getShortTitle()));
                }else {
                    holder.tvSewageName.setText("站点名称：无");
                }
                if(!CommonUtil.isEmpty(waterTestBean.getSewage().getOperationNum())){
                    holder.tvSewageOperateNo.setText("运营编号：".concat(waterTestBean.getSewage().getOperationNum()));
                }else {
                    holder.tvSewageOperateNo.setText("运营编号：无");
                }
                if(waterTestBean.getSewage().getControlId()!=0){
                    holder.tvSewageControlId.setText("控制ID：".concat(waterTestBean.getSewage().getControlId()+""));
                }else {
                    holder.tvSewageControlId.setText("控制ID：无");
                }
            }
//        污水站点名称 运营编号 控制ID 化验单号 进水COD 进水NH3N 进水P 出水COD 出水NH3N 出水P 取样时间
            if(!CommonUtil.isEmpty(waterTestBean.getReportNo())){
                holder.tvReportNo.setText("化验单号：".concat(waterTestBean.getReportNo()));
            }else {
                holder.tvReportNo.setText("化验单号：无");
            }
            if(waterTestBean.getInCod()!=null){
                holder.tvInCod.setText("进水COD：".concat(waterTestBean.getInCod()+""));
            }else {
                holder.tvInCod.setText("进水COD：无");
            }
            if(waterTestBean.getInNh3n()!=null){
                holder.tvInNh3n.setText("进水NH3N：".concat(waterTestBean.getInNh3n()+""));
            }else {
                holder.tvInNh3n.setText("进水NH3N：无");
            }
            if(waterTestBean.getInP()!=null){
                holder.tvInP.setText("进水P：".concat(waterTestBean.getInP()+""));
            }else {
                holder.tvInP.setText("进水P：无");
            }
            if(waterTestBean.getOutCod()!=null){
                holder.tvOutCod.setText("出水COD：".concat(waterTestBean.getOutCod()+""));
            }else {
                holder.tvOutCod.setText("出水COD：无");
            }
            if(waterTestBean.getOutNh3n()!=null){
                holder.tvOutNh3n.setText("出水NH3N：".concat(waterTestBean.getOutNh3n()+""));
            }else {
                holder.tvOutNh3n.setText("出水NH3N：无");
            }
            if(waterTestBean.getOutP()!=null){
                holder.tvOutP.setText("出水P：".concat(waterTestBean.getOutP()+""));
            }else {
                holder.tvOutP.setText("出水P：无");
            }
            if(waterTestBean.getTestingTime()!=0){
                holder.tvTestingTime.setText("取样时间：".concat(CommonUtil.stampToStr2(waterTestBean.getTestingTime())));
            }else {
                holder.tvTestingTime.setText("取样时间： ---");
            }
        }
        return convertView;
    }

    private static class ViewHolder {
        private TextView tvSewageName, tvSewageOperateNo, tvSewageControlId, tvReportNo,tvInCod,tvInNh3n,tvInP,tvOutCod,
                tvOutNh3n,tvOutP,tvTestingTime;
    }
}