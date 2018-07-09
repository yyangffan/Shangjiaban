package com.superc.shangjiaban.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.superc.shangjiaban.R;
import com.superc.shangjiaban.base.BaseActivity;
import com.superc.shangjiaban.bean.HuoDongResponse;
import com.superc.shangjiaban.bean.VillageCityBean;
import com.superc.shangjiaban.bean.VillageXiaoqBean;
import com.superc.shangjiaban.ui.adapter.ShopDdAdapter;
import com.superc.shangjiaban.ui.adapter.VillageCityAdapter;
import com.superc.shangjiaban.utils.ShareUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.superc.shangjiaban.base.Constant.ALLHUODONG;
import static com.superc.shangjiaban.base.Constant.SELECTVILLAGE;

public class OrderSearchZtActivity extends BaseActivity {

    @BindView(R.id.order_searchzt_xiaoqu)
    TextView mOrderSearchztXiaoqu;
    @BindView(R.id.order_searchzt_hd)
    TextView mOrderSearchztHd;
    @BindView(R.id.order_searchzt_edit)
    EditText mOrderSearchztEdit;
    @BindView(R.id.order_searchzt_search)
    TextView mOrderSearchztSearch;

    private PopupWindow mPopupWindow_huodong;
    private ListView mhuo_lv;
    private ShopDdAdapter mShopDdAdapter;
    private List<Map<String, String>> mMapList;
    private String mHuodong;
    private String huodong_id = "";
    private String xiaoqu = "";
    private String xiaoqu_id = "";
    private String uid = "";

    private VillageCityAdapter mVillageCityAdapter;
    private List<VillageCityBean.DataBean.AreaBean> mVillageCityBeanList;
    private PopupWindow mPopupWindow;
    private ListView mMlv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_search_zt);
        ButterKnife.bind(this);
        setFinalContentL("订单搜索", true);
        initListener();
    }

    @Override
    public void initListener() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                xiaoqu = extras.getString("xiaoqu", "");
                mHuodong = extras.getString("huodong", "");

                xiaoqu_id = extras.getString("xiaoqu_id", "");
                huodong_id = extras.getString("huodong_id", "");
                String keyword = extras.getString("keyword");
                mOrderSearchztEdit.setText(keyword);
                mOrderSearchztXiaoqu.setText(xiaoqu);
                mOrderSearchztHd.setText(mHuodong);
            }
        }
    }

    @Override
    public void init() {
        uid = (String) ShareUtil.getInstance(this).get("uid", "");
        mVillageCityBeanList = new ArrayList<>();
        mVillageCityAdapter = new VillageCityAdapter(this, mVillageCityBeanList);
        mMapList = new ArrayList<>();
        mShopDdAdapter = new ShopDdAdapter(this, mMapList);
        configPop();
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
                mHuodong = map.get("content");
                huodong_id = map.get("id");
                mOrderSearchztHd.setText(mHuodong);
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
                mOrderSearchztXiaoqu.setText(areaBean.getName());
                xiaoqu_id = areaBean.getId();
                xiaoqu = areaBean.getName();
            }
        });

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
                    Map<String, String> ma = new HashMap<>();
                    ma.put("id", "");
                    ma.put("content", "所有活动");
                    mMapList.add(0, ma);
                    mShopDdAdapter.notifyDataSetChanged();

                    if (mPopupWindow_huodong != null) {
                        mPopupWindow_huodong.showAsDropDown(mOrderSearchztHd);
                    }
                }
            }
        });

    }


    @OnClick({R.id.order_searchzt_xiaoqu, R.id.order_searchzt_hd, R.id.order_searchzt_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.order_searchzt_xiaoqu:
                getXiaoQu(mOrderSearchztXiaoqu);
                break;
            case R.id.order_searchzt_hd:
                getHuodong();
                break;
            case R.id.order_searchzt_search:
                showToast("开始搜索");
                Intent intent = new Intent();
                intent.putExtra("xiaoqu_id", xiaoqu_id);
                intent.putExtra("huodong_id", huodong_id);
                intent.putExtra("xiaoqu", xiaoqu);
                intent.putExtra("huodong", mHuodong);
                intent.putExtra("keyword", mOrderSearchztEdit.getText().toString());

                setResult(RESULT_OK, intent);
                this.finish();
                break;
        }
    }

    /*获取小区*/
    public void getXiaoQu(final View v) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        toGetData(map, SELECTVILLAGE, false, new CallNetBack() {
            @Override
            public void callNetBack(JSONObject data) {
                VillageXiaoqBean cityBean = new Gson().fromJson(data.toString(), VillageXiaoqBean.class);
                if (cityBean.getCode() == 200) {
                    mVillageCityBeanList.clear();
                    List<VillageXiaoqBean.DateBean> xiao_qu = cityBean.getDate();
                    for (VillageXiaoqBean.DateBean xq : xiao_qu) {
                        VillageCityBean.DataBean.AreaBean areaBean = new VillageCityBean.DataBean.AreaBean();
                        areaBean.setId(xq.getId());
                        areaBean.setName(xq.getName());
                        mVillageCityBeanList.add(areaBean);
                    }
                    if (xiao_qu.size() == 0) {
                        showToast("没有小区信息");
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
