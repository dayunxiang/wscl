package com.tmxk.wscl.android.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.mvp.model.InspectionEntryBean;
import com.tmxk.wscl.android.util.CommonUtil;
import com.tmxk.wscl.android.util.Const;

import java.util.List;

public class RadioListAdapter extends BaseAdapter {
    private List<InspectionEntryBean> inspectionEntryBeans;
    private final LayoutInflater mInflater;

    public RadioListAdapter(Context context, List<InspectionEntryBean> inspectionEntryBeans) {
        this.inspectionEntryBeans = inspectionEntryBeans;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<InspectionEntryBean> getList() {
        return this.inspectionEntryBeans;
    }

    public void setList(List<InspectionEntryBean> inspectionEntryBeans) {
        this.inspectionEntryBeans = inspectionEntryBeans;
    }

    @Override
    public int getCount() {
        return inspectionEntryBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return inspectionEntryBeans.get(position);
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
            convertView = mInflater.inflate(R.layout.list_item_radio, null);
            holder = new ViewHolder();
            holder.tv = convertView.findViewById(R.id.textView);
            holder.rb_1 = convertView.findViewById(R.id.rb_1);
            holder.rb_2 = convertView.findViewById(R.id.rb_2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final InspectionEntryBean inspectionEntryBean = inspectionEntryBeans.get(position);
        if (inspectionEntryBean != null) {
            if(!CommonUtil.isEmpty(inspectionEntryBean.getName())){
                holder.tv.setText(inspectionEntryBean.getName());
            }else {
                holder.tv.setText("无");
            }
            if(inspectionEntryBean.getChildren()!=null&&
                    inspectionEntryBean.getChildren().size()>1){
                holder.rb_1.setText(inspectionEntryBean.getChildren().get(0).getName());
                holder.rb_2.setText(inspectionEntryBean.getChildren().get(1).getName());
            }else {
                holder.rb_1.setText("无");
                holder.rb_2.setText("无");
            }
            holder.rb_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Const.INSPECTION_ENTRY.put(inspectionEntryBean.getAlias(),inspectionEntryBean.getChildren().get(0).getAlias());
                }
            });
            holder.rb_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Const.INSPECTION_ENTRY.put(inspectionEntryBean.getAlias(),inspectionEntryBean.getChildren().get(1).getAlias());
                }
            });
        }
        return convertView;
    }

    private static class ViewHolder {
        private TextView tv;
        private RadioButton rb_1, rb_2;
    }
}