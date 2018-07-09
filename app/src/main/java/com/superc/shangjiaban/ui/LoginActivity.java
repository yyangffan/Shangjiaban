package com.superc.shangjiaban.ui;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.utils.ShareUtil;
import com.superc.shangjiaban.utils.ToastUtil;
import com.superc.shangjiaban.utils.TxtUtil;
import com.umeng.analytics.MobclickAgent;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.superc.shangjiaban.base.Constant.ADMINLOGIN;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.login_tv)
    TextView mLoginTv;
    @BindView(R.id.edit_account)
    EditText mEditAccount;
    @BindView(R.id.edit_pass)
    EditText mEditPass;
    @BindView(R.id.logint_forgetPas)
    TextView mLogintForgetPas;
    private RequestQueue mRequestQueue;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initListener();

    }

    public void initListener() {
        String name= (String) ShareUtil.getInstance(this).get("name","");
        mRequestQueue = NoHttp.newRequestQueue();
        mLoginTv.setOnClickListener(this);
        mLogintForgetPas.setOnClickListener(this);
        if(name!=null&&!name.equals("")){
            mEditAccount.setText(name);
            mEditAccount.setSelection(mEditAccount.getText().toString().length());
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_tv:
                if (TxtUtil.isEmpty(mEditAccount.getText().toString()) || TxtUtil.isEmpty(mEditPass.getText().toString())) {
                    ToastUtil.showToast(this, "请填写账号/密码");
                    return;
                }
                toLogin();
                break;
            case R.id.logint_forgetPas:
                ToastUtil.showToast(this, "忘记密码");
                break;

        }
    }


    public void toLogin() {
        final String name = mEditAccount.getText().toString();
        final String pwd = mEditPass.getText().toString();
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(ADMINLOGIN, RequestMethod.POST);
        request.add("username", name);
        request.add("password",pwd);
        request.add("type", "app");
        showLoadPop();

        mRequestQueue.add(2, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                hideLoadPop();
                JSONObject jsonObject = response.get();
                Log.e("登录返回数据",jsonObject.toString());
                try {
                    String code = jsonObject.getString("code");
                    String info = jsonObject.getString("info");
                    if (code.equals("200")) {
                        String uid = jsonObject.getString("uid");
                        String role_id=jsonObject.getString("role_id");
                        Bundle bundle = new Bundle();
                        bundle.putString("login", "登录成功");
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                        ShareUtil.getInstance(LoginActivity.this).put("uid", uid);
                        ShareUtil.getInstance(LoginActivity.this).put("role_id",role_id);
                        ShareUtil.getInstance(LoginActivity.this).put("name",mEditAccount.getText().toString());

                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("name_pwd","用户名:"+name+" 密码:"+pwd);
                        MobclickAgent.onEvent(LoginActivity.this, "user__pwd", map);

                        LoginActivity.this.finish();
                    }
                    ToastUtil.showToast(LoginActivity.this, info);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {
                hideLoadPop();
                ToastUtil.showToast(LoginActivity.this, "网络异常");
            }

            @Override
            public void onFinish(int what) {
                hideLoadPop();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    /*加载Loading 如下两个*/
    public void showLoadPop() {
        mPopupWindow = new PopupWindow(this);
        mPopupWindow.setContentView(LayoutInflater.from(this).inflate(R.layout.layout_load_popup, null));
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

    public void hideLoadPop() {
        if(mPopupWindow!=null){
            mPopupWindow.dismiss();
        }
    }

}
