package com.tmxk.wscl.android.ui.user;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.tmxk.wscl.android.adpter.AdminListAdapter;
import com.tmxk.wscl.android.adpter.DeviceDocListAdapter;
import com.tmxk.wscl.android.adpter.GirdDropDownAdapter;
import com.tmxk.wscl.android.adpter.UserListAdapter;
import com.tmxk.wscl.android.application.MainApplication;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.AdminListBean;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.SiteDeviceDocListBean;
import com.tmxk.wscl.android.mvp.model.UserBean;
import com.tmxk.wscl.android.mvp.model.UserListBean;
import com.tmxk.wscl.android.mvp.presenter.DeviceDocPresenter;
import com.tmxk.wscl.android.mvp.presenter.UserPresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.mvp.view.UserView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.ui.device.DeviceDocCreateActivity;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminActivity extends MvpActivity<UserPresenter> implements UserView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.adminRefreshLayout)
    RefreshLayout refreshLayout;
    private AdminListAdapter adminListAdapter;
    private ImageView imgRight;
    private static final int ADMIN_MANAGE_ADD_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_admin_manage);
        ButterKnife.bind(this);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initView();
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(this);
    }

    private void initView() {
        //init toolbar
        toolbar.setTitle("管理员配置");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        imgRight = toolbar.findViewById(R.id.imgRight);
        imgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AdminActivity.this, AdminCreateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("addAdmin", true);
                intent.putExtras(bundle);
                startActivityForResult(intent, ADMIN_MANAGE_ADD_REQUEST_CODE);
            }
        });
        //smart
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@android.support.annotation.NonNull RefreshLayout refreshLayout) {
                adminListAdapter = null;
                mvpPresenter.getAdminList(true);
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@android.support.annotation.NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getAdminList(false);
                refreshLayout.finishLoadMore();
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
        if (adminListAdapter == null) {
            return;
        }
        final AdminListBean.ObjectBean adminBean = ((AdminListBean.ObjectBean) adminListAdapter.getItem(position));
        if (adminBean != null) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            @SuppressLint("InflateParams")
            View contentView = inflater.inflate(R.layout.dialog_del, null);
            final PopupWindow popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setTouchable(true);
            popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
            ((TextView) contentView.findViewById(R.id.tv_tips)).setText("管理员".concat(adminBean.getName()).concat("删除后不可恢复"));
            contentView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    mvpPresenter.delAdminUser(adminBean.getId());
                }
            });
            contentView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
            if (!popupWindow.isShowing()) {
                popupWindow.showAtLocation(findViewById(R.id.adminParentView), Gravity.CENTER, 0, 0);
            } else {
                popupWindow.dismiss();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d("AdminActivity","onActivityResult-requestCode:"+requestCode);
        Log.d("AdminActivity","onActivityResult-resultCode:"+resultCode);
        if (requestCode == ADMIN_MANAGE_ADD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (intent.hasExtra("hasAddAdmin")) {
                adminListAdapter = null;
                Log.d("AdminActivity","onActivityResult: refresh");
                refreshLayout.autoRefresh();
            }
        }
    }

    @Override
    public void getDataSuccess(Object adminList, DataTypeEnum dataTypeEnum) {
        if (adminList instanceof AdminListBean) {
            if (adminListAdapter != null) {
                adminListAdapter.notifyDataSetChanged();
            } else {
                adminListAdapter = new AdminListAdapter(this, ((AdminListBean) adminList).getObject());
                listView.setAdapter(adminListAdapter);
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