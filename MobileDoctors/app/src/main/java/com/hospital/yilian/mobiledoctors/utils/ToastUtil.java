package com.hospital.yilian.mobiledoctors.utils;

import android.widget.Toast;


public class ToastUtil {

    /**
     * 长时间提示信息显示
     */
    public static void Long(String text) {
        Toast.makeText(AppContext.getAppContext(), text, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间提示信息显示
     */
    public static void Long(int srcId) {
        Toast.makeText(AppContext.getAppContext(), srcId, Toast.LENGTH_LONG).show();
    }

    /**
     * 短时间提示信息显示
     */
    public static void Short(String text) {
        Toast.makeText(AppContext.getAppContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间提示信息显示
     */
    public static void Short(int srcId) {
        Toast.makeText(AppContext.getAppContext(), srcId, Toast.LENGTH_SHORT).show();
    }


}
