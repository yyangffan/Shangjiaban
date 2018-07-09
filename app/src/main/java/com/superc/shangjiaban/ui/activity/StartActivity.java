package com.superc.shangjiaban.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

import com.superc.shangjiaban.ui.LoginActivity;
import com.superc.shangjiaban.ui.MainActivity;
import com.superc.shangjiaban.utils.ShareUtil;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                boolean success = (boolean) ShareUtil.getInstance(StartActivity.this).get("success", false);
                if(success) {
                    StartActivity.this.startActivity(new Intent(StartActivity.this, MainActivity.class));
                }else {
                    StartActivity.this.startActivity(new Intent(StartActivity.this, LoginActivity.class));
                }
                finish();
            }
        }.start();
    }
}
