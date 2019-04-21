package com.tmxk.wscl.android.ui.operates;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
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
import com.tmxk.wscl.android.adpter.GirdDropDownAdapter;
import com.tmxk.wscl.android.adpter.InspectionInfoListAdapter;
import com.tmxk.wscl.android.adpter.RepairmentListAdapter;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.InspectionInfoListBean;
import com.tmxk.wscl.android.mvp.model.InspectionUrls;
import com.tmxk.wscl.android.mvp.model.RepairmentBean;
import com.tmxk.wscl.android.mvp.model.RepairmentListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.UploadPicResBean;
import com.tmxk.wscl.android.mvp.presenter.OperatePresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.CommonUtil;
import com.tmxk.wscl.android.util.Const;
import com.tmxk.wscl.android.util.ImageUtils;
import com.tmxk.wscl.android.util.JumpNativeComponent;
import com.tmxk.wscl.android.widget.DropDownMenuView;
import com.yyydjk.library.DropDownMenu;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by wy on 19/04/20.
 * user manage page
 */
public class InspectionInfoActivity extends MvpActivity<OperatePresenter> implements SewageArchiveView {
    private int SEWAGE_ID=0;
    private int INSPECTION_INFO_ID=0;
    private InspectionInfoListAdapter inspectionInfoListAdapter;
    private TimePickerView pvTime;
    private int timerPickerPos = -1;
    private static String FILE_NAME;
    private static String SEWAGE_NAME="";
    private static String SEWAGE_OPERATE_NO="";

