package com.hospital.yilian.mobiledoctors.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * 路径管理类
 * @author zhuangAH
 * @date 2015-5-19
 */
public class PathManager {

    /** 数据文件名后缀 */
	public final static String FILE_SUFFIX = ".dat";

    /** App所在目录 */
    public static String sAppDir = null;

    /** 应用程序包内数据 */
    public static String localDir = null;


    /**
     * 初始化应用目录
     *
     * @author xiayb
     * @date 2015-4-27
     */
    public static void init(Context context) {
        // 应用程序路径
         sAppDir = Environment.getExternalStorageDirectory().getPath() + "/superMa";
         mkdirs(sAppDir);
         localDir = context.getFilesDir().getPath();
         String sPicDir = ImageUtils.getImgDir();
         mkdirs(sPicDir);
         Log.i("test", localDir+" "+sAppDir+" "+sPicDir);
    }


    /**
     * 获取apk下载目录
     * @return
     */
    public static String getDownloadApkDir(){
    	String downloadApkDir = sAppDir+"/download";
    	mkdirs(downloadApkDir);
    	return downloadApkDir;
    }


    /**
     * 获取订单生成的模板目录
     * @return
     */
    public static String getTemplateDir(){
    	String downloadApkDir = sAppDir+"/template-cache";
    	mkdirs(downloadApkDir);
    	return downloadApkDir;
    }

    /**
     * 获取二维码的目录
     */
    public static String getQrCodeDir(){
    	String downloadApkDir = sAppDir+"/data/qrCode";
    	mkdirs(downloadApkDir);
    	return downloadApkDir;
    }

    /**
     * 获取apk下载路径
     * @param versionName
     * @return
     */
    public static String getDownloadApkPath(String versionName){
    	String downloadApkPath = getDownloadApkDir() + "/ccPuls-"+versionName+".apk";
    	return downloadApkPath;
    }

    /**
     * 获取apk下载路径(缓存文件)
     * @param versionName
     * @return
     */
    public static String getDownloadApkCachePath(String versionName){
    	String downloadApkPath = getDownloadApkDir() + "/ccPuls-"+versionName+".cache";
    	return downloadApkPath;
    }


    /**
     * 获取订单生产模板的存储路径
     * @return
     */
    public static String getPrintTemplateInfoPath(){
    	String printTemplateCachePath = getTemplateDir() + "/templateinfo"+FILE_SUFFIX;
    	return printTemplateCachePath;
    }

    /**
     * 获取订单生产的模板路径
     */
    public static String getGeneratePrintTemplatePath(String fileName){
    	String path = getTemplateDir() + "/"+fileName+FILE_SUFFIX;
    	return path;
    }

    /**
     * 获取二维码图片的路径
     */
    public static String getQrCodePath(String name){
    	String path = getQrCodeDir() + "/" + name;
    	return path;
    }

    /**
     * 图片Dir
     * @return
     */
    public static String getImageDir(){
    	String imageDir = sAppDir + "/data/picture";
    	mkdirs(imageDir);
        Log.i("test",imageDir);
    	return imageDir;
    }

    /**
     * 图片Dir
     * @return
     */
    public static String getInfoDir(){
        String imageDir = sAppDir + "/info";
        mkdirs(imageDir);
        return imageDir;
    }

    /**
     * 图片Dir
     * @return
     */
    public static String getImagePath(String fileName){
    	String imageDir = getImageDir()+ File.separator+fileName;
    	return imageDir;
    }

    /**
     * 图片Dir
     * @return
     */
    public static String getTempImageDir(){
    	String imageDir = sAppDir + "/data/Temp";
    	mkdirs(imageDir);
    	return imageDir;
    }

    /**
     * 获取图片路径
     *
     * @param fileName
     * @return
     * @author xiayb
     * @date 2015-4-27
     */
    public static String getImgCachePath(String fileName) {
        return getImageDir() + File.separator + fileName;
    }

    /**
     * 图片Dir
     * @return
     */
    public static String getTempImagePath(String name){
    	String imageDir = sAppDir + "/data/Temp/"+name;
    	mkdirs(imageDir);
    	return imageDir;
    }

    /**
     * 创建目录
     */
    public static void mkdirs(String path){
    	File fileDir = new File(path);
    	//目录不存在,创建目录
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
    }

}
