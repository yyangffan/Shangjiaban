package com.superc.shangjiaban.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.base.BaseActivity;
import com.superc.shangjiaban.others.PublicBean;
import com.superc.shangjiaban.ui.adapter.ShopDdAdapter;
import com.superc.shangjiaban.views.CustomDatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/********************************************************************
 @version: 1.0.0
 @description: 订单--订单搜索
 @author: user
 @time: 2018/3/7 13:58
 @变更历史:
 ********************************************************************/
public class OrderSearchActivity extends BaseActivity {

    @BindView(R.id.order_search_lx)
    TextView mOrderSearchLx;
    @BindView(R.id.order_search_sttime)
    TextView mOrderSearchSttime;
    @BindView(R.id.order_search_endtime)
    TextView mOrderSearchEndtime;
    @BindView(R.id.order_search_zfzt)
    TextView mOrderSearchZfzt;
    @BindView(R.id.order_search_fhzt)
    TextView mOrderSearchFhzt;
    @BindView(R.id.order_search_ddzt)
    TextView mOrderSearchDdzt;
    @BindView(R.id.order_search_bianhao)
    EditText mOrderSearchBianhao;
    @BindView(R.id.order_search_search)
    TextView mOrderSearchSearch;


    private PopupWindow mPopupWindow;
    private ListView mMlv;
    private ShopDdAdapter mShopDdAdapter;
    private int isWhat = 1;

    private List<Map<String, String>> mMapList;
    private List<Map<String, String>> map_one;
    private List<Map<String, String>> map_two;
    private String[] mstring_two;
    private List<Map<String, String>> map_three;
    private String[] mstring_three;
    private List<Map<String, String>> map_four;
    private String[] mstring_four;
    private CustomDatePicker customDatePickerSt;
    private String choseTime;

