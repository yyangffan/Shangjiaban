package com.superc.shangjiaban.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.superc.shangjiaban.R;
import com.superc.shangjiaban.base.BaseActivity;
import com.superc.shangjiaban.bean.AddVillageBean;
import com.superc.shangjiaban.bean.VillageAllBean;
import com.superc.shangjiaban.bean.VillageCityBean;
import com.superc.shangjiaban.bean.VillageDetailBean;
import com.superc.shangjiaban.ui.adapter.VillageCityAdapter;
import com.superc.shangjiaban.utils.ShareUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.superc.shangjiaban.base.Constant.ADDXIAOQU;
import static com.superc.shangjiaban.base.Constant.DIQU;
import static com.superc.shangjiaban.base.Constant.XQDETAIL;

/************************************************************************************
 @version: 1.0.0
 @description: 添加小区--编辑小区--通过mIsAdd判断  true--添加小区  false--编辑小区
 @author: user
 @time: 2018/3/5 16:25
 @变更历史:
 ************************************************************************************/
public class AddVillageActivity extends BaseActivity {

    @BindView(R.id.add_village_name)
    EditText mAddVillageName;
    @BindView(R.id.add_village_lainxiren)
    EditText mAddVillageLainxiren;
    @BindView(R.id.add_village_lxfs)
    EditText mAddVillageLxfs;
    @BindView(R.id.add_village_sheng)
    TextView mAddVillageSheng;
    @BindView(R.id.add_village_shi)
    TextView mAddVillageShi;
    @BindView(R.id.add_village_qu)
    TextView mAddVillageQu;
    @BindView(R.id.linearLayout)
    LinearLayout mLinearLayout;
    @BindView(R.id.add_village_jiedao)
    EditText mAddVillageJiedao;
    @BindView(R.id.add_village_tihuo)
    EditText mAddVillageTihuo;
    @BindView(R.id.add_village_peisongfei)
    EditText mAddVillagePeisongfei;
    @BindView(R.id.add_village_peisongtime)
    EditText mAddVillagePeisongtime;
    @BindView(R.id.add_village_miaoshu)
    EditText mAddVillageMiaoshu;
    @BindView(R.id.add_village_complete)
    TextView mAddVillageComplete;
    @BindView(R.id.textView6)
    TextView mtv;

    private int isWhat = 1;
    private VillageCityAdapter mVillageCityAdapter;
    private List<VillageCityBean.DataBean.AreaBean> mVillageCityBeanList;
    private ListView mMlv;
    private PopupWindow mPopupWindow;
    private String sheng = "";
    private String shi = "";
    private String xian = "";
    private String qu = "";

