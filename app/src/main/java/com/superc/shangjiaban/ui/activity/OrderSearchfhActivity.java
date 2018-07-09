package com.superc.shangjiaban.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.superc.shangjiaban.R;
import com.superc.shangjiaban.base.BaseActivity;
import com.superc.shangjiaban.bean.HuoDongResponse;
import com.superc.shangjiaban.bean.VillageCityBean;
import com.superc.shangjiaban.ui.adapter.ShopDdAdapter;
import com.superc.shangjiaban.ui.adapter.VillageCityAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.superc.shangjiaban.base.Constant.ALLHUODONG;
import static com.superc.shangjiaban.base.Constant.DIQU;

/********************************************************************
 @version: 1.0.0
 @description: 订单--待发货--地址搜索
 @author: user
 @time: 2018/3/8 9:17
 @变更历史:
 ********************************************************************/

public class OrderSearchfhActivity extends BaseActivity {

    @BindView(R.id.order_searchfh_sheng)
    TextView mOrderSearchfhSheng;
    @BindView(R.id.order_searchfh_shi)
    TextView mOrderSearchfhShi;
    @BindView(R.id.order_searchfh_qu)
    TextView mOrderSearchfhQu;
    @BindView(R.id.order_searchfh_quxian)
    TextView mOrderSearchfhQuxian;
    @BindView(R.id.order_searchfh_allhd)
    TextView mOrderSearchfhAllhd;
    @BindView(R.id.order_searchfh_search)
    TextView mOrderSearchfhSearch;

    private VillageCityAdapter mVillageCityAdapter;
    private List<VillageCityBean.DataBean.AreaBean> mVillageCityBeanList;
    private ListView mMlv;
    private PopupWindow mPopupWindow;
    private String sheng = "";
    private String shi = "";
    private String xian = "";
    private String qu = "";
    private String huodong = "";

    private int isWhat = 1;

    private int page = 1;
    private String sheng_id = "";
    private String shi_id = "";
    private String xian_id = "";
    private String xiaoqu_id = "";
    private String huodong_id = "";

