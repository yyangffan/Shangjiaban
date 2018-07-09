package com.superc.shangjiaban.ui;

import android.os.Bundle;
import android.view.View;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.base.BaseActivity;
import com.superc.shangjiaban.utils.ShareUtil;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setFinalContentL("二次元",true);
        initListener();
    }
    @Override
    public void initListener() {
        ShareUtil.getInstance(this).put("success",false);
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
}
