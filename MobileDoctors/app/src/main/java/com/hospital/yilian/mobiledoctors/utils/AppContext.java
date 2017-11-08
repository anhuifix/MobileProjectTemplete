package com.hospital.yilian.mobiledoctors.utils;

import android.content.Context;

/**
 * @author  zhuangAH
 */
public class AppContext {
	private final static boolean isDebug = false;//BuildConfig.DEBUG;

	private static Context context = null;
	private static AppContext appContext = null;

	public AppContext() {
	}

	public AppContext(Context context) {
		AppContext.context = context;
	}

	public static AppContext getInstance(){
		if(appContext == null){
			appContext = new AppContext();
		}
		return appContext;
	}

	public static Context getAppContext(){
		return AppContext.context;
	}

	public static boolean isDebug(){
		return isDebug;
	}

}
