package com.tmxk.wscl.android.ui.operates;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.adpter.AssignOrderListAdapter;
import com.tmxk.wscl.android.adpter.GirdDropDownAdapter;
import com.tmxk.wscl.android.adpter.LoginLogListAdapter;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.AssignmentOrderListBean;
import com.tmxk.wscl.android.mvp.model.CarInfoResBean;
import com.tmxk.wscl.android.mvp.model.UserBean;
import com.tmxk.wscl.android.mvp.model.UserListBean;
import com.tmxk.wscl.android.mvp.model.UserLoginLogBean;
import com.tmxk.wscl.android.mvp.model.UserLoginLogListBean;
import com.tmxk.wscl.android.mvp.presenter.OperatePresenter;
import com.tmxk.wscl.android.mvp.presenter.UserPresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.CommonUtil;
import com.tmxk.wscl.android.widget.DropDownMenuView;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by wjf on 18/11/01.
 * user manage page
 */
public class AssignOrderActivity extends MvpActivity<OperatePresenter> implements SewageArchiveView {
    private TimePickerView pvTime;
    private int timerPickerPos = -1;
    private AssignOrderListAdapter assignOrderListAdapter;
    private FrameLayout parentView;
    private SmartRefreshLayout refreshLayout;
    private ListView listView;
    private DropDownMenuView dropDownMenu;
    private Toolbar toolbar;
    private Button btnFilter;
    private LinearLayout ll1;
    private TextView tv1;
    private DropDownMenu taskStatusDropDownMenu;
    private LinearLayout ll2;
    private TextView tv2;
    private DropDownMenu sysuserDropDownMenu;
    private LinearLayout ll3;
    private TextView tv3;
    private Button btnStartDate;
    private LinearLayout ll4;
    private TextView tv4;
    private Button btnEndDate;
    private Button btnSearch;
    private static boolean SYSUSER_INFO=true;
    private String userHeaders[] = new String[]{"请选择指定任务人"};
    private String tastkStatusHeaders[] = new String[]{"请选择任务状态"};
    private List<View> userPopupViews = new ArrayList<>();
    private List<View> tastkStatusPopupViews = new ArrayList<>();
    private GirdDropDownAdapter userAdapter;
    private GirdDropDownAdapter tastkStatusAdapter;
    private List<String> userName = new ArrayList<>();
    private List<Integer> userId = new ArrayList<>();
    private List<String> taskStatus = new ArrayList<>();
    private int SYSUSER_ID=0;
    private String TASK_STATUS="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_assginment_order_list);
        //status bar
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initView();
        //tool bar
        toolbar.setTitle("派单查询");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //time picker
        initTimePicker();
        initData();
        //smart
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                assignOrderListAdapter = null;
                String start;
                String end;
                if(("不限").equals(btnStartDate.getText().toString().trim())||("").equals(btnStartDate.getText().toString().trim())){
                    start = "2000-01-01 00:00:00.000";
                }else{
                    start = btnStartDate.getText().toString().trim()+" 00:00:00.000";
                }
                if(("不限").equals(btnEndDate.getText().toString().trim())||("").equals(btnEndDate.getText().toString().trim())){
                    end = "2100-01-01 00:00:00.000";
                }else{
                    end = btnEndDate.getText().toString().trim()+" 00:00:00.000";
                }
                if(SYSUSER_ID!=0 && !("").equals(TASK_STATUS)){
                    mvpPresenter.getAssignOrderByCondition(true, SYSUSER_ID, TASK_STATUS, start, end);
                }else if(SYSUSER_ID!=0 && ("").equals(TASK_STATUS)){
                    mvpPresenter.getAssignOrderByCondition(true, SYSUSER_ID, start, end);
                }else if(SYSUSER_ID==0 && !("").equals(TASK_STATUS)){
                    mvpPresenter.getAssignOrderByCondition(true, TASK_STATUS, start, end);
                }else if(SYSUSER_ID==0 && ("").equals(TASK_STATUS)){
                    mvpPresenter.getAssignOrderByCondition(true, start, end);
                }else {
                    mvpPresenter.getAllAssignOrder(true);
                }
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                String start;
                String end;
                if(("不限").equals(btnStartDate.getText().toString().trim())||("").equals(btnStartDate.getText().toString().trim())){
                    start = "2000-01-01 00:00:00.000";
                }else{
                    start = btnStartDate.getText().toString().trim()+" 00:00:00.000";
                }
                if(("不限").equals(btnEndDate.getText().toString().trim())||("").equals(btnEndDate.getText().toString().trim())){
                    end = "2100-01-01 00:00:00.000";
                }else{
                    end = btnEndDate.getText().toString().trim()+" 23:59:59.999";
                }
                if(SYSUSER_ID!=0 && !("").equals(TASK_STATUS)){
                    mvpPresenter.getAssignOrderByCondition(false, SYSUSER_ID, TASK_STATUS, start, end);
                }else if(SYSUSER_ID!=0 && ("").equals(TASK_STATUS)){
                    mvpPresenter.getAssignOrderByCondition(false, SYSUSER_ID, start, end);
                }else if(SYSUSER_ID==0 && !("").equals(TASK_STATUS)){
                    mvpPresenter.getAssignOrderByCondition(false, TASK_STATUS, start, end);
                }else if(SYSUSER_ID==0 && ("").equals(TASK_STATUS)){
                    mvpPresenter.getAssignOrderByCondition(false, start, end);
                }else {
                    mvpPresenter.getAllAssignOrder(false);
                }
                refreshLayout.finishLoadMore();
            }
        });
        refreshLayout.autoRefresh();
    }

    @Override
    protected OperatePresenter createPresenter() {
        return new OperatePresenter(this);
    }

    private void initTimePicker() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (timerPickerPos == 0) {
                    btnStartDate.setText(CommonUtil.getTimeByDay(date));
                } else if (timerPickerPos == 1) {
                    btnEndDate.setText(CommonUtil.getTimeByDay(date));
                }
                timerPickerPos = -1;
                Log.i("pvTime", "onTimeSelect");
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setOutSideCancelable(true)
                .setCancelColor(Color.parseColor("#333333"))
                .setSubmitColor(Color.parseColor("#333333"))
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true)
                .build();
        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);
                dialogWindow.setGravity(Gravity.BOTTOM);
            }
        }
    }

    private void hideInputMethod() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && inputMethodManager.isActive()) {
            inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onRefresh() {
        refreshLayout.autoRefresh();
    }

    @Override
    public void getDataSuccess(Object object, DataTypeEnum dataTypeEnum) {
        if (object instanceof AssignmentOrderListBean) {
            if (assignOrderListAdapter != null) {
                List<AssignmentOrderListBean.ObjectBean> assignmentOrderBeans = assignOrderListAdapter.getList();
                assignmentOrderBeans.addAll(((AssignmentOrderListBean) object).getObject());
                assignOrderListAdapter.setList(assignmentOrderBeans);
                assignOrderListAdapter.notifyDataSetChanged();
            } else {
                assignOrderListAdapter = new AssignOrderListAdapter(this, ((AssignmentOrderListBean) object).getObject());
                listView.setAdapter(assignOrderListAdapter);
            }
        }else if(object instanceof UserListBean){
            SYSUSER_INFO=false;
            userHeaders = new String[]{"请选择指定任务人"};
            userPopupViews = new ArrayList<>();
            UserListBean userListBean = (UserListBean) object;
            if(userListBean!=null&&userListBean.getObject().size()>0){
                for(UserBean userBean:userListBean.getObject()){
                    userName.add(userBean.getUserName());
                    userId.add(userBean.getId());
                }
            }
            ListView userView = new ListView(this);
            userAdapter = new GirdDropDownAdapter(this, userName);
            userView.setDividerHeight(0);
            userView.setAdapter(userAdapter);
            userPopupViews.add(userView);

            //init context view
            TextView contentView = new TextView(this);
            contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            contentView.setGravity(Gravity.CENTER);
            contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            //init dropDownView
            if (sysuserDropDownMenu != null) {
                sysuserDropDownMenu.setDropDownMenu(Arrays.asList(userHeaders), userPopupViews, contentView);
            }

            userView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("AssignOrderActi","onItemClick "+position);
                    userAdapter.setCheckItem(position);
                    sysuserDropDownMenu.setTabText(userName.get(position));
                    SYSUSER_ID = userId.get(position);
                    sysuserDropDownMenu.closeMenu();
                }
            });
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnFilter:
                if (!dropDownMenu.isOpen()) {
                    dropDownMenu.open();
                    if(SYSUSER_INFO){
                        mvpPresenter.getAllSysuser();
                    }
                } else {
                    dropDownMenu.close();
                    hideInputMethod();
                }
                break;
            case R.id.btnSearch:
                if (dropDownMenu.isOpen()) {
                    assignOrderListAdapter = null;
                    dropDownMenu.close();
                    hideInputMethod();
                }
                refreshLayout.autoRefresh();
                break;
            case R.id.btnStartDate:
                if (pvTime != null) {
                    timerPickerPos = 0;
                    pvTime.show(view);
                }
                break;
            case R.id.btnEndDate:
                if (pvTime != null) {
                    timerPickerPos = 1;
                    pvTime.show(view);
                }
                break;
        }
    }

    private void initView() {
        parentView = (FrameLayout) findViewById(R.id.parentView);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        listView = (ListView) findViewById(R.id.listView);
        dropDownMenu = (DropDownMenuView) findViewById(R.id.dropDownMenu);
        toolbar = (Toolbar) findViewById(R.id.viewTop);
        btnFilter = (Button) findViewById(R.id.btnFilter);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        tv1 = (TextView) findViewById(R.id.tv1);
        taskStatusDropDownMenu = (DropDownMenu) findViewById(R.id.taskStatusDropDownMenu);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        tv2 = (TextView) findViewById(R.id.tv2);
        sysuserDropDownMenu = (DropDownMenu) findViewById(R.id.sysuserDropDownMenu);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        tv3 = (TextView) findViewById(R.id.tv3);
        btnStartDate = (Button) findViewById(R.id.btnStartDate);
        ll4 = (LinearLayout) findViewById(R.id.ll4);
        tv4 = (TextView) findViewById(R.id.tv4);
        btnEndDate = (Button) findViewById(R.id.btnEndDate);
        btnSearch = (Button) findViewById(R.id.btnSearch);
    }

    private void initData(){
        tastkStatusHeaders = new String[]{"请选择指定任务人"};
        tastkStatusPopupViews = new ArrayList<>();
        taskStatus.add("已分配");
        taskStatus.add("处理中");
        taskStatus.add("已完成");
        ListView tastkStatusView = new ListView(this);
        tastkStatusAdapter = new GirdDropDownAdapter(this, taskStatus);
        tastkStatusView.setDividerHeight(0);
        tastkStatusView.setAdapter(tastkStatusAdapter);
        tastkStatusPopupViews.add(tastkStatusView);

        //init context view
        TextView contentView = new TextView(this);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        //init dropDownView
        if (taskStatusDropDownMenu != null) {
            taskStatusDropDownMenu.setDropDownMenu(Arrays.asList(tastkStatusHeaders), tastkStatusPopupViews, contentView);
        }

        tastkStatusView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("AssignOrderActi","onItemClick "+position);
                tastkStatusAdapter.setCheckItem(position);
                taskStatusDropDownMenu.setTabText(taskStatus.get(position));
                TASK_STATUS = taskStatus.get(position);
                taskStatusDropDownMenu.closeMenu();
            }
        });
    }
}