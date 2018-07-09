package com.superc.shangjiaban.ui;

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

public class SearchActivity extends BaseActivity {

    @BindView(R.id.tv_sttime)
    TextView mTvSttime;
    @BindView(R.id.tv_endtime)
    TextView mTvEndtime;
    @BindView(R.id.tv_name)
    EditText mEdtName;
    @BindView(R.id.tv_search)
    TextView mTvSearch;

    private CustomDatePicker customDatePickerSt;
    private String choseTime = "";
    private String st_time="";
    private String ed_time="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setFinalContentL("搜索", true);
        initListener();
    }

    @Override
    public void initListener() {
        mTvSttime.setOnClickListener(this);
        mTvEndtime.setOnClickListener(this);
        mTvSearch.setOnClickListener(this);
        Bundle extras = this.getIntent().getExtras();
        if(extras!=null){
            st_time=extras.getString("st_time");
            ed_time=extras.getString("ed_time");
            String keyword = extras.getString("keyword");
            mEdtName.setText(keyword);
        }
        initDatePick();

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
            case R.id.tv_sttime:
                showDateDialog(mTvSttime);
                break;
            case R.id.tv_endtime:
                showDateDialog(mTvEndtime);
                break;
            case R.id.tv_search:
                Bundle bundle = new Bundle();
                bundle.putString("st_time", mTvSttime.getText().toString());
                bundle.putString("ed_time", mTvEndtime.getText().toString());
                bundle.putString("keyword", mEdtName.getText().toString());
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                this.finish();
                break;
        }

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
        mTvSttime.setText(st_time==null||st_time.equals("")?now_time:st_time);
        mTvEndtime.setText(ed_time==null||ed_time.equals("")?now_time:ed_time);
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
