package com.hospital.yilian.mobiledoctors.utils;

import android.content.Context;
import android.content.Intent;

import com.hc_smart.www.mobiledoctor.UI.home.UIActivity;

/**
 * Created by anhui on 2017/10/25.
 * email:anhuifix@163.com
 * Date:2017/10/25
 */

public class UIHelperUtils {


    /**
     * 前往主页
     * @param mContext
     */
    public static void  toUIActivity(Context mContext){
        Intent intent =new Intent(mContext, UIActivity.class);
        mContext.startActivity(intent);
    }
}
