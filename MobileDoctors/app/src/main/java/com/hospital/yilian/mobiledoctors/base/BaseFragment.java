package com.hospital.yilian.mobiledoctors.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.hc_smart.www.mobiledoctor.utils.NetworkUtils;
import com.hc_smart.www.mobiledoctor.utils.ToastUtil;
import com.socks.library.KLog;

import butterknife.ButterKnife;

/**
 * 基类
 * Created by anhui on 2017/8/29.
 * email:anhuifix@163.com
 * Date:2017/8/29
 */

public abstract class BaseFragment extends Fragment implements MvpView {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("test", "onViewCreated");
        ButterKnife.bind(this, view);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("test", "onActivityCreated");
        initView();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {
        KLog.i("test", "错误堆栈：" + message);
    }

    @Override
    public void showMessage(String message) {
        ToastUtil.Short(message);
    }

    @Override
    public void showMessage(int resId) {
        ToastUtil.Short(resId);
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getActivity().getApplicationContext());
    }

    @Override
    public void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public abstract void initView();


}