    private GirdDropDownAdapter regionAdapter;
    private GirdDropDownAdapter stationAdapter;
    private List<String> regions = new ArrayList<>();
    private List<String> sewages = new ArrayList<>();
    private List<Integer> sewageIds = new ArrayList<>();
    ListView regionView;
    ListView stationView;
    private String headers[] = {};
    private List<View> popupViews = new ArrayList<>();
    private FrameLayout parentView;
    private SmartRefreshLayout refreshLayout;
    private ListView listView;
    private DropDownMenuView dropDownMenu;
    private Toolbar toolbar;
    private Button btnFilter;
    private TextView tv1;
    private DropDownMenu areaDropDownMenu;
    private TextView tv2;
    private Button btnStartDate;
    private TextView tv3;
    private Button btnEndDate;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_inspection_info_list);
        //status bar
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initView();
        //tool bar
        toolbar.setTitle("巡检查询");
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
                inspectionInfoListAdapter = null;
                String start;
                String end;
                if (("不限").equals(btnStartDate.getText().toString().trim()) || ("").equals(btnStartDate.getText().toString().trim())) {
                    start = "2000-01-01 00:00:00.000";
                } else {
                    start = btnStartDate.getText().toString().trim() + " 00:00:00.000";
                }
                if (("不限").equals(btnEndDate.getText().toString().trim()) || ("").equals(btnEndDate.getText().toString().trim())) {
                    end = "2100-01-01 00:00:00.000";
                } else {
                    end = btnEndDate.getText().toString().trim() + " 00:00:00.000";
                }
                if (SEWAGE_ID != 0) {
                    mvpPresenter.getInspectionCondition(true, SEWAGE_ID, start, end);
                } else {
                    mvpPresenter.getInspectionCondition(true, start, end);
                }
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                String start;
                String end;
                if (("不限").equals(btnStartDate.getText().toString().trim()) || ("").equals(btnStartDate.getText().toString().trim())) {
                    start = "2000-01-01 00:00:00.000";
                } else {
                    start = btnStartDate.getText().toString().trim() + " 00:00:00.000";
                }
                if (("不限").equals(btnEndDate.getText().toString().trim()) || ("").equals(btnEndDate.getText().toString().trim())) {
                    end = "2100-01-01 00:00:00.000";
                } else {
                    end = btnEndDate.getText().toString().trim() + " 23:59:59.999";
                }
                if (SEWAGE_ID != 0) {
                    mvpPresenter.getInspectionCondition(false, SEWAGE_ID, start, end);
                } else {
                    mvpPresenter.getInspectionCondition(false, start, end);
                }
                refreshLayout.finishLoadMore();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //展示图片上传
                showModifyDialog(position);
                return true;
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
        if (object instanceof InspectionInfoListBean) {
            if (inspectionInfoListAdapter != null) {
                List<InspectionInfoListBean.ObjectBean> inpectionInfoBeans = inspectionInfoListAdapter.getList();
                inpectionInfoBeans.addAll(((InspectionInfoListBean) object).getObject());
                inspectionInfoListAdapter.setList(inpectionInfoBeans);
                inspectionInfoListAdapter.notifyDataSetChanged();
            } else {
                inspectionInfoListAdapter = new InspectionInfoListAdapter(this, ((InspectionInfoListBean) object).getObject());
                listView.setAdapter(inspectionInfoListAdapter);
            }
        } else if (object instanceof AreaListBean) {
            Log.d("RepairDealActivity", "AreaListBean " + object.toString());
            AreaListBean areaListBean = (AreaListBean) object;
            //init region menu
            final List<Integer> regionIds = new ArrayList<>();
            if (areaListBean != null && areaListBean.getObject().size() > 0) {
                for (AreaListBean.AreaBean areaBean : areaListBean.getObject()) {
                    regions.add(areaBean.getName());
                    regionIds.add(areaBean.getId());
                }
            }
            ListView regionView = new ListView(this);
            regionAdapter = new GirdDropDownAdapter(this, regions);
            regionView.setDividerHeight(0);
            regionView.setAdapter(regionAdapter);
            popupViews.add(regionView);
            stationView.setDividerHeight(0);
            stationAdapter = new GirdDropDownAdapter(this, sewages);
            stationView.setAdapter(stationAdapter);
            //init popupViews
            popupViews.add(stationView);
            headers = new String[]{"请选择区县", "请选择站点"};

            //init context view
            TextView contentView = new TextView(this);
            contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            contentView.setGravity(Gravity.CENTER);
            contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            //init dropDownView
            if (areaDropDownMenu != null) {
                areaDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
            }

            regionView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("SewageArchiveActivity", "onItemClick " + position);
                    Log.d("SewageArchiveActivity", "onItemClick-regions " + regions.get(position));
                    regionAdapter.setCheckItem(position);
                    areaDropDownMenu.setTabText(regions.get(position));
                    //根据区域获取站点
                    mvpPresenter.getSewages(true, regionIds.get(position));
                    areaDropDownMenu.closeMenu();
                }
            });
        } else if (object instanceof SewageListBean) {
            final SewageListBean sewageListBean = (SewageListBean) object;
            Log.d("SewageArchiveActivity", "SewageListBean " + object.toString());
            if (sewageListBean != null && sewageListBean.getObject().size() > 0) {
                sewages.clear();
                sewageIds.clear();
                for (SewageListBean.ObjectBean sewageBean : sewageListBean.getObject()) {
                    sewages.add(sewageBean.getName());
                    sewageIds.add(sewageBean.getId());
                }
            }
            //init context view
            TextView contentView = new TextView(this);
            contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            contentView.setGravity(Gravity.CENTER);
            contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            //init dropDownView
            if (areaDropDownMenu != null) {
                areaDropDownMenu.refreshDrawableState();
            }
            stationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    stationAdapter.setCheckItem(position);
                    areaDropDownMenu.setTabText(sewages.get(position));
                    SEWAGE_ID = sewageIds.get(position);
                    areaDropDownMenu.closeMenu();
                }
            });
        }else if(object instanceof UploadPicResBean){
            UploadPicResBean uploadPicResBean = (UploadPicResBean) object;
            Log.d("operatePhoto","downloadUrl:" + uploadPicResBean.getDownloadUrl());
            toastShow("照片上传成功");
            //记录巡检图片表信息
            mvpPresenter.createInspectionUrl(new InspectionUrls(new InspectionInfoListBean.ObjectBean(INSPECTION_INFO_ID),
                    uploadPicResBean.getDownloadUrl()));
        }else if(object instanceof InspectionUrls){
            toastShow("照片记录成功");
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
                    inspectionInfoListAdapter = null;
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

    private void initData() {
        //init region menu
        regionView = new ListView(this);
        stationView = new ListView(this);
        //获取区县
        mvpPresenter.getAllAreas();
    }

    private void showModifyDialog(final int position) {
        if (inspectionInfoListAdapter == null) {
            return;
        }
        final InspectionInfoListBean.ObjectBean inspectionInfoBean = ((InspectionInfoListBean.ObjectBean) inspectionInfoListAdapter.getItem(position));
        if (inspectionInfoBean != null) {
            //处理照片弹窗
            INSPECTION_INFO_ID = inspectionInfoBean.getId();
            if(inspectionInfoBean.getSewage()!=null){
                if(inspectionInfoBean.getSewage().getOperationNum()!=null){
                    SEWAGE_OPERATE_NO = inspectionInfoBean.getSewage().getOperationNum();
                }
                if(inspectionInfoBean.getSewage().getShortTitle()!=null){
                    SEWAGE_NAME = inspectionInfoBean.getSewage().getOperationNum();
                }
            }

            SEWAGE_NAME = inspectionInfoBean.getSewage().getName();
            showPopueWindow();
        }
    }



    private void showPopueWindow(){
        View popView = View.inflate(this,R.layout.popupwindow_camera_need,null);
        Button bt_album = (Button) popView.findViewById(R.id.btn_pop_album);
        Button bt_camera = (Button) popView.findViewById(R.id.btn_pop_camera);
        Button bt_cancle = (Button) popView.findViewById(R.id.btn_pop_cancel);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels*1/3;

        final PopupWindow popupWindow = new PopupWindow(popView,weight,height);
//        popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);

        bt_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpNativeComponent.startAlbum(InspectionInfoActivity.this, Const.ALBUM_CHOOSE_IMG, null);
                popupWindow.dismiss();

            }
        });
        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FILE_NAME = Environment.getExternalStorageDirectory() + CommonUtil.generateFileName()+".jpg";
                Uri imageUri = Uri.fromFile(new File( FILE_NAME ));
                Log.d("operatePhoto","path:"+ FILE_NAME);
                JumpNativeComponent.startCamera(InspectionInfoActivity.this, Const.CAMERA_WITH_DATA, null, imageUri);
                popupWindow.dismiss();

            }
        });
        bt_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM,0,50);

    }

    //当图片被选中的返回结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            Log.d("operatePhoto","requestCode:"+requestCode);
            if (requestCode == Const.ALBUM_CHOOSE_IMG) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                // 获取游标
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgPath = cursor.getString(columnIndex);
                cursor.close();
                Log.d("operatePhoto","imgPath:"+imgPath);
