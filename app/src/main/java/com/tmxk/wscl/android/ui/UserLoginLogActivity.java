package com.tmxk.wscl.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.application.MainApplication;
import com.tmxk.wscl.android.mvp.model.UserBean;
import com.tmxk.wscl.android.mvp.presenter.UserPresenter;
import com.tmxk.wscl.android.mvp.view.UserView;
import com.tmxk.wscl.android.widget.DropDownMenuView;

import java.util.Objects;

import butterknife.BindView;

public class UserLoginLogActivity extends MvpActivity<UserPresenter> implements UserView {
    @BindView(R.id.dropDownMenu)
    DropDownMenuView dropDownMenu;
    @BindView(R.id.viewTop)
    Toolbar toolbar;
    @BindView(R.id.edtLoginName)
    EditText edtLoginName;
    @BindView(R.id.edtUserName)
    EditText edtUserName;
    @BindView(R.id.edtStartDate)
    EditText edtStartDate;
    @BindView(R.id.edtEndDate)
    EditText edtEndDate;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.listView)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_log);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        toolbar.setTitle("登陆日志");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //smart
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@android.support.annotation.NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getSysUserLoginLogs(
                        true,
                        edtLoginName.getText().toString().trim(),
                        edtUserName.getText().toString().trim(),
                        edtStartDate.getText().toString().trim(),
                        edtEndDate.getText().toString().trim()
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
                        edtStartDate.getText().toString().trim(),
                        edtEndDate.getText().toString().trim()
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

    @Override
    public void onRefresh() {

    }

    @Override
    public void getDataSuccess(Object model) {
        //set adapter
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
                }
                break;
            case R.id.btnSearch:
                if (dropDownMenu.isOpen()) {
                    dropDownMenu.close();
                }
                mvpPresenter.getSysUserLoginLogs(
                        true,
                        edtLoginName.getText().toString().trim(),
                        edtUserName.getText().toString().trim(),
                        edtStartDate.getText().toString().trim(),
                        edtEndDate.getText().toString().trim());
                break;
        }
    }
}