    private PopupWindow mPopupWindow_huodong;
    private ListView mhuo_lv;
    private ShopDdAdapter mShopDdAdapter;
    private List<Map<String, String>> mMapList;
    private String[] mstrings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_searchfh);
        ButterKnife.bind(this);
        setFinalContentL("订单搜索", true);
        initListener();
    }

    @OnClick({R.id.order_searchfh_sheng, R.id.order_searchfh_shi, R.id.order_searchfh_qu, R.id.order_searchfh_quxian, R.id.order_searchfh_allhd, R.id.order_searchfh_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.order_searchfh_sheng:
                isWhat = 1;
                getCityData("省", null, null, null, mOrderSearchfhSheng);
                break;
            case R.id.order_searchfh_shi:
                isWhat = 2;
                sheng = mOrderSearchfhSheng.getText().toString();
                if (sheng.equals("所有省") || sheng.equals("")) {
                    showToast("请选择省");
                    return;
                }
                getCityData(null, "市", null, null, mOrderSearchfhShi);
                break;
            case R.id.order_searchfh_qu:/*这个是区县(id名有问题)*/
                isWhat = 3;
                shi = mOrderSearchfhShi.getText().toString();
                if (shi.equals("所有市") || shi.equals("")) {
                    showToast("请选择市");
                    return;
                }
                getCityData(null, null, "县", null, mOrderSearchfhQu);

                break;
            case R.id.order_searchfh_quxian:/*这个是区(id名有问题)*/
                isWhat = 4;
                xian = mOrderSearchfhQu.getText().toString();
                if (xian.equals("所有区县") || xian.equals("")) {
                    showToast("请选择区县");
                    return;
                }
                qu = mOrderSearchfhQuxian.getText().toString();
                getCityData(null, null, null, "小区", mOrderSearchfhQuxian);

                break;
            case R.id.order_searchfh_allhd://活动
                getHuodong();

                break;
            case R.id.order_searchfh_search:
                String kkk = mOrderSearchfhSheng.getText().toString();
                if (kkk == null || kkk.equals("")) {
                    setResult(RESULT_OK, new Intent());
                    this.finish();
                    return;
                }
                String xiaoqu = mOrderSearchfhQuxian.getText().toString();
                if (xiaoqu.equals("") && huodong.equals("")) {
                    showToast("选择到小区或者一个活动才能进行搜索");
                    return;
                }
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("sheng_id", sheng_id);
                bundle.putString("shi_id", shi_id);
                bundle.putString("quxian_id", xian_id);
                bundle.putString("qu_id", xiaoqu_id);
                bundle.putString("huodong", huodong);

                bundle.putString("sheng", sheng);
                bundle.putString("shi", shi);
                bundle.putString("quxian", xian);
                bundle.putString("qu", qu);
                bundle.putString("huodong_id", huodong_id);
                intent.putExtras(bundle);

                setResult(RESULT_OK, intent);
                this.finish();
                break;
        }
    }

    @Override
    public void initListener() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                sheng = extras.getString("sheng", "");
                shi = extras.getString("shi", "");
                xian = extras.getString("quxian", "");
                qu = extras.getString("xiaoqu", "");
                huodong = extras.getString("huodong", "");

                sheng_id = extras.getString("sheng_id", "");
                shi_id = extras.getString("shi_id", "");
                xian_id = extras.getString("quxian_id", "");
                xiaoqu_id = extras.getString("xiaoqu_id", "");
                huodong_id = extras.getString("huodong_id", "");

                mOrderSearchfhSheng.setText(sheng);
                mOrderSearchfhShi.setText(shi);
                mOrderSearchfhQu.setText(xian);
                mOrderSearchfhQuxian.setText(qu);
                mOrderSearchfhAllhd.setText(huodong);
            }
        }

    }

    @Override
    public void init() {
        mstrings = this.getResources().getStringArray(R.array.huodong);
        mVillageCityBeanList = new ArrayList<>();
        mVillageCityAdapter = new VillageCityAdapter(this, mVillageCityBeanList);
        mMapList = new ArrayList<>();
        mShopDdAdapter = new ShopDdAdapter(this, mMapList);
        configPop();

    }

    /*获取活动*/
    public void getHuodong() {
        Map<String, String> map = new HashMap<>();
        toGetData(map, ALLHUODONG, false, new CallNetBack() {
            @Override
            public void callNetBack(JSONObject data) {
                HuoDongResponse huoDong = new Gson().fromJson(data.toString(), HuoDongResponse.class);
                showToast(huoDong.getInfo());
                if (huoDong.getCode() == 200) {
                    mMapList.clear();
                    for (HuoDongResponse.DateBean bean : huoDong.getDate()) {
                        Map<String, String> map = new HashMap<>();
                        map.put("id", bean.getId());
                        map.put("content", bean.getTitle());
                        mMapList.add(map);
                    }
                    Map<String, String> ma = new HashMap<String, String>();
                    ma.put("id", "");
                    ma.put("content", "所有活动");
                    mMapList.add(0, ma);
                    mShopDdAdapter.notifyDataSetChanged();
                    if (mPopupWindow_huodong != null) {
                        mPopupWindow_huodong.showAsDropDown(mOrderSearchfhAllhd);
                    }
                }
            }
        });

    }


    @Override
    public void getData() {

    }

    /*初始化弹出的PopUpwindow*/
    public void configPop() {
        mPopupWindow_huodong = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_city, null);
        mhuo_lv = view.findViewById(R.id.pop_city_lv);
        mPopupWindow_huodong.setContentView(view);
        mPopupWindow_huodong.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow_huodong.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow_huodong.setOutsideTouchable(true);
        mhuo_lv.setAdapter(mShopDdAdapter);
        mhuo_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map<String, String> map = mMapList.get(i);
                huodong = map.get("content");
                huodong_id = map.get("id");
                mOrderSearchfhAllhd.setText(huodong);
                mPopupWindow_huodong.dismiss();
            }
        });

        mPopupWindow = new PopupWindow(this);
        View v = LayoutInflater.from(this).inflate(R.layout.layout_city, null);
        mMlv = v.findViewById(R.id.pop_city_lv);
        mPopupWindow.setContentView(v);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);
        mMlv.setAdapter(mVillageCityAdapter);
        mMlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
                VillageCityBean.DataBean.AreaBean areaBean = mVillageCityBeanList.get(i);
                switch (isWhat) {
                    case 1:/*省*/
                        mOrderSearchfhSheng.setText(areaBean.getName().equals("所有省") ? "" : areaBean.getName());
                        sheng_id = areaBean.getId();
                        if (!sheng.equals(areaBean.getName())) {
                            mOrderSearchfhShi.setText("");
                            mOrderSearchfhQu.setText("");
                            mOrderSearchfhQuxian.setText("");
                            shi_id = "";
                            xian_id = "";
                            xiaoqu_id = "";
                        }
                        break;
                    case 2:/*市*/
                        mOrderSearchfhShi.setText(areaBean.getName().equals("所有市") ? "" : areaBean.getName());
                        shi_id = areaBean.getId();
                        if (!shi.equals(areaBean.getName())) {
                            mOrderSearchfhQu.setText("");
                            mOrderSearchfhQuxian.setText("");
                            xian_id = "";
                            xiaoqu_id = "";
                        }
                        break;
                    case 3:/*区县*/
                        mOrderSearchfhQu.setText(areaBean.getName().equals("所有区县") ? "" : areaBean.getName());
                        xian_id = areaBean.getId();
                        if (!xian.equals(areaBean.getName())) {
                            mOrderSearchfhQuxian.setText("");
                            xiaoqu_id = "";
                        }
                        break;
                    case 4:/*小区*/
                        mOrderSearchfhQuxian.setText(areaBean.getName());
                        xiaoqu_id = areaBean.getId();
                        qu = areaBean.getName();
                        break;
                }
            }
        });
    }

    /*获取省市县*/
    public void getCityData(String sg, String si, String xn, String xiaoqu, final View v) {
        Map<String, String> map = new HashMap<>();
        if (sg == null) {
            map.put("pro", mOrderSearchfhSheng.getText().toString());
            if (si == null) {
                map.put("city", mOrderSearchfhShi.getText().toString());
                if (xn == null) {
                    map.put("dis", mOrderSearchfhQu.getText().toString());
                } else {
                    map.put("dis", "");
                }
            } else {
                map.put("city", "");
                map.put("dis", "");
            }
        } else {
            map.put("pro", "");
            map.put("city", "");
            map.put("dis", "");
        }
        map.put("village", null);
        map.put("page", page + "");
//        if(xiaoqu==null){
//            map.put("biaoshi", "app");
//        }
        toGetData(map, DIQU, false, new CallNetBack() {
            @Override
            public void callNetBack(JSONObject data) {
                VillageCityBean cityBean = new Gson().fromJson(data.toString(), VillageCityBean.class);
                if (cityBean.getCode().equals("200")) {
                    mVillageCityBeanList.clear();
                    if (isWhat != 4) {/*非小区处理*/
                        mVillageCityBeanList.addAll(cityBean.getData().getArea());
                        VillageCityBean.DataBean.AreaBean areaBean = new VillageCityBean.DataBean.AreaBean();
                        areaBean.setId("");
                        switch (isWhat) {
                            case 1:
                                areaBean.setName("所有省");
                                break;
                            case 2:
                                areaBean.setName("所有市");
                                break;
                            case 3:
                                areaBean.setName("所有区县");
                                break;
                        }
                        mVillageCityBeanList.add(0, areaBean);
                    } else {/*下面的小区的处理*/
                        List<VillageCityBean.DataBean.XiaoquBean> xiao_qu = cityBean.getData().getXiaoqu();
                        for (VillageCityBean.DataBean.XiaoquBean xq : xiao_qu) {
                            VillageCityBean.DataBean.AreaBean areaBean = new VillageCityBean.DataBean.AreaBean();
                            areaBean.setId(xq.getId());
                            areaBean.setName(xq.getName());
                            mVillageCityBeanList.add(areaBean);
                        }
                        if (xiao_qu.size() == 0) {
                            showToast("没有小区信息");
                        }
                    }
                    mVillageCityAdapter.notifyDataSetChanged();
                    if (mPopupWindow != null && mVillageCityBeanList.size() != 0) {
                        mPopupWindow.showAsDropDown(v);
                    }
                } else {
                    showToast(cityBean.getInfo());
                }

            }
        });
    }


}
