package com.superc.shangjiaban.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.base.BaseActivity;
import com.superc.shangjiaban.views.CustomDatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchTxJLActivity extends BaseActivity {

    @BindView(R.id.search_txjl_sttime)
    TextView mSearchTxjlSttime;
    @BindView(R.id.search_txjl_endtime)
    TextView mSearchTxjlEndtime;
    @BindView(R.id.search_txjl_keyword)
    EditText mSearchTxjlKeyword;
    @BindView(R.id.search_txjl_stsearch)
    TextView mSearchTxjlStsearch;

    private CustomDatePicker customDatePickerSt;
    private String choseTime = "";
    private String st_time, ed_time = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tx_jl);
        ButterKnife.bind(this);
        initListener();
        setFinalContentL("搜索提现记录", true);
    }

    @Override
    public void initListener() {
        mSearchTxjlSttime.requestFocus();
        mSearchTxjlSttime.setOnClickListener(this);
        mSearchTxjlEndtime.setOnClickListener(this);
        mSearchTxjlStsearch.setOnClickListener(this);
        Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            st_time = extras.getString("st_time");
            ed_time = extras.getString("ed_time");
            mSearchTxjlKeyword.setText(extras.getString("keyword"));
            mSearchTxjlKeyword.setSelection(mSearchTxjlKeyword.getText().toString().length());
        }
        initDatePick();
    }

    public void showDateDialog(final TextView mtv) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        customDatePickerSt = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                mtv.setText(time);
                choseTime = getTime(time);
                Log.e("开始", "开始时间的时间戳:" + getTime(time));
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePickerSt.showSpecificTime(true); // 不显示时和分
        customDatePickerSt.setIsLoop(true); // 允许循环滚动
        customDatePickerSt.show(mtv.getText().toString());
    }

    public void initDatePick() {
        SimpleDateFormat sdf_no = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now_time = sdf_no.format(new Date());
        mSearchTxjlSttime.setText(st_time == null || st_time.equals("") ? now_time : st_time);
        mSearchTxjlEndtime.setText(ed_time == null || ed_time.equals("") ? now_time : ed_time);
    }


    @Override
    public void init() {

    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_txjl_sttime:
                showDateDialog(mSearchTxjlSttime);
                break;
            case R.id.search_txjl_endtime:
                showDateDialog(mSearchTxjlEndtime);
                break;
            case R.id.search_txjl_stsearch:
                Bundle bundle = new Bundle();
                bundle.putString("st_time", mSearchTxjlSttime.getText().toString());
                bundle.putString("ed_time", mSearchTxjlEndtime.getText().toString());
                bundle.putString("keyword", mSearchTxjlKeyword.getText().toString());
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                this.finish();
                break;
        }

    }


    // 将字符串转为时间戳
    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        } catch (ParseException e) {
        }
        return re_time;
    }
}
