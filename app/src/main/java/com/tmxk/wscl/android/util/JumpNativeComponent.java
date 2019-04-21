package com.tmxk.wscl.android.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

public class JumpNativeComponent {

    /**
     * 调用系统相册
     * @param activity
     * @param resultCode
     * @param bundle
     *
     */
    public static void startAlbum(Activity activity, int resultCode, @Nullable Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
//        activity.startActivityForResult(intent, FLAG_CHOOSE_IMG);
        startActivityForResult(activity, resultCode, bundle, intent);
    }


    /**
     * 调用系统相机
     * @param activity
     * @param resultCode
     * @param bundle
     * @param imageUri
     */
    public static void startCamera(Activity activity, int resultCode, @Nullable Bundle bundle, Uri imageUri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//        startActivityForResult(intent, CAMERA_WITH_DATA);
        startActivityForResult(activity, resultCode, bundle, intent);
    }

    /**
     * 跳转系统裁剪界面
     * @param activity
     * @param resultCode
     * @param bundle
     * @param imageUri
     */
    public static void startCrop(Activity activity, int resultCode, @Nullable Bundle bundle, Uri imageUri){
        // TODO 添加保存图片的宽高
        Intent intent = new Intent();
        intent.setAction("com.android.camera.action.CROP");
//      intent.setDataAndType(uri, "image/*");
//      intent.putExtra("crop", "true");
////        intent.putExtra("outputX", 300);  //裁剪图片的宽
////        intent.putExtra("outputY", 600);
//      intent.putExtra("aspectX", 1);  //裁剪方框宽的比例
//      intent.putExtra("aspectY", 1);
//      intent.putExtra("scale", true);  //是否保持比例
        intent.putExtra("return-data", false);  //是否返回bitmap
//
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);  //保存图片到指定uri
//      intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());  //输出格式

        intent.setDataAndType(imageUri,  "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高 用于保存并显示的大小
        intent.putExtra("outputX", 96);
        intent.putExtra("outputY", 96);
//      intent.putExtra("return-data", true);
//        startActivityForResult(intent, FLAG_MODIFY_FINISH);
        startActivityForResult(activity, resultCode, null, intent);
    }

    private static void startActivityForResult(Activity activity, int resultCode, @Nullable Bundle bundle, Intent intent) {
        if(bundle == null){
            activity.startActivityForResult(intent, resultCode);
        }else{
            activity.startActivityForResult(intent, resultCode, bundle);
        }
    }

}
