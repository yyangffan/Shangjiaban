package com.superc.shangjiaban.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.superc.shangjiaban.R;
import com.superc.shangjiaban.base.BaseActivity;
import com.superc.shangjiaban.bean.DateUtil;
import com.superc.shangjiaban.bean.HttpBean;
import com.superc.shangjiaban.bean.OrderDetailBean;
import com.superc.shangjiaban.ui.adapter.OrderDetailLvAdapter;
import com.superc.shangjiaban.utils.ShareUtil;
import com.superc.shangjiaban.views.MyListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.superc.shangjiaban.base.Constant.DELIVERGOODSPAGE;
import static com.superc.shangjiaban.base.Constant.ORDERDETAIL;

/********************************************************************
 @version: 1.0.0
 @description: 批量发货--发货界面
 @author: user
 @time: 2018/3/26 13:54
 @变更历史:
 ********************************************************************/
public class ShopFHActivity extends BaseActivity {

    @BindView(R.id.shop_fh_id)
    TextView mShopFhId;
    @BindView(R.id.shop_fh_xdtime)
    TextView mShopFhXdtime;
    @BindView(R.id.shop_fh_name)
    TextView mShopFhName;
    @BindView(R.id.shop_fh_lxfs)
    TextView mShopFhLxfs;
    @BindView(R.id.shop_fh_shdz)
    TextView mShopFhShdz;
    @BindView(R.id.shop_fh_editText)
    EditText mShopFhEditText;
    @BindView(R.id.shop_fh_sure)
    TextView mShopFhSure;
    @BindView(R.id.shop_fh_recycle)
    MyListView mShopFhRecycle;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    private String mOrder_id;
    private String uid = "";

    private List<OrderDetailBean.CountBean> mMapList;
    private OrderDetailLvAdapter mOrderDetailLvAdapter;
    private boolean mIs_one;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_fh);
        ButterKnife.bind(this);
        initListener();
        setFinalContentL("发货", true);
    }

    @Override
    public void initListener() {
        mMapList = new ArrayList<>();
        mOrderDetailLvAdapter = new OrderDetailLvAdapter(this, mMapList);
        mShopFhRecycle.setAdapter(mOrderDetailLvAdapter);

        scrollView.smoothScrollTo(0, Integer.MAX_VALUE);
        mShopFhSure.setOnClickListener(this);
    }

    @Override
    public void init() {
        uid = (String) ShareUtil.getInstance(this).get("uid", "");
        Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            mIs_one = extras.getBoolean("is_one");
            mOrder_id = extras.getString("order_id");
            if (mIs_one) {//单个发货
                getMsg();
            } else {//批量发货

            }
        }
    }

    @Override
    public void getData() {

    }

    public void getMsg() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("order_id", mOrder_id);
        toGetData(map, ORDERDETAIL, false, new CallNetBack() {
            @Override
            public void callNetBack(JSONObject data) {
                OrderDetailBean detailBean = new Gson().fromJson(data.toString(), OrderDetailBean.class);
                String code = detailBean.getCode();
                showToast(detailBean.getInfo());
                if (code.equals("200")) {
                    setMsg(detailBean.getDate());
                    mMapList.clear();
                    mMapList.addAll(detailBean.getCount());
                } else {
                    mMapList.clear();
                }
                mOrderDetailLvAdapter.notifyDataSetChanged();
            }
        });
    }

    public void setMsg(OrderDetailBean.DateBean bean) {
        mShopFhId.setText(bean.getOrderId());
        mShopFhXdtime.setText(bean.getAddTime() == null || bean.getAddTime().equals("") ? "" : DateUtil.getStrTime(bean.getAddTime()));
        mShopFhName.setText(bean.getConsignee());
        mShopFhLxfs.setText(bean.getMobile());
        mShopFhShdz.setText(bean.getAddress2());


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shop_fh_sure:
                if (mIs_one) {
                    gotoFh();
                } else {


                }

                break;


        }
    }
    /*单独发货*/
    public void gotoFh() {
        String note = mShopFhEditText.getText().toString();
        if(note==null||note.equals("")){
            showToast("备注不能为空");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("order_id",mOrder_id);
        map.put("biaoshi","app");
        map.put("note",note);
        toGetData(map, DELIVERGOODSPAGE, false, new CallNetBack() {
            @Override
            public void callNetBack(JSONObject data) {
                HttpBean bean = new Gson().fromJson(data.toString(), HttpBean.class);
                showToast(bean.getInfo());
            }
        });


    }

}