    private boolean mIsAdd = true;
    private VillageAllBean.UsersBean mExampleClass;
    private String uid = "";
    private String sheng_id;
    private String xian_id;
    private String shi_id;
    private String mId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_village);
        ButterKnife.bind(this);
        initListener();
    }

    @Override
    public void initListener() {
        uid = (String) ShareUtil.getInstance(this).get("uid", "");
        mtv.requestFocus();
        mAddVillageComplete.setOnClickListener(this);
        mAddVillageSheng.setOnClickListener(this);
        mAddVillageShi.setOnClickListener(this);
        mAddVillageQu.setOnClickListener(this);
        mVillageCityBeanList = new ArrayList<>();
        mVillageCityAdapter = new VillageCityAdapter(this, mVillageCityBeanList);
        setMsg();
        configPop();
    }

    public void setMsg() {
        if (mExampleClass != null) {
            mAddVillageName.setText(mExampleClass.getCommunityId());
            mAddVillageLainxiren.setText(mExampleClass.getCommunityId());
            mAddVillageLxfs.setText(mExampleClass.getQuname());
        }
    }

    @Override
    public void init() {
        uid = (String) ShareUtil.getInstance(this).get("uid", "");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mIsAdd = extras.getBoolean("isAdd");
        }
        if (mIsAdd) {
            setFinalContentL("添加小区", true);
        } else {
            setFinalContentL("编辑小区", true);
            mId = extras.getString("id");
            getXqData(mId);
        }


    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_village_sheng:
                isWhat = 1;
                getCityData("省", null, null, mAddVillageSheng);

                break;
            case R.id.add_village_shi:
                isWhat = 2;
                sheng = mAddVillageSheng.getText().toString();
                if (sheng.equals("省") || sheng.equals("")) {
                    showToast("请选择省");
                    return;
                }
                getCityData(null, "市", null, mAddVillageShi);

                break;
            case R.id.add_village_qu:
                isWhat = 3;
                shi = mAddVillageShi.getText().toString();
                if (shi.equals("市") || shi.equals("")) {
                    showToast("请选择市");
                    return;
                }
                xian = mAddVillageQu.getText().toString();
                getCityData(null, null, "县", mAddVillageQu);
                break;

            case R.id.add_village_complete:
                if (mIsAdd) {
                    addData();
                } else {
                    editData();
                }
                break;
        }
    }

    /*获取小区数据*/
    public void getXqData(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        toGetData(map, XQDETAIL, false, new CallNetBack() {
            @Override
            public void callNetBack(JSONObject data) {
                VillageDetailBean bean = new Gson().fromJson(data.toString(), VillageDetailBean.class);
                int code = bean.getCode();
                if (code == 200) {
                    setMssg(bean);
                }
                showToast(bean.getInfo());
            }
        });
    }

    /*编辑小区时设置数据*/
    public void setMssg(VillageDetailBean bean) {
        VillageDetailBean.DateBean date = bean.getDate();
        mAddVillageName.setText(date.getCommunityId());
        mAddVillageLainxiren.setText(date.getUsername());
        mAddVillageLxfs.setText(date.getPhone());
        mAddVillageSheng.setText(date.getShengname());
        mAddVillageShi.setText(date.getShiname());
        mAddVillageQu.setText(date.getQuname());
        mAddVillageJiedao.setText(date.getStreet());
        mAddVillageTihuo.setText(date.getAddress());
        mAddVillagePeisongfei.setText(date.getDeliverFee() + "");
        mAddVillagePeisongtime.setText(date.getDistributionTime());
        mAddVillageMiaoshu.setText(date.getContent());
        sheng_id = date.getSheng();
        shi_id = date.getShi();
        xian_id = date.getQu();

    }

    /*编辑完成*/
    public void editData() {
        String lianxiren = mAddVillageLainxiren.getText().toString();
        String lxfs = mAddVillageLxfs.getText().toString();
        String village_name = mAddVillageName.getText().toString();
        String jiedao = mAddVillageJiedao.getText().toString();
        if (village_name == null || village_name.equals("")) {
            showToast("请填写小区名称");
            return;
        }
        if (lianxiren == null || lianxiren.equals("")) {
            showToast("请填写联系人");
            return;
        }
        if (lxfs == null || lxfs.equals("")) {
            showToast("请填写联系方式");
            return;
        }
        if (sheng_id == null || sheng_id.equals("") || shi_id == null || shi_id.equals("") || xian_id == null || xian_id.equals("")) {
            showToast("请选择地址");
            return;
        }
        if (jiedao == null || jiedao.equals("")) {
            showToast("请填写详细地址");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("username", lianxiren);
        map.put("phone", lxfs);
        map.put("community_id", village_name);
        map.put("province_id", sheng_id);
        map.put("city_id", shi_id);
        map.put("district_id", xian_id);
        map.put("address", mAddVillageTihuo.getText().toString());
        map.put("deliver_fee", mAddVillagePeisongfei.getText().toString());
        map.put("distribution_time", mAddVillagePeisongtime.getText().toString());
        map.put("content", mAddVillageMiaoshu.getText().toString());
        map.put("street", jiedao);
        map.put("article_id", mId);
        toGetData(map, ADDXIAOQU, false, new CallNetBack() {
            @Override
            public void callNetBack(JSONObject data) {
                AddVillageBean addVillageBean = new Gson().fromJson(data.toString(), AddVillageBean.class);
                int code = addVillageBean.getCode();
                if (code == 200) {
                    AddVillageActivity.this.finish();
                }
                showToast(addVillageBean.getInfo());
            }
        });
    }


    /*新增小区*/
    public void addData() {
        String lianxiren = mAddVillageLainxiren.getText().toString();
        String lxfs = mAddVillageLxfs.getText().toString();
        String village_name = mAddVillageName.getText().toString();
        String jiedao = mAddVillageJiedao.getText().toString();
        if (village_name == null || village_name.equals("")) {
            showToast("请填写小区名称");
            return;
        }
        if (lianxiren == null || lianxiren.equals("")) {
            showToast("请填写联系人");
            return;
        }
        if (lxfs == null || lxfs.equals("")) {
            showToast("请填写联系方式");
            return;
        }
        if (sheng_id == null || sheng_id.equals("") || shi_id == null || shi_id.equals("") || xian_id == null || xian_id.equals("")) {
            showToast("请选择地址");
            return;
        }
        if (jiedao == null || jiedao.equals("")) {
            showToast("请填写详细地址");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("username", lianxiren);
        map.put("phone", lxfs);
        map.put("community_id", village_name);
        map.put("province_id", sheng_id);
        map.put("city_id", shi_id);
        map.put("district_id", xian_id);
        map.put("address", mAddVillageTihuo.getText().toString());
        map.put("deliver_fee", mAddVillagePeisongfei.getText().toString());
        map.put("distribution_time", mAddVillagePeisongtime.getText().toString());
        map.put("content", mAddVillageMiaoshu.getText().toString());
        map.put("street", jiedao);
        toGetData(map, ADDXIAOQU, false, new CallNetBack() {
            @Override
            public void callNetBack(JSONObject data) {
                AddVillageBean addVillageBean = new Gson().fromJson(data.toString(), AddVillageBean.class);
                int code = addVillageBean.getCode();
                if (code == 200) {
                    AddVillageActivity.this.finish();
                }
                showToast(addVillageBean.getInfo());
            }
        });
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
                        mAddVillageSheng.setText(areaBean.getName());
                        sheng_id = areaBean.getId();
                        if (!sheng.equals(areaBean.getName())) {
                            mAddVillageShi.setText("");
                            mAddVillageQu.setText("");
                            shi_id = "";
                            xian_id = "";
                        }
                        break;
                    case 2:
                        mAddVillageShi.setText(areaBean.getName());
                        shi_id = areaBean.getId();
                        if (!shi.equals(areaBean.getName())) {
                            mAddVillageQu.setText("");
                            xian_id = "";
                        }
                        break;
                    case 3:
                        xian_id = areaBean.getId();
                        mAddVillageQu.setText(areaBean.getName());
                        break;
                }
            }
        });
    }

    /*获取省市县*/
    public void getCityData(String sg, String si, String xn, final View v) {
        Map<String, String> map = new HashMap<>();
        if (sg == null) {
            map.put("pro", mAddVillageSheng.getText().toString());
            if (si == null) {
                map.put("city", mAddVillageShi.getText().toString());
                map.put("dis", mAddVillageQu.getText().toString());
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
        map.put("page", "1");
        map.put("biaoshi", "app");
        toGetData(map, DIQU, false, new CallNetBack() {
            @Override
            public void callNetBack(JSONObject data) {
                VillageCityBean cityBean = new Gson().fromJson(data.toString(), VillageCityBean.class);
                if (cityBean.getCode().equals("200")) {
                    mVillageCityBeanList.clear();
                    mVillageCityBeanList.addAll(cityBean.getData().getArea());
                    mVillageCityAdapter.notifyDataSetChanged();
                    if (mPopupWindow != null) {
                        mPopupWindow.showAsDropDown(v);
                    }
                }else {
                    showToast(cityBean.getInfo());
                }

            }
        });
    }

}
