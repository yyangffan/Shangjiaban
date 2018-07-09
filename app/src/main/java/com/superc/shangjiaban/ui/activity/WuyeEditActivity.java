package com.superc.shangjiaban.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.superc.shangjiaban.R;
import com.superc.shangjiaban.base.BaseActivity;
import com.superc.shangjiaban.bean.ProPertyListBean;
import com.superc.shangjiaban.bean.PropertyDetailBean;
import com.superc.shangjiaban.utils.ShareUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.superc.shangjiaban.base.Constant.PROPERDETAIL;
import static com.superc.shangjiaban.base.Constant.UPDATEPROPERTY;

public class WuyeEditActivity extends BaseActivity {

    @BindView(R.id.wuye_edit_tv)
    TextView mWuyeEditTv;
    @BindView(R.id.wuye_edit_name)
    EditText mWuyeEditName;
    @BindView(R.id.wuye_edit_money)
    EditText mWuyeEditMoney;
    @BindView(R.id.wuye_edit_username)
    EditText mWuyeEditUsername;
    @BindView(R.id.wuye_edit_phone)
    EditText mWuyeEditPhone;
    @BindView(R.id.wuye_edit_youxiang)
    EditText mWuyeEditYouxiang;
    @BindView(R.id.wuye_edit_shoukuantujing)
    EditText mWuyeEditShoukuantujing;
    @BindView(R.id.wuye_edit_shoukaunzhanghao)
    EditText mWuyeEditShoukaunzhanghao;
    @BindView(R.id.wuye_edit_newpwd)
    EditText mWuyeEditNewpwd;
    @BindView(R.id.wuye_edit_surepwd)
    EditText mWuyeEditSurepwd;
    @BindView(R.id.wy_tv_complete)
    TextView mtv_complete;
    private ProPertyListBean.DateBean mExampleClass;
    private String uid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuye_edit);
        ButterKnife.bind(this);
        setFinalContentL("编辑", true);
        initListener();
    }

    @Override
    public void initListener() {
        mWuyeEditTv.requestFocus();
        mtv_complete.setOnClickListener(this);
        getDetail();
    }

    /*获取单条物业接口*/
    public void getDetail() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        toGetData(map, PROPERDETAIL, false, new CallNetBack() {
            @Override
            public void callNetBack(JSONObject data) {
                PropertyDetailBean bean = new Gson().fromJson(data.toString(), PropertyDetailBean.class);
                int code = bean.getCode();
                if (code == 200) {
                    setMsg(bean);
                }else {
                    showToast(bean.getInfo());
                }
            }
        });


    }

    /*设置初始数据*/
    public void setMsg(PropertyDetailBean bean) {
        PropertyDetailBean.DateBean date = bean.getDate();
        mWuyeEditName.setText(date.getUserName());
        mWuyeEditUsername.setText(date.getLinkman());
        mWuyeEditPhone.setText(date.getPhone());
        mWuyeEditYouxiang.setText(date.getEmail());
        mWuyeEditShoukuantujing.setText(date.getChannel());
        mWuyeEditShoukaunzhanghao.setText(date.getAccount());
        mWuyeEditMoney.setText(date.getDistributMoney() + "");
    }

    @Override
    public void init() {
        uid = (String) ShareUtil.getInstance(this).get("uid", "");

    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wy_tv_complete:
                isComplete();
                break;


        }

    }

    /*编辑完成接口*/
    public void isComplete() {
        String name = mWuyeEditUsername.getText().toString();
        String phone = mWuyeEditPhone.getText().toString();
        String youxaing = mWuyeEditYouxiang.getText().toString();
        String fangshi = mWuyeEditShoukuantujing.getText().toString();
        String zhanghao = mWuyeEditShoukaunzhanghao.getText().toString();
        if (name == null || name.equals("")) {
            showToast("请填写联系人");
            return;
        }
        if (phone == null || phone.equals("")) {
            showToast("请填写电话");
            return;
        }
        if (youxaing == null || youxaing.equals("")) {
            showToast("请填写邮箱");
            return;
        }
        if (fangshi == null || fangshi.equals("")) {
            showToast("请填写收款途径");
            return;
        }
        if (zhanghao == null || zhanghao.equals("")) {
            showToast("请填写收款账号");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("linkman", name);
        map.put("phone", phone);
        map.put("email", youxaing);
        map.put("channel", fangshi);
        map.put("account", zhanghao);

        toGetData(map, UPDATEPROPERTY, false, new CallNetBack() {
            @Override
            public void callNetBack(JSONObject data) {
                try {
                    String code = data.getString("code");
                    String info = data.getString("info");
                    if (code.equals("200")) {
                        WuyeEditActivity.this.finish();
                    }
                    showToast(info);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }


}
