package com.hospital.yilian.mobiledoctors.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Date;

/**
 * 图片加载
 * Created by anhui on 2017/10/9.
 * email:anhuifix@163.com
 * Date:2017/10/9
 */

public  class ImageUtils {

    /**
     * 列表加载
     *
     * @param path
     * @param imageView
     */
    public static  void displayImage(String path, ImageView imageView){
        Glide.with(AppContext.getAppContext()).load(path)
                .asBitmap()
//                .placeholder(R.drawable.list_placeholder)
//                .error(R.drawable.list_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .fitCenter()
                .into(imageView);
    }

    /**
     * 评论预加载图
     * @param path
     * @param imageView
     */
    public static  void displayCommitImage(String path, ImageView imageView){
        Glide.with(AppContext.getAppContext()).load(path)
                .asBitmap()
//                .placeholder(R.drawable.comment_placeholder)
//                .error(R.drawable.comment_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }


    /**
     * 广告图
     * @param path
     * @param imageView
     */
    public static  void displayBannerImage(String path, ImageView imageView){
        Glide.with(AppContext.getAppContext()).load(path)
                .asBitmap()
//                .placeholder(R.drawable.banner_placeholder)
//                .error(R.drawable.banner_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    /**
     * 加载drawable资源
     * @param resource
     * @param imageView
     */
    public static  void displayBannerImage(int resource, ImageView imageView){
        // 加载应用资源
        Glide.with(AppContext.getAppContext())
                .load(resource)
//                .placeholder(R.drawable.banner_placeholder)
//                .error(R.drawable.banner_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    /**
     * 用户Icon
     * @param path
     * @param imageView
     */
    public static  void displayUserIConImage(String path, ImageView imageView){
        Glide.with(AppContext.getAppContext()).load(path)
                .asBitmap()
//                .placeholder(R.drawable.user_placeholder)
//                .error(R.drawable.user_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }


    /**
     * 生成图片名称
     *
     * @return
     */
    public static String getCameraImageName() {
        Date date = new Date();
        String name = String.valueOf(date.getTime());
        return name + ".jpg";
    }

    /**
     * 通过图库选图返回的uri获取图片路径
     */
    public static String findPathByUri(Context context, Uri uri) {
        String[] filepathColum = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, filepathColum, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filepathColum[0]);
        return cursor.getString(columnIndex);
    }
    /**
     * 获取图片目录路径
     */
    public static String getImgDir() {
        return PathManager.getImageDir();
    }
}
