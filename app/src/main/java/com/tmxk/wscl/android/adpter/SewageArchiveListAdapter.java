package com.tmxk.wscl.android.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.mvp.model.ControlMethodBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.SiteDeviceDocBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SewageArchiveListAdapter extends BaseAdapter {
    private final List<SewageListBean.ObjectBean> sewageBeans;
    private final LayoutInflater mInflater;

    public SewageArchiveListAdapter(Context context, List<SewageListBean.ObjectBean> sewageBeans) {
        this.sewageBeans = sewageBeans;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return sewageBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return sewageBeans.get(position);
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
            convertView = mInflater.inflate(R.layout.sewage_list_item, null);
            holder = new ViewHolder();
            holder.tvSewageName = convertView.findViewById(R.id.sewageName);
            holder.tvSewageShortName = convertView.findViewById(R.id.sewageShortName);
            holder.tvSewageOperator = convertView.findViewById(R.id.sewageOperator);
            holder.tvSewageControlId = convertView.findViewById(R.id.sewageControlId);
            holder.tvSewageControlMethod = convertView.findViewById(R.id.sewageControlMethod);
            holder.tvSewageEmissionStandard = convertView.findViewById(R.id.sewageEmissionStandard);
            holder.tvSewageOperateNo = convertView.findViewById(R.id.sewageOperateNo);
            holder.tvSewageTonnage = convertView.findViewById(R.id.sewageTonnage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SewageListBean.ObjectBean swageBean = sewageBeans.get(position);
        if (swageBean != null) {
            List<ControlMethodBean> controlMethodBeans = new ArrayList<>();
            controlMethodBeans.add(new ControlMethodBean(1,"AOF工艺"));
            controlMethodBeans.add(new ControlMethodBean(2,"AO+人工湿地"));
            controlMethodBeans.add(new ControlMethodBean(3,"AO工艺"));
            controlMethodBeans.add(new ControlMethodBean(4,"MBR工艺"));
            controlMethodBeans.add(new ControlMethodBean(5,"SBR工艺"));
            controlMethodBeans.add(new ControlMethodBean(6,"跌水充氧接触氧化+人工湿地工艺"));
            controlMethodBeans.add(new ControlMethodBean(7,"复合生物滤床+人工湿地工艺"));
            controlMethodBeans.add(new ControlMethodBean(8,"复合塔式生态滤池+人工湿地工艺"));
            controlMethodBeans.add(new ControlMethodBean(9,"高负荷地下渗滤工艺"));
            controlMethodBeans.add(new ControlMethodBean(10,"海沃特工艺"));
            controlMethodBeans.add(new ControlMethodBean(11,"接触氧化+人工湿地工艺"));
            controlMethodBeans.add(new ControlMethodBean(12,"脉冲多层复合滤池+人工湿地工艺"));
            controlMethodBeans.add(new ControlMethodBean(13,"厌氧滤池+好氧流化床工艺"));
            controlMethodBeans.add(new ControlMethodBean(14,"转笼式生物膜工艺"));
//      <!--站点名称、简称、管理员、控制ID、运营编号、吨位、处理工艺、排放标准-->
            holder.tvSewageName.setText("站点名称：".concat(swageBean.getName()));
            holder.tvSewageShortName.setText("简称：".concat(swageBean.getShortTitle()));
            holder.tvSewageOperator.setText("管理员：".concat(swageBean.getAdministrator().getName()));
            holder.tvSewageControlId.setText("控制ID：".concat(swageBean.getControlId()+""));
            holder.tvSewageControlMethod.setText("处理工艺：".concat(controlMethodBeans.get(swageBean.getControlMethod()).getName()));
            holder.tvSewageEmissionStandard.setText("排放标准：".concat(swageBean.getEmissionStandard()));
            holder.tvSewageOperateNo.setText("运营编号：".concat(swageBean.getOperationNum()));
            holder.tvSewageTonnage.setText("吨位：".concat(swageBean.getTonnage()+" 吨"));
        }
        return convertView;
    }

    private static class ViewHolder {
        private TextView tvSewageName,tvSewageShortName, tvSewageOperator, tvSewageControlId,
                tvSewageControlMethod, tvSewageEmissionStandard, tvSewageOperateNo, tvSewageTonnage;
    }
}