    private String st_time="";
    private String ed_time="";
    private String dd_lx="";
    private String dd_lx_id="";
    private String zf_zt="";
    private String zf_zt_id="";
    private String fh_zt="";
    private String fh_zt_id="";
    private String dd_zt="";
    private String dd_zt_id="";
    private String bianhao="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_search);
        ButterKnife.bind(this);
        setFinalContentL("订单搜索", true);
        initListener();
    }

    @Override
    public void initListener() {
        mOrderSearchLx.requestFocus();
        Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            PublicBean publicBean = (PublicBean) extras.getSerializable("data");
            setMsg(publicBean);
        }

    }

    @Override
    public void init() {


        mMapList = new ArrayList<>();
        mstring_two = this.getResources().getStringArray(R.array.shop_zfzt);
        mstring_three = this.getResources().getStringArray(R.array.shop_fhzt);
        mstring_four = this.getResources().getStringArray(R.array.shop_ddzt);


        map_one = new ArrayList<>();
        Map<String, String> mapm = new HashMap<>();
        mapm.put("id", "0");
        mapm.put("content", "普通");
        Map<String, String> mapp = new HashMap<>();
        mapp.put("id", "6");
        mapp.put("content", "超市");
        map_one.add(mapm);
        map_one.add(mapp);

        map_two = new ArrayList<>();
        for (int i = 0; i < mstring_two.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("id", i + "");
            map.put("content", mstring_two[i]);
            map_two.add(map);
        }

        map_three = new ArrayList<>();
        for (int i = 0; i < mstring_three.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("id", i + "");
            map.put("content", mstring_three[i]);
            map_three.add(map);
        }

        map_four = new ArrayList<>();
        for (int i = 0; i < mstring_four.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("id", (i + 1) + "");
            map.put("content", mstring_four[i]);
            map_four.add(map);
        }
        mMapList.addAll(map_one);
        mShopDdAdapter = new ShopDdAdapter(this, mMapList);
        configPop();

    }

    /*设置数据*/
    public void setMsg(PublicBean publicBean) {
        if(publicBean!=null) {
            this.st_time = publicBean.getSt_time();
            this.ed_time = publicBean.getEd_time();
            this.dd_lx = publicBean.getDd_lx();
            this.dd_lx_id = publicBean.getDd_lx_id();
            this.zf_zt = publicBean.getZf_zt();
            this.zf_zt_id = publicBean.getZf_zt_id();
            this.fh_zt = publicBean.getFh_zt();
            this.fh_zt_id = publicBean.getFh_zt_id();
            this.dd_zt = publicBean.getDd_zt();
            this.dd_zt_id = publicBean.getDd_zt_id();
            this.bianhao = publicBean.getOther_one();
        }
        mOrderSearchLx.setText(dd_lx);
        mOrderSearchZfzt.setText(zf_zt);
        mOrderSearchFhzt.setText(fh_zt);
        mOrderSearchDdzt.setText(dd_zt);
        mOrderSearchBianhao.setText(bianhao);
        initDatePick();
    }


    @Override
    public void getData() {

    }

    /*初始化弹出的PopUpwindow*/
    public void configPop() {
        mPopupWindow = new PopupWindow(this);
        View v = LayoutInflater.from(this).inflate(R.layout.layout_city, null);
        mMlv = v.findViewById(R.id.pop_city_lv);
        mPopupWindow.setContentView(v);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);
        mMlv.setAdapter(mShopDdAdapter);
        mMlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
                Map<String, String> map = mMapList.get(i);
                String content = map.get("content");
                String id=map.get("id");
                switch (isWhat) {
                    case 1:
                        mOrderSearchLx.setText(content);
                        dd_lx=content;
                        dd_lx_id=id;
                        break;
                    case 2:
                        mOrderSearchZfzt.setText(content);
                        zf_zt=content;
                        zf_zt_id=id;
                        break;
                    case 3:
                        mOrderSearchFhzt.setText(content);
                        fh_zt=content;
                        fh_zt_id=id;
                        break;
                    case 4:
                        mOrderSearchDdzt.setText(content);
                        dd_zt=content;
                        dd_zt_id=id;
                        break;
                }
            }
        });
    }

    /*展示Pop*/
    public void showPop(List<Map<String, String>> maps, View v) {
        mMapList.clear();
        mMapList.addAll(maps);
        mShopDdAdapter.notifyDataSetChanged();
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(v);
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
        mOrderSearchSttime.setText(st_time==null||st_time.equals("")?now_time:st_time);
        mOrderSearchEndtime.setText(ed_time==null||ed_time.equals("")?now_time:ed_time);
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


    @OnClick({R.id.order_search_lx, R.id.order_search_sttime, R.id.order_search_endtime, R.id.order_search_zfzt, R.id.order_search_fhzt,
            R.id.order_search_ddzt, R.id.order_search_bianhao, R.id.order_search_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.order_search_lx:/*订单类型*/
                isWhat = 1;
                showPop(map_one, mOrderSearchLx);

                break;
            case R.id.order_search_sttime:/*开始时间*/
                showDateDialog(mOrderSearchSttime);
                break;
            case R.id.order_search_endtime:/*结束时间*/
                showDateDialog(mOrderSearchEndtime);
                break;
            case R.id.order_search_zfzt:/*支付状态*/
                isWhat = 2;
                showPop(map_two, mOrderSearchZfzt);
                break;
            case R.id.order_search_fhzt:/*发货状态*/
                isWhat = 3;
                showPop(map_three, mOrderSearchFhzt);
                break;
            case R.id.order_search_ddzt:/*订单状态*/
                isWhat = 4;
                showPop(map_four, mOrderSearchDdzt);
                break;
            case R.id.order_search_bianhao:
                break;
            case R.id.order_search_search:/*进行搜索*/
                st_time=mOrderSearchSttime.getText().toString();
                ed_time=mOrderSearchEndtime.getText().toString();
                bianhao=mOrderSearchBianhao.getText().toString();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                PublicBean publicBean = new PublicBean(st_time, ed_time, dd_lx, dd_lx_id, zf_zt, zf_zt_id,
                        fh_zt, fh_zt_id, dd_zt, dd_zt_id, bianhao);
                bundle.putSerializable("data", publicBean);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                this.finish();
                break;
        }
    }
}