//                ImageView imgView = (ImageView) findViewById(R.id.imageView);
//                imgView.setImageBitmap(BitmapFactory.decodeFile(imgPath));
                File file = compressImage(imgPath);
                RequestBody photoRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part photo = MultipartBody.Part.createFormData("file", file.getName(), photoRequestBody);
                //文件夹名称为年月日+站点名称+运营编号
                mvpPresenter.uploadFile(CommonUtil.generateDay() +"_"+ SEWAGE_NAME +"_"+ SEWAGE_OPERATE_NO ,CommonUtil.generateFileName1(),photo);
            }else if(requestCode == Const.CAMERA_WITH_DATA && !CommonUtil.isEmpty(FILE_NAME)){
                Log.d("operatePhoto","imgPath:"+FILE_NAME);
                File file = compressImage(FILE_NAME);
                RequestBody photoRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part photo = MultipartBody.Part.createFormData("file", file.getName(), photoRequestBody);
                mvpPresenter.uploadFile(CommonUtil.generateDay() +"_"+ SEWAGE_NAME +"_"+ SEWAGE_OPERATE_NO,CommonUtil.generateFileName1(),photo);
            } else {
                toastShow("请选择图片");
            }
        } catch (Exception e) {
            toastShow("上传照片异常,请重试");
            Log.e("operatePhoto","exception:"+e.toString());
            e.printStackTrace();
        }
    }

    /**
     * 压缩
     *
     * @param filePath 文件路径
     */
    private File compressImage(final String filePath) {
        Bitmap bitmap = ImageUtils.compressImageFromFile(filePath, 1024f);// 按尺寸压缩图片
        int size = bitmap.getByteCount();
        Log.d("operatePhoto","size:"+size);
        File file = ImageUtils.compressImage(bitmap);  //按质量压缩图片
        return file;
    }

    private void initView() {
        parentView = (FrameLayout) findViewById(R.id.parentView);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        listView = (ListView) findViewById(R.id.listView);
        dropDownMenu = (DropDownMenuView) findViewById(R.id.dropDownMenu);
        toolbar = (Toolbar) findViewById(R.id.viewTop);
        btnFilter = (Button) findViewById(R.id.btnFilter);
        tv1 = (TextView) findViewById(R.id.tv1);
        areaDropDownMenu = (DropDownMenu) findViewById(R.id.areaDropDownMenu);
        tv2 = (TextView) findViewById(R.id.tv2);
        btnStartDate = (Button) findViewById(R.id.btnStartDate);
        tv3 = (TextView) findViewById(R.id.tv3);
        btnEndDate = (Button) findViewById(R.id.btnEndDate);
        btnSearch = (Button) findViewById(R.id.btnSearch);
    }
}