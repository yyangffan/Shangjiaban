package com.superc.shangjiaban.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.base.BaseActivity;
import com.superc.shangjiaban.utils.ShareUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.superc.shangjiaban.base.Constant.ADDAPPLY;

public class AddAplyActivity extends BaseActivity {

    @BindView(R.id.money)
    EditText mMoney;
    @BindView(R.id.xianshi_money)
    TextView mXianshiMoney;
    @BindView(R.id.commit)
    TextView mCommit;
    private String uid;
    private String mXsmoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aply);
        ButterKnife.bind(this);
        setFinalContentL("添加申请", true);
        initListener();
    }

    @Override
    public void initListener() {
        mCommit.setOnClickListener(this);
    }

    @Override
    public void init() {
        uid = (String) ShareUtil.getInstance(this).get("uid", "");
        panduanZhanghu();
    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.commit:
                addApply();
                break;

        }

    }

    /*添加申请接口*/
    public void addApply() {
        String money = mMoney.getText().toString();
        if (money == null || money.equals("")) {
            showToast("请输入提现金额");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("act", "add");
        map.put("money", mMoney.getText().toString());
        toGetData(map, ADDAPPLY, false, new CallNetBack() {
            @Override
            public void callNetBack(JSONObject data) {
                try {
                    String code = data.getString("code");
                    String info = data.getString("info");
                    if (code.equals("200")) {
                        AddAplyActivity.this.finish();
                    }else  if (code.equals("201")) {
                        stActivity(WuyeEditActivity.class, null);
                    }
                    showToast(info);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /*判断是否添加了提款账号*/
    public void panduanZhanghu() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("act", "");
        map.put("money", "");
        toGetData(map, ADDAPPLY, false, new CallNetBack() {
            @Override
            public void callNetBack(JSONObject data) {
                try {
                    String code = data.getString("code");
                    String info = data.getString("info");
                    JSONObject date = data.getJSONObject("date");
                    if (code.equals("201")) {
                        stActivity(WuyeEditActivity.class, null);
                        showToast(info);
                    }else if(code.equals("203")){
                        mXsmoney= date.getString("money");
                        mXianshiMoney.setText(mXsmoney + "元");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
