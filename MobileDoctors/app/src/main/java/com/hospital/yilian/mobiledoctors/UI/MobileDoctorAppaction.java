package com.hospital.yilian.mobiledoctors.UI;

import android.app.Application;

import com.hc_smart.www.mobiledoctor.BuildConfig;
import com.hc_smart.www.mobiledoctor.utils.AppContext;
import com.hc_smart.www.mobiledoctor.utils.PathManager;
import com.socks.library.KLog;

import org.xutils.x;

/**
 * Created by anhui on 2017/11/7.
 * email:anhuifix@163.com
 * Date:2017/11/7
 */

public class MobileDoctorAppaction extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new AppContext(getApplicationContext());

        PathManager.init(this);
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }
}
