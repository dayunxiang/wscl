package com.tmxk.wscl.android.ui.user;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.adpter.UserListAdapter;
import com.tmxk.wscl.android.application.MainApplication;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.UserBean;
import com.tmxk.wscl.android.mvp.model.UserListBean;
import com.tmxk.wscl.android.mvp.presenter.UserPresenter;
import com.tmxk.wscl.android.mvp.view.UserView;
import com.tmxk.wscl.android.ui.base.MvpActivity;

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
    private MainApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_manage);
        //status bar
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        //tool bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        ImageView imgRight = toolbar.findViewById(R.id.imgRight);
        toolbar.setTitle("用户管理");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        imgRight.setOnClickListener(new View.OnClickListener() {
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
        application = (MainApplication) getApplication();
        //smart
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@android.support.annotation.NonNull RefreshLayout refreshLayout) {
                userListAdapter = null;
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
                showDelDialog(position);
                return true;
            }
        });
        refreshLayout.autoRefresh();
    }

    private void showDelDialog(final int position) {
        if (userListAdapter == null) {
            return;
        }
        final UserBean userBean = ((UserBean) userListAdapter.getItem(position));
        if (userBean != null) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            @SuppressLint("InflateParams")
            View contentView = inflater.inflate(R.layout.dialog_del, null);
            final PopupWindow popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setTouchable(true);
            popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
            ((TextView) contentView.findViewById(R.id.tv_tips)).setText("用户".concat(userBean.getLoginName()).concat("删除后不可恢复"));
            contentView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    mvpPresenter.delUserInfo(userBean.getLoginName());
                }
            });
            contentView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
            if (!popupWindow.isShowing()) {
                popupWindow.showAtLocation(findViewById(R.id.parentView), Gravity.CENTER, 0, 0);
            } else {
                popupWindow.dismiss();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == USER_MANAGE_UPDATE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (intent.hasExtra("userBean") && intent.hasExtra("position")) {
                UserBean userBean = (UserBean) intent.getSerializableExtra("userBean");
                //update local login user data
                if (userBean.getLoginName().equals(application.getUserBean().getLoginName())) {
                    application.setUserBean(userBean);
                }
                //update remote user data
                if (userListAdapter != null) {
                    userListAdapter.updateUserList(
                            userBean,
                            intent.getIntExtra("position", -1));
                    userListAdapter.notifyDataSetChanged();
                }
            }
        } else if (requestCode == USER_MANAGE_ADD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (intent.hasExtra("hasAddUser")) {
                userListAdapter = null;
                refreshLayout.autoRefresh();
            }
        }
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(this);
    }

    @Override
    public void getDataSuccess(Object userList, DataTypeEnum dataTypeEnum) {
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
    public void onRefresh() {
        refreshLayout.autoRefresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
            mvpPresenter.onUnSubscribe();
            mvpPresenter = null;
        }
    }
}