package com.superc.shangjiaban.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.base.BaseActivity;
import com.superc.shangjiaban.base.Constant;
import com.superc.shangjiaban.utils.ShareUtil;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

import static com.superc.shangjiaban.base.Constant.JPUSH_BIAOSHI;

public class SettingActivity extends BaseActivity {
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setFinalContentL("设置", true);
        mRequestQueue = NoHttp.newRequestQueue();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void init() {

    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View view) {

    }

    public void logout(View v) {
        ShareUtil.getInstance(SettingActivity.this).put("success", false);
//        new SetJPushAlias("",this).cancleAlias();
        JPushInterface.deleteAlias(this,JPUSH_BIAOSHI);
        ShareUtil.getInstance(this).remove("uid");
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(Constant.LOGNOUT, RequestMethod.POST);
        mRequestQueue.add(2, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {
            }

            @Override
            public void onFinish(int what) {
            }
        });

        setResult(RESULT_OK,new Intent());
        finishAll();

    }
}
