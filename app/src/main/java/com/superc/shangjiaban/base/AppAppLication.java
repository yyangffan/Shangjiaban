package com.superc.shangjiaban.base;

import android.app.Application;
import android.content.Context;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.NoHttp;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by user on 2018/2/26.
 */

public class AppAppLication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initEve();
    }

    public static Context getmContext() {
        return mContext;
    }
    /*各种操作的初始化*/
    public void initEve() {
        /*---------------Nohttp的初始化-------------------*/
        InitializationConfig config = InitializationConfig.newBuilder(this).connectionTimeout(30 * 1000).readTimeout(30 * 1000).retry(10).build();
        NoHttp.initialize(config);
        /*---------------友盟相关初始化--------------------*/
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "5aaf58578f4a9d1c9100007b");
        UMConfigure.setLogEnabled(true);/*打开友盟的调试模式*/
        /*--------------极光推送-------------------------*/
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);





    }

}
