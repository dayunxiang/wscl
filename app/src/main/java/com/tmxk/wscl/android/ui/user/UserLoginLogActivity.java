package com.tmxk.wscl.android.ui.user;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.adpter.LoginLogListAdapter;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.AdminListBean;
import com.tmxk.wscl.android.mvp.model.UserLoginLogBean;
import com.tmxk.wscl.android.mvp.model.UserLoginLogListBean;
import com.tmxk.wscl.android.mvp.presenter.UserPresenter;
import com.tmxk.wscl.android.mvp.view.UserView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.CommonUtil;
import com.tmxk.wscl.android.widget.DropDownMenuView;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * Created by wjf on 18/11/01.
 * user manage page
 */
public class UserLoginLogActivity extends MvpActivity<UserPresenter> implements UserView {
    @BindView(R.id.dropDownMenu)
    DropDownMenuView dropDownMenu;
    @BindView(R.id.viewTop)
    Toolbar toolbar;
    @BindView(R.id.edtLoginName)
    EditText edtLoginName;
    @BindView(R.id.edtUserName)
    EditText edtUserName;
    @BindView(R.id.btnStartDate)
    Button btnStartDate;
    @BindView(R.id.btnEndDate)
    Button btnEndDate;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.listView)
    ListView listView;
    private TimePickerView pvTime;
    private int timerPickerPos = -1;
    private LoginLogListAdapter loginLogListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_log);
        //status bar
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        //tool bar
        toolbar.setTitle("登陆日志");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //time picker
        initTimePicker();
        //smart
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@android.support.annotation.NonNull RefreshLayout refreshLayout) {
                loginLogListAdapter = null;
                mvpPresenter.getSysUserLoginLogs(
                        true,
                        edtLoginName.getText().toString().trim(),
                        edtUserName.getText().toString().trim(),
                        btnStartDate.getText().toString().trim(),
                        btnEndDate.getText().toString().trim()
                );
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@android.support.annotation.NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getSysUserLoginLogs(
                        false,
                        edtLoginName.getText().toString().trim(),
                        edtUserName.getText().toString().trim(),
                        btnStartDate.getText().toString().trim(),
                        btnEndDate.getText().toString().trim()
                );
                refreshLayout.finishLoadMore();
            }
        });
        refreshLayout.autoRefresh();

    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(this);
    }

    private void initTimePicker() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (timerPickerPos == 0) {
                    btnStartDate.setText(CommonUtil.getTime(date));
                } else if (timerPickerPos == 1) {
                    btnEndDate.setText(CommonUtil.getTime(date));
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
    public void getDataSuccess(Object userLoginLogList, DataTypeEnum dataTypeEnum) {
        if (userLoginLogList instanceof UserLoginLogListBean) {
            if (loginLogListAdapter != null) {
                List<UserLoginLogBean> userLoginLogBeans = loginLogListAdapter.getList();
                userLoginLogBeans.addAll(((UserLoginLogListBean) userLoginLogList).getObject());
                loginLogListAdapter.setList(userLoginLogBeans);
                loginLogListAdapter.notifyDataSetChanged();
            } else {
                loginLogListAdapter = new LoginLogListAdapter(this, ((UserLoginLogListBean) userLoginLogList).getObject());
                listView.setAdapter(loginLogListAdapter);
            }
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
                } else {
                    dropDownMenu.close();
                    hideInputMethod();
                }
                break;
            case R.id.btnSearch:
                if (dropDownMenu.isOpen()) {
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
}