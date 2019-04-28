package com.tmxk.wscl.android.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.mvp.model.InspectionEntryBean;
import com.tmxk.wscl.android.util.CommonUtil;
import com.tmxk.wscl.android.util.Const;

import java.util.List;

public class SpinnerListAdapter extends BaseAdapter {
    private List<InspectionEntryBean> inspectionEntryBeans;
    private final LayoutInflater mInflater;
    private ArrayAdapter<String> adapter;
    private Context context;

    public SpinnerListAdapter(Context context, List<InspectionEntryBean> inspectionEntryBeans) {
        this.inspectionEntryBeans = inspectionEntryBeans;
        this.context = context;
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
            convertView = mInflater.inflate(R.layout.list_item_spinner, null);
            holder = new ViewHolder();
            holder.tv = convertView.findViewById(R.id.textView);
            holder.spinner = convertView.findViewById(R.id.spinner);
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
            if(inspectionEntryBean.getChildren()!=null&&inspectionEntryBean.getChildren().size()>0){
                String[] m = new String[inspectionEntryBean.getChildren().size()];
                final String[] alias = new String[inspectionEntryBean.getChildren().size()];
                for(int i=0;i<inspectionEntryBean.getChildren().size();i++){
                    m[i] = inspectionEntryBean.getChildren().get(i).getName();
                    alias[i] = inspectionEntryBean.getChildren().get(i).getAlias();
                }
                //将可选内容与ArrayAdapter连接起来
                adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,m);
                //设置下拉列表的风格
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //将adapter 添加到spinner中
                holder.spinner.setAdapter(adapter);
                holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Const.INSPECTION_ENTRY.put(inspectionEntryBean.getAlias(),alias[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        }
        return convertView;
    }

    private static class ViewHolder {
        private TextView tv;
        private Spinner spinner;
    }
}