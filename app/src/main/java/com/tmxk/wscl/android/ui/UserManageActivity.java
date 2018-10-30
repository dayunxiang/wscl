package com.tmxk.wscl.android.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.ListView;

import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.adpter.UserListAdapter;
import com.tmxk.wscl.android.mvp.model.UserListBean;
import com.tmxk.wscl.android.mvp.presenter.UserPresenter;
import com.tmxk.wscl.android.mvp.view.UserView;

import java.util.Objects;

import butterknife.BindView;
import lombok.NonNull;

public class UserManageActivity extends MvpActivity<UserPresenter> implements UserView {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.listView)
    ListView listView;
    private UserListAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_manage);
        //toolbar
        StatusBarUtil.setColor(this, getResources().getColor(R.color.primary));
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("用户管理");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //smart
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableRefresh(true);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getSysUsers(true);
                refreshLayout.finishRefresh();
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getSysUsers(false);
                refreshLayout.finishLoadMore();
            }
        });
        refreshLayout.autoRefresh(500);
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(this);
    }

    @Override
    public void getDataSuccess(Object userList) {
        if (userList instanceof UserListBean) {
            if (userListAdapter != null) {
                userListAdapter.notifyDataSetChanged();
            } else {
                userListAdapter = new UserListAdapter(this, ((UserListBean) userList).getObject());
                listView.setAdapter(userListAdapter);
            }
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }
}
