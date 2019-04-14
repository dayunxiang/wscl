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
import com.tmxk.wscl.android.mvp.model.UserLoginLogBean;
import com.tmxk.wscl.android.util.CommonUtil;

import java.util.List;

public class AssignOrderListAdapter extends BaseAdapter {
    private List<AssignmentOrderListBean.ObjectBean> assignmentOrderBeans;
    private final LayoutInflater mInflater;

    public AssignOrderListAdapter(Context context, List<AssignmentOrderListBean.ObjectBean> assignmentOrderBeans) {
        this.assignmentOrderBeans = assignmentOrderBeans;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<AssignmentOrderListBean.ObjectBean> getList() {
        return this.assignmentOrderBeans;
    }

    public void setList(List<AssignmentOrderListBean.ObjectBean> assignmentOrderBeans) {
        this.assignmentOrderBeans = assignmentOrderBeans;
    }

    @Override
    public int getCount() {
        return assignmentOrderBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return assignmentOrderBeans.get(position);
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
            convertView = mInflater.inflate(R.layout.list_item_assginment_order, null);
            holder = new ViewHolder();
            holder.tvAssignTime = convertView.findViewById(R.id.assignTime);
            holder.tvSysuser = convertView.findViewById(R.id.sysuser);
            holder.tvTaskStartTime = convertView.findViewById(R.id.taskStartTime);
            holder.tvTaskEndTime = convertView.findViewById(R.id.taskEndTime);
            holder.tvTaskDescription = convertView.findViewById(R.id.taskDescription);
            holder.tvTaskStatus = convertView.findViewById(R.id.taskStatus);
            holder.tvSewageInfo = convertView.findViewById(R.id.sewageInfo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AssignmentOrderListBean.ObjectBean assignmentOrderBean = assignmentOrderBeans.get(position);
        if (assignmentOrderBean != null) {
            if (assignmentOrderBean.getSysUser() != null && assignmentOrderBean.getSewage() != null) {
//                派单时间 指定任务人 任务接收时间 任务完成时间 任务描述 任务状态
                if(assignmentOrderBean.getSysUser().getUserName()!=null){
                    holder.tvSysuser.setText("指定任务人：".concat(assignmentOrderBean.getSysUser().getUserName()));
                }
                if(assignmentOrderBean.getSewage().getShortTitle()!=null){
                    holder.tvSewageInfo.setText("站点名称：".concat(assignmentOrderBean.getSewage().getShortTitle()));
                }
            }
            holder.tvAssignTime.setText("派单时间：".concat(CommonUtil.stampToStr2(assignmentOrderBean.getTaskDate())));
            if(assignmentOrderBean.getBeginTaskTime()==null||assignmentOrderBean.getBeginTaskTime()==0){
                holder.tvTaskStartTime.setText("任务接收时间：---");
            }else {
                holder.tvTaskStartTime.setText("任务接收时间：".concat(CommonUtil.stampToStr2(assignmentOrderBean.getBeginTaskTime())));
            }
            if(assignmentOrderBean.getEndTaskTime()==null||assignmentOrderBean.getEndTaskTime()==0){
                holder.tvTaskEndTime.setText("任务完成时间：---");
            }else {
                holder.tvTaskEndTime.setText("任务完成时间：".concat(CommonUtil.stampToStr2(assignmentOrderBean.getEndTaskTime())));
            }
            if(assignmentOrderBean.getTaskDescription()!=null){
                holder.tvTaskDescription.setText("任务描述：".concat(assignmentOrderBean.getTaskDescription()));
            }
            if(assignmentOrderBean.getTaskStatus()!=null){
                holder.tvTaskStatus.setText("任务状态：".concat(assignmentOrderBean.getTaskStatus()));
            }
        }
        return convertView;
    }

    private static class ViewHolder {
        private TextView tvAssignTime, tvSysuser, tvTaskStartTime, tvTaskEndTime, tvTaskDescription, tvTaskStatus, tvSewageInfo;
    }
}