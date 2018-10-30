package com.tmxk.wscl.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.adpter.UserListAdapter;
import com.tmxk.wscl.android.mvp.model.UserBean;
import com.tmxk.wscl.android.mvp.model.UserListBean;
import com.tmxk.wscl.android.mvp.presenter.UserPresenter;
import com.tmxk.wscl.android.mvp.view.UserView;

import java.util.Objects;

import butterknife.BindView;

/**
 * Created by wjf on 18/10/30.
 * user manage page
 */
public class UserManageActivity extends MvpActivity<UserPresenter> implements UserView {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.listView)
    ListView listView;
    private UserListAdapter userListAdapter;
    private static final int USER_MANAGE_UPDATE_REQUEST_CODE = 0;
    private static final int USER_MANAGE_ADD_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_manage);
        //toolbar
        StatusBarUtil.setColor(this, getResources().getColor(R.color.primary));
        Toolbar toolbar = findViewById(R.id.toolbar);
        Button btnRight = toolbar.findViewById(R.id.btnRight);
        toolbar.setTitle("用户管理");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(UserManageActivity.this, UserInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("addUser", true);
                intent.putExtras(bundle);
                startActivityForResult(intent, USER_MANAGE_ADD_REQUEST_CODE);
            }
        });
        //smart
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@android.support.annotation.NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getSysUsers(true);
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@android.support.annotation.NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getSysUsers(false);
                refreshLayout.finishLoadMore();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(UserManageActivity.this, UserInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("userBean", (UserBean) userListAdapter.getItem(position));
                bundle.putInt("position", position);
                intent.putExtras(bundle);
                startActivityForResult(intent, USER_MANAGE_UPDATE_REQUEST_CODE);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mvpPresenter.delUserInfo(((UserBean) userListAdapter.getItem(position)).getLoginName());
                return true;
            }
        });
        refreshLayout.autoRefresh();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == USER_MANAGE_UPDATE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (intent.hasExtra("userBean") && intent.hasExtra("position")) {
                userListAdapter.updateUserList(
                        (UserBean) intent.getSerializableExtra("userBean"),
                        intent.getIntExtra("position", -1));
                userListAdapter.notifyDataSetChanged();
            }
        } else if (requestCode == USER_MANAGE_ADD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (intent.hasExtra("hasAddUser")) {
                refreshLayout.autoRefresh();
            }
        }
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

    @Override
    public void autoRefresh() {
        refreshLayout.autoRefresh();
    }
}
