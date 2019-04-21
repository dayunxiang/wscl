package com.tmxk.wscl.android.ui.operates;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.UploadPicResBean;
import com.tmxk.wscl.android.mvp.presenter.OperatePresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.CommonUtil;
import com.tmxk.wscl.android.util.Const;
import com.tmxk.wscl.android.util.Constant;
import com.tmxk.wscl.android.util.ImageUtils;
import com.tmxk.wscl.android.util.JumpNativeComponent;

import java.io.File;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class OperatePhotoActivity extends MvpActivity<OperatePresenter> implements SewageArchiveView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btnUpload)
    Button btnUpload;
    private static String FILE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_operate_photo);
        ButterKnife.bind(this);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        //init toolbar
        toolbar.setTitle("运维拍照");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopueWindow();
            }
        });
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
                JumpNativeComponent.startAlbum(OperatePhotoActivity.this, Const.ALBUM_CHOOSE_IMG, null);
                popupWindow.dismiss();

            }
        });
        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FILE_NAME = Environment.getExternalStorageDirectory() +CommonUtil.generateFileName()+".png";
                Uri imageUri = Uri.fromFile(new File( FILE_NAME ));
                Log.d("operatePhoto","path:" + FILE_NAME);
                JumpNativeComponent.startCamera(OperatePhotoActivity.this, Const.CAMERA_WITH_DATA, null, imageUri);
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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == Const.CAMERA_WITH_DATA) {
//           /* compressAnd2Base64(Environment.getExternalStorageDirectory()
//                    + "/temp.jpg");*/
//            compressImage(Environment.getExternalStorageDirectory() + "/temp.png");
//
//
//        } else if (resultCode == RESULT_OK && requestCode == Const.ALBUM_CHOOSE_IMG) {
//            ArrayList<String> list = BGAPhotoPickerActivity.getSelectedImages(data);
//            for (int i = 0; i < list.size(); i++) {
//                compressImage(list.get(i));
//            }
//        }
//    }

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
                mvpPresenter.uploadFile("1",CommonUtil.generateFileName1(),photo);
            }else if(requestCode == Const.CAMERA_WITH_DATA && !CommonUtil.isEmpty(FILE_NAME)){
                Log.d("operatePhoto","imgPath:"+FILE_NAME);
                File file = compressImage(FILE_NAME);
                RequestBody photoRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part photo = MultipartBody.Part.createFormData("file", file.getName(), photoRequestBody);
                mvpPresenter.uploadFile("1",CommonUtil.generateFileName1(),photo);
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
//        //threadPoolUtils.execute()为线程池的操作。
//        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//        singleThreadExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
                Bitmap bitmap = ImageUtils.compressImageFromFile(filePath, 1024f);// 按尺寸压缩图片
                int size = bitmap.getByteCount();
//                Logger.d(bitmap);
                Log.d("operatePhoto","size:"+size);
                File file = ImageUtils.compressImage(bitmap);  //按质量压缩图片
        return file;
//                File file = ImageUtils.bitmap2File(compressBitmap);
//                String fileSize = FileUtils.getFileSize(file);
//                upLoadPhotos(file);
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected OperatePresenter createPresenter() {
        return new OperatePresenter(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void getDataSuccess(Object model, DataTypeEnum dataTypeEnum) {
        if(model instanceof UploadPicResBean){
            UploadPicResBean uploadPicResBean = (UploadPicResBean) model;
            Log.d("operatePhoto","downloadUrl:" + uploadPicResBean.getDownloadUrl());
            toastShow("照片上传成功");
            finish();
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow("上传图片失败,请重试");
    }
}