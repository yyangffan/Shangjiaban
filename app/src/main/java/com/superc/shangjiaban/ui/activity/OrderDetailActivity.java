package com.superc.shangjiaban.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.superc.shangjiaban.R;
import com.superc.shangjiaban.base.BaseActivity;
import com.superc.shangjiaban.bean.DateUtil;
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
import butterknife.OnClick;

import static com.superc.shangjiaban.base.Constant.ORDERDETAIL;

/********************************************************************
 @version: 1.0.0
 @description: 订单--所有订单--订单详情
 @author: user
 @time: 2018/3/7 14:01
 @变更历史:
 ********************************************************************/
public class OrderDetailActivity extends BaseActivity {

    @BindView(R.id.order_detail_id)
    TextView mOrderDetailId;
    @BindView(R.id.order_detail_zffs)
    TextView mOrderDetailZffs;
    @BindView(R.id.order_detail_ddzt)
    TextView mOrderDetailDdzt;
    @BindView(R.id.order_detail_ddhao)
    TextView mOrderDetailDdhao;
    @BindView(R.id.order_detail_phone)
    TextView mOrderDetailPhone;
    @BindView(R.id.order_detail_xdtime)
    TextView mOrderDetailXdtime;
    @BindView(R.id.order_detail_huiyuan)
    TextView mOrderDetailHuiyuan;
    @BindView(R.id.order_detail_money)
    TextView mOrderDetailMoney;
    @BindView(R.id.order_detail_zftime)
    TextView mOrderDetailZftime;
    @BindView(R.id.order_detail_shouhuoren)
    TextView mOrderDetailShouhuoren;
    @BindView(R.id.order_detail_lianxifanghsi)
    TextView mOrderDetailLianxifanghsi;
    @BindView(R.id.order_detail_shouhuodz)
    TextView mOrderDetailShouhuodz;
    @BindView(R.id.order_detail_edit)
    TextView mOrderDetailEdit;
    @BindView(R.id.order_detail_sure)
    TextView mOrderDetailSure;
    @BindView(R.id.order_detail_recyclerView)
    MyListView mOrderDetailRecyclerView;
    @BindView(R.id.order_detail_allmoney)
    TextView mOrderDetailAllmoney;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;

    private List<OrderDetailBean.CountBean> mMapList;
    private OrderDetailLvAdapter mOrderDetailLvAdapter;
    private String mOrder_id;
    private String uid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        initListener();
        setFinalContentL("订单详情", true);
    }

    @Override
    public void initListener() {
        mScrollView.smoothScrollTo(0, Integer.MAX_VALUE);
        mMapList = new ArrayList<>();
        Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            mOrder_id = extras.getString("order_id");
            getMsg();
        }

        mOrderDetailLvAdapter = new OrderDetailLvAdapter(this, mMapList);
        mOrderDetailRecyclerView.setAdapter(mOrderDetailLvAdapter);
    }

    /*设置数据*/
    public void setMsg(OrderDetailBean.DateBean dateBean) {
        if (dateBean != null) {
            mOrderDetailId.setText(dateBean.getOrderId());
            if(dateBean.getPayCode().equals("1")){
                mOrderDetailZffs.setText("支付宝");
            }else if(dateBean.getPayCode().equals("2")){
                mOrderDetailZffs.setText("微信");
            }else {
                mOrderDetailZffs.setText("未支付");
            }
            mOrderDetailDdzt.setText("已确认");
            mOrderDetailDdhao.setText(dateBean.getOrderSn());
            mOrderDetailPhone.setText(dateBean.getMobile());
            mOrderDetailXdtime.setText(DateUtil.getStrTime(dateBean.getAddTime()));
//            mOrderDetailHuiyuan.setText(dateBean.getName());
            mOrderDetailMoney.setText(dateBean.getOrderAmount());
            mOrderDetailZftime.setText(dateBean.getPayTime()==null||dateBean.getPayTime().equals("")?"- -":DateUtil.getStrTime(dateBean.getPayTime()));
            mOrderDetailShouhuoren.setText(dateBean.getConsignee());
            mOrderDetailLianxifanghsi.setText(dateBean.getMobile());
            mOrderDetailShouhuodz.setText(dateBean.getAddress2());
            mOrderDetailAllmoney.setText(dateBean.getOrderAmount());
            mOrderDetailEdit.setText(dateBean.getAdminNote());
        }

    }

    @Override
    public void init() {
        uid= (String) ShareUtil.getInstance(this).get("uid","");

    }

    @Override
    public void getData() {

    }

    /*获取订单详情*/
    public void getMsg() {
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("order_id",mOrder_id);

        toGetData(map, ORDERDETAIL, false, new CallNetBack() {
            @Override
            public void callNetBack(JSONObject data) {
                OrderDetailBean detailBean=new Gson().fromJson(data.toString(),OrderDetailBean.class);
                String code = detailBean.getCode();
                showToast(detailBean.getInfo());
                if(code.equals("200")){
                    setMsg(detailBean.getDate());
                    mMapList.clear();
                    mMapList.addAll(detailBean.getCount());
                }else {
                    mMapList.clear();
                }
                mOrderDetailLvAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick(R.id.order_detail_sure)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.order_detail_sure:
                showToast("确定");
                break;


        }

    }
}
