package com.superc.shangjiaban.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.superc.shangjiaban.R;
import com.superc.shangjiaban.base.BaseActivity;
import com.superc.shangjiaban.bean.VillageCityBean;
import com.superc.shangjiaban.ui.adapter.VillageCityAdapter;
import com.superc.shangjiaban.utils.ToastUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.superc.shangjiaban.base.Constant.DIQU;

/********************************************************************
 @version: 1.0.0
 @description: 小区搜索
 @author: user
 @time: 2018/3/5 16:40
 @变更历史:
 ********************************************************************/
public class SearchVillageActivity extends BaseActivity {

    @BindView(R.id.search_village_sheng)
    TextView mSearchVillageSheng;
    @BindView(R.id.search_village_shi)
    TextView mSearchVillageShi;
    @BindView(R.id.search_village_xian)
    TextView mSearchVillageXian;
    @BindView(R.id.search_village_xiaoqu)
    EditText mSearchVillageXiaoqu;
    @BindView(R.id.search_village_search)
    TextView mSearchVillageSearch;

    private VillageCityAdapter mVillageCityAdapter;
    private List<VillageCityBean.DataBean.AreaBean> mVillageCityBeanList;
    private ListView mMlv;
    private PopupWindow mPopupWindow;
    private String sheng = "";
    private String shi = "";
    private String xian = "";

    private int page = 1;

    private int isWhat = 1;
    private String sheng_id = "";
    private String shi_id = "";
    private String xian_id = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_village);
        ButterKnife.bind(this);
        initListener();
        setFinalContentL("小区搜索", true);
    }

    @Override
    public void initListener() {
        mSearchVillageSheng.setOnClickListener(this);
        mSearchVillageShi.setOnClickListener(this);
        mSearchVillageXian.setOnClickListener(this);
        mSearchVillageSearch.setOnClickListener(this);
        Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            sheng = extras.getString("sheng", "");
            sheng_id = extras.getString("sheng_id", "");
            shi = extras.getString("shi", "");
            shi_id = extras.getString("shi_id", "");
            xian = extras.getString("xian", "");
            xian_id = extras.getString("xian_id", "");
            mSearchVillageXiaoqu.setText(extras.getString("keyword", ""));
            mSearchVillageSheng.setText(sheng);
            mSearchVillageShi.setText(shi);
            mSearchVillageXian.setText(xian);
        }

    }

    @Override
    public void init() {
        mVillageCityBeanList = new ArrayList<>();
        mVillageCityAdapter = new VillageCityAdapter(this, mVillageCityBeanList);
        configPop();

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
        mMlv.setAdapter(mVillageCityAdapter);
        mMlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
                VillageCityBean.DataBean.AreaBean areaBean = mVillageCityBeanList.get(i);
                switch (isWhat) {
                    case 1:
                        mSearchVillageSheng.setText(areaBean.getName().equals("所有省")?"":areaBean.getName());
                        sheng_id = areaBean.getId();
                        if (!sheng.equals(areaBean.getName())) {
                            mSearchVillageShi.setText("");
                            mSearchVillageXian.setText("");
                            shi_id = "";
                            xian_id = "";
                        }
                        break;
                    case 2:
                        mSearchVillageShi.setText(areaBean.getName().equals("所有市")?"":areaBean.getName());
                        shi_id = areaBean.getId();
                        if (!shi.equals(areaBean.getName())) {
                            mSearchVillageXian.setText("");
                            xian_id = "";
                        }
                        break;
                    case 3:
                        xian_id = areaBean.getId();
                        mSearchVillageXian.setText(areaBean.getName().equals("所有县")?"":areaBean.getName());
                        break;
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_village_sheng:
                isWhat = 1;
                getCityData("省", null, null, mSearchVillageSheng);
                break;
            case R.id.search_village_shi:
                isWhat = 2;
                sheng = mSearchVillageSheng.getText().toString();
                if (sheng.equals("所有省") || sheng.equals("")) {
                    showToast("请选择省");
                    return;
                }
                getCityData(null, "市", null, mSearchVillageShi);

                break;
            case R.id.search_village_xian:
                isWhat = 3;
                shi = mSearchVillageShi.getText().toString();
                if (shi.equals("所有市") || shi.equals("")) {
                    showToast("请选择市");
                    return;
                }
                xian = mSearchVillageXian.getText().toString();
                getCityData(null, null, "县", mSearchVillageXian);

                break;
            case R.id.search_village_search:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("sheng_id", sheng_id);
                bundle.putString("shi_id", shi_id);
                bundle.putString("xian_id", xian_id);
                bundle.putString("keyword", mSearchVillageXiaoqu.getText().toString());

                bundle.putString("sheng", mSearchVillageSheng.getText().toString());
                bundle.putString("shi", mSearchVillageShi.getText().toString());
                bundle.putString("xian", mSearchVillageXian.getText().toString());
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                this.finish();

                break;
        }
    }

    /*获取省市县*/
    public void getCityData(String sg, String si, String xn, final View v) {
        Map<String, String> map = new HashMap<>();
        if (sg == null) {
            map.put("pro", mSearchVillageSheng.getText().toString());
            if (si == null) {
                map.put("city", mSearchVillageShi.getText().toString());
                map.put("dis", mSearchVillageXian.getText().toString());
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
        map.put("biaoshi", "app");
        toGetData(map, DIQU, false, new CallNetBack() {
            @Override
            public void callNetBack(JSONObject data) {
                VillageCityBean cityBean = new Gson().fromJson(data.toString(), VillageCityBean.class);
                if(cityBean.getCode().equals("200")) {
                    mVillageCityBeanList.clear();
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
                            areaBean.setName("所有县");
                            break;
                    }
                    mVillageCityBeanList.add(0, areaBean);
                    mVillageCityAdapter.notifyDataSetChanged();
                    if (mPopupWindow != null) {
                        mPopupWindow.showAsDropDown(v);
                    }
                }else {
                    ToastUtil.showToast(SearchVillageActivity.this,cityBean.getInfo());
                }

            }
        });
    }


}
