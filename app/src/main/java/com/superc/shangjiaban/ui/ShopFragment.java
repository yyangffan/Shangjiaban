package com.superc.shangjiaban.ui;


import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.superc.shangjiaban.R;
import com.superc.shangjiaban.base.BaseFragment;
import com.superc.shangjiaban.bean.HttpBean;
import com.superc.shangjiaban.bean.ShopAllListBean;
import com.superc.shangjiaban.bean.ShopFhBean;
import com.superc.shangjiaban.bean.ShopZtBean;
import com.superc.shangjiaban.others.CustomListener;
import com.superc.shangjiaban.others.DialogUtil;
import com.superc.shangjiaban.others.PopUpUtil;
import com.superc.shangjiaban.others.PublicBean;
import com.superc.shangjiaban.ui.activity.OrderDetailActivity;
import com.superc.shangjiaban.ui.activity.OrderSearchActivity;
import com.superc.shangjiaban.ui.activity.OrderSearchZtActivity;
import com.superc.shangjiaban.ui.activity.OrderSearchfhActivity;
import com.superc.shangjiaban.ui.activity.ShopFHActivity;
import com.superc.shangjiaban.ui.adapter.ShopAllAdapter;
import com.superc.shangjiaban.ui.adapter.ShopFhAdapter;
import com.superc.shangjiaban.ui.adapter.ShopZtAdapter;
import com.superc.shangjiaban.utils.ShareUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.superc.shangjiaban.base.Constant.DELIVERYLIST;
import static com.superc.shangjiaban.base.Constant.PENDINGLIST;
import static com.superc.shangjiaban.base.Constant.PICKEDUP;
import static com.superc.shangjiaban.base.Constant.SHOPINDEX;
import static com.superc.shangjiaban.base.Otehers.REQUESTCODE_ALLDDSEARCH;
import static com.superc.shangjiaban.base.Otehers.REQUESTCODE_ALLDZITI;
import static com.superc.shangjiaban.base.Otehers.REQUESTCODE_DDSEARCH;

/********************************************************************
 @version: 1.0.0
 @description: 订单界面  isWhat 用来标示显示哪一个
 @author: user
 @time: 2018/3/7 9:44
 @变更历史:
 ********************************************************************/
public class ShopFragment extends BaseFragment implements TextToSpeech.OnInitListener {
    @BindView(R.id.shop_rb_all)
    RadioButton mShopRbAll;
    @BindView(R.id.shop_rb_waitfh)
    RadioButton mShopRbWaitfh;
    @BindView(R.id.shop_rb_waitown)
    RadioButton mShopRbWaitown;
    @BindView(R.id.shop_rg)
    RadioGroup mShopRg;
    @BindView(R.id.shop_tv_search)
    TextView mShopTvSearch;
    @BindView(R.id.shop_imgv_shuoming)
    ImageView mShopImgvShuoming;
    @BindView(R.id.shop_rc)
    RecyclerView mShopRc;
    @BindView(R.id.shop_rb_choseall)
    RadioButton mShopRbChoseall;
    @BindView(R.id.relativeLayout)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.smartrefresh)
    SmartRefreshLayout mSmartRefreshLayout;
    private View mView;
    private RadioButton[] rbs;
    private int isWhat = 0;

    /*所有订单*/
    private ShopAllAdapter mShopAllAdapter;
    private List<ShopAllListBean.DateBean> mAllList;
    public int all_page = 1;
    /*待发货*/
    private ShopFhAdapter mShopFhAdapter;
    private List<ShopFhBean.DateBean> mFHList;
    public int fh_page = 1;
    private boolean isCheck = false;
    /*待自提*/
    private ShopZtAdapter mShopZtAdapter;
    private List<ShopZtBean.DateBean> mOwnList;
    public int zt_page = 1;

    private MainActivity mActivity;
    private TextToSpeech tts;

    private String uid = "";

    private String keyword, st_time, ed_time, order_sn, order_status,
            pay_status, shipp_status, order_type = "";/*这些都是所以订单使用*/
    //如下的biaoshi在为1的时候表示进行批量发货，否则为查询列表
    private String sheng, shi, qu, xiaoqu = "";/*这些都是待发货使用*/
    private String sheng_id, shi_id, qu_id, xiaoqu_id, huodong, huodong_id, biaoshi = "";/*这些都是待发货使用*/
    //如下的zt_biaoshi在为1的时候为通知操作，否则为查询列表
    private String zt_keyword, zt_huodong_id, zt_xiaoqu_id, zt_xiaoqu, zt_huodong, zt_biaoshi = "";/*这些都是待自提列表使用*/
    private String role_id = "";
    private PublicBean mPublicBean_allList;

    private PopUpUtil mPopUpUtil;
    private String[] mString_explain;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_shop, container, false);
        ButterKnife.bind(this, mView);
        mActivity = (MainActivity) this.getActivity();
        init();
        return mView;
    }

    @Override
    public void init() {
        uid = (String) ShareUtil.getInstance(this.getActivity()).get("uid", "");
        role_id = (String) ShareUtil.getInstance(this.getActivity()).get("role_id", "");
        mShopTvSearch.setOnClickListener(this);
        mShopImgvShuoming.setOnClickListener(this);
        mShopRbChoseall.setOnClickListener(this);
        mPopUpUtil = new PopUpUtil(this.getActivity());
        mString_explain = new String[]{};

        rbs = new RadioButton[]{mShopRbAll, mShopRbWaitfh, mShopRbWaitown};
        mAllList = new ArrayList<>();
        mFHList = new ArrayList<>();
        mOwnList = new ArrayList<>();
        mShopAllAdapter = new ShopAllAdapter(this.getActivity(), mAllList);
        mShopFhAdapter = new ShopFhAdapter(this.getActivity(), mFHList);
        mShopZtAdapter = new ShopZtAdapter(this.getActivity(), mOwnList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mShopRc.setLayoutManager(layoutManager);
        mShopRc.setAdapter(mShopAllAdapter);

        tts = new TextToSpeech(this.getActivity(), this);

//        getAllList();//首先加载所有订单列表
        mShopRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int view_id) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(ShopFragment.this.getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mShopRc.setLayoutManager(layoutManager);
                switch (view_id) {
                    case R.id.shop_rb_all:
                        dingdanAll();
                        resetZtCanshu();
                        break;
                    case R.id.shop_rb_waitfh:
                        /*不进行全选操作了*/
//                        mRelativeLayout.setVisibility(View.VISIBLE);
                        changeColor(1);
                        mSmartRefreshLayout.setEnableLoadmore(true);
                        fh_page = 1;
                        mShopRc.setAdapter(mShopFhAdapter);
                        getWaitFh();
                        resetALlCanshu();
                        resetZtCanshu();
                        break;
                    case R.id.shop_rb_waitown:
                        mRelativeLayout.setVisibility(View.GONE);
                        changeColor(2);
                        mSmartRefreshLayout.setEnableLoadmore(true);
                        zt_page = 1;
                        mShopRc.setAdapter(mShopZtAdapter);
                        getWaitOwn();
                        resetFhCanshu();
                        resetALlCanshu();
                        break;
                }
            }
        });
        mShopAllAdapter.setCustomListener(new CustomListener() {
            @Override
            public void CustomeListener(int view_id, int postion) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("order_id", mAllList.get(postion).getOrderId());
                stActivity(OrderDetailActivity.class, bundle);
            }
        });

        mShopFhAdapter.setCustomListener(new CustomListener() {
            @Override
            public void CustomeListener(int view_id, int postion) {
                ShopFhBean.DateBean exampleClass = mFHList.get(postion);
                Bundle bundle = new Bundle();
                bundle.putString("order_id", exampleClass.getOrderId());
                bundle.putBoolean("is_one", true);
                stActivity(ShopFHActivity.class, bundle);

            }
        });
        mShopFhAdapter.setOnDuiGouClickListener(new ShopFhAdapter.OnDuiGouClickListener() {
            @Override
            public void OnDuiGouClickListener(int position) {
                ShopFhBean.DateBean exampleClass = mFHList.get(position);
                boolean go = exampleClass.isC();
                exampleClass.setC(!go);
                mShopFhAdapter.notifyDataSetChanged();
            }
        });
        mShopFhAdapter.setOnItemClickListener(new ShopFhAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("order_id", mFHList.get(position).getOrderId());
                stActivity(OrderDetailActivity.class, bundle);
//                boolean isAllChose = true;
//                for (ShopFhBean.DateBean ec : mFHList) {
//                    if (!ec.isGo()) {
//                        isAllChose = false;
//                    }
//                }
//                mShopRbChoseall.setChecked(isAllChose);
//                isCheck = isAllChose;
//                if (isAllChose) {
//                    mShopRbChoseall.setText("取消全选");
//                } else {
//                    mShopRbChoseall.setText("全选");
//                }
            }
        });

        mShopZtAdapter.setCustomListener(new CustomListener() {
            @Override
            public void CustomeListener(int view_id, int postion) {
                ShopZtBean.DateBean dateBean = mOwnList.get(postion);
                String state = dateBean.getState();
                String orderId = dateBean.getOrderId();
                String order_prom_type = dateBean.getOrder_prom_type();
                // TODO: 2018/3/26 之后放开注释内容
//                if (role_id.equals("1")) {/*只有role_id为1情况下才能进行该操作*/
//                if (state.equals("1")) {/*进行通知操作--弹窗*/
//                    gotoRemind();
//                } else if (state.equals("2")) {/*进行已自提操作--弹窗*/
                gotoYiTH(orderId, order_prom_type);
//                }
//                } else {
//                    showToast("权限不足");
//                }
            }
        });
        mShopZtAdapter.setOnItemClickListener(new ShopZtAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("order_id", mOwnList.get(position).getOrderId());
                stActivity(OrderDetailActivity.class, bundle);
            }
        });

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                switch (isWhat) {
                    case 0:
                        all_page = 1;
                        mSmartRefreshLayout.setEnableLoadmore(true);
                        getAllList();
                        break;
                    case 1:
                        fh_page = 1;
                        mSmartRefreshLayout.setEnableLoadmore(true);
                        getWaitFh();
                        break;
                    case 2:
                        zt_page = 1;
                        mSmartRefreshLayout.setEnableLoadmore(true);
                        getWaitOwn();
                        break;
                }
            }
        });
        mSmartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                switch (isWhat) {
                    case 0:
                        getAllList();
                        break;
                    case 1:
                        getWaitFh();
                        break;
                    case 2:
                        getWaitOwn();
                        break;
                }
            }
        });

    }

    /*MainActivity跳转时所有订单的跳转*/
    public void refreshDingdanAll() {
        if (mShopRbAll != null) {
            mShopRbAll.setChecked(true);
        }
    }

    public void dingdanAll() {
        mRelativeLayout.setVisibility(View.GONE);
        changeColor(0);
        mSmartRefreshLayout.setEnableLoadmore(true);
        all_page = 1;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mShopRc.setLayoutManager(layoutManager);
        mShopRc.setAdapter(mShopAllAdapter);
        getAllList();
        resetFhCanshu();/*重置待发货的搜索条件*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shop_tv_search://点击搜索
                if (isWhat == 0) {/*所有订单*/
                    Intent intent = new Intent(this.getActivity(), OrderSearchActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", mPublicBean_allList);
                    intent.putExtras(bundle);
                    ((MainActivity) ShopFragment.this.getActivity()).startActivityForResult(intent, REQUESTCODE_ALLDDSEARCH);
                } else if (isWhat == 1) {/*待发货*/
                    Intent intent = new Intent(ShopFragment.this.getActivity(), OrderSearchfhActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("sheng", sheng);
                    bundle.putString("shi", shi);
                    bundle.putString("quxian", qu);
                    bundle.putString("xiaoqu", xiaoqu);
                    bundle.putString("huodong", huodong);

                    bundle.putString("sheng_id", sheng_id);
                    bundle.putString("shi_id", shi_id);
                    bundle.putString("quxian_id", qu_id);
                    bundle.putString("xiaoqu_id", xiaoqu_id);
                    bundle.putString("huodong_id", huodong_id);
                    intent.putExtras(bundle);
                    ((MainActivity) ShopFragment.this.getActivity()).startActivityForResult(intent, REQUESTCODE_DDSEARCH);
                    Log.e("传递小区时候的数据2", xiaoqu);
                } else if (isWhat == 2) {/*待自提*/
                    Intent intent = new Intent(ShopFragment.this.getActivity(), OrderSearchZtActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("xiaoqu_id", zt_xiaoqu_id);
                    bundle.putString("huodong_id", zt_huodong_id);

                    bundle.putString("xiaoqu", zt_xiaoqu);
                    bundle.putString("huodong", zt_huodong);
                    bundle.putString("keyword", zt_keyword);
                    intent.putExtras(bundle);

                    ((MainActivity) ShopFragment.this.getActivity()).startActivityForResult(intent, REQUESTCODE_ALLDZITI);
                }
                break;
            case R.id.shop_imgv_shuoming://点击说明
                mPopUpUtil.showPop(mString_explain, mShopImgvShuoming);
                break;
            case R.id.shop_rb_choseall:/*全选按钮*/
//                if (isCheck) {
//                    mShopRbChoseall.setChecked(false);
//                    mShopRbChoseall.setText("全选");
//                } else {
//                    mShopRbChoseall.setChecked(true);
//                    mShopRbChoseall.setText("取消全选");
//                }
//                isCheck = !isCheck;
//                for (ShopFhBean.DateBean exampleClass : mFHList) {
//                    exampleClass.setGo(mShopRbChoseall.isChecked());
//                }
//                mShopFhAdapter.notifyDataSetChanged();
                break;


        }
    }

    /*所有订单---进行搜索条件后的所有参数进行赋值---在MainActivity的onActivityForResult中会进行调用*/
    public void setCanshu(PublicBean publicBean) {
        this.mPublicBean_allList = publicBean;
        this.st_time = publicBean.getSt_time();
        this.ed_time = publicBean.getEd_time();
        this.order_sn = publicBean.getOther_one();
        this.order_status = publicBean.getDd_zt_id();
        this.pay_status = publicBean.getZf_zt_id();
        this.shipp_status = publicBean.getFh_zt_id();
        this.order_type = publicBean.getDd_lx_id();
        mShopTvSearch.setText(publicBean.getThePj());
        getAllList();
    }

    /*重置所有订单的搜索条件*/
    public void resetALlCanshu() {
        this.st_time = "";
        this.ed_time = "";
        this.order_sn = "";
        this.order_status = "";
        this.pay_status = "";
        this.shipp_status = "";
        this.order_type = "";
        this.mPublicBean_allList = null;
        mShopTvSearch.setText("");
    }

    /*所有订单*/
    public void getAllList() {
        mString_explain = ShopFragment.this.getActivity().getResources().getStringArray(R.array.shop_all);
        mShopTvSearch.setHint("筛选订单");
        isWhat = 0;
        mActivity.setTitleRight(isWhat + "");

        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("keywords", keyword);
        map.put("page", all_page + "");
        map.put("size", "15");
        map.put("add_time_begin", st_time);
        map.put("add_time_end", ed_time);
        map.put("order_sn", order_sn);
        map.put("order_status", order_status);
        map.put("pay_status", pay_status);
        map.put("pay_code", "");
        map.put("shipping_status", shipp_status);
        map.put("order_prom_type", order_type);

        toGetData(map, SHOPINDEX, false, ShopAllListBean.class, new CallNetBack<ShopAllListBean>() {
            @Override
            public void callNetBack(ShopAllListBean data) {
                String code = data.getCode();
                String info = data.getInfo();
                showToast(info);
                mSmartRefreshLayout.finishRefresh();
                mSmartRefreshLayout.finishLoadmore();
                if (code.equals("200")) {
                    if (all_page == 1) {
                        mAllList.clear();
                        mAllList.addAll(data.getDate());
                        if (data.getDate().size() < 15) {
                            mSmartRefreshLayout.setEnableLoadmore(false);
                        }
                    } else {
                        for (ShopAllListBean.DateBean bean : data.getDate()) {
                            mAllList.add(bean);
                        }
                        if (data.getDate().size() < 15) {
                            mSmartRefreshLayout.setEnableLoadmore(false);
                        }
                    }
                    mShopAllAdapter.notifyDataSetChanged();
                    all_page++;
                } else if (code.equals("201")) {
                    mAllList.clear();
                    mShopAllAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    /*条件搜索时的参数设置---在MainActivity的onActivityForResult中会进行调用*/
    public void setFhCanshu(String sheng, String shi, String qu, String xiaoqu, String huodong) {
        this.sheng = sheng;
        this.shi = shi;
        this.qu = qu;
        this.xiaoqu = xiaoqu;
        this.huodong = huodong;
        if (xiaoqu == null || xiaoqu.equals("")) {
            mShopTvSearch.setText("");
        } else {
            mShopTvSearch.setText(sheng + "-" + shi + "-" + qu + "-" + xiaoqu + ((huodong == null || huodong.equals("")) ? "" : "-") + (huodong == null || huodong.equals("") ? "" : huodong));
        }
    }

    public void setFhSearchCanshu(String sheng_id, String shi_id, String qu_id, String xiaoqu_id, String huodong_id) {
        this.sheng_id = sheng_id;
        this.shi_id = shi_id;
        this.qu_id = qu_id;
        this.xiaoqu_id = xiaoqu_id;
        this.huodong_id = huodong_id;
        getWaitFh();
    }

    public void resetFhCanshu() {
        sheng = "";
        shi = "";
        qu = "";
        xiaoqu = "";
        sheng_id = "";
        shi_id = "";
        qu_id = "";
        xiaoqu_id = "";
        huodong = "";
        huodong_id = "";
        biaoshi = "";
        mShopTvSearch.setText("");
    }

    /*待发货--biaoshi在这里传“”在进行批量发货的时候才赋值为1*/
    public void getWaitFh() {
        mString_explain = ShopFragment.this.getActivity().getResources().getStringArray(R.array.shop_fahuo);
        mShopTvSearch.setHint("请选择地址");
        isWhat = 1;
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("order_sn", "");
        map.put("province_id", sheng_id);
        map.put("city", shi_id);
        map.put("district", qu_id);
        map.put("districts", xiaoqu_id);
        map.put("huodong", huodong_id);
        map.put("biaoshi", "");
        map.put("page", fh_page + "");
        map.put("size", "15");
        toGetData(map, DELIVERYLIST, false, ShopFhBean.class, new CallNetBack<ShopFhBean>() {
            @Override
            public void callNetBack(ShopFhBean data) {
                String code = data.getCode();
                String info = data.getInfo();
                showToast(info);
                if (code.equals("200")) {
                    mSmartRefreshLayout.finishRefresh();
                    mSmartRefreshLayout.finishLoadmore();
                    if (fh_page == 1) {
                        mFHList.clear();
                        /*如果是进行搜索过来的则可以进行选择操作*/
                        if (xiaoqu_id != null && !xiaoqu_id.equals("")) {
                            for (ShopFhBean.DateBean bean : data.getDate()) {
                                bean.setC(false);
                            }
                        }
                        mFHList.addAll(data.getDate());
                        if (data.getDate().size() < 15) {
                            mSmartRefreshLayout.setEnableLoadmore(false);
                        }
                    } else {
                        for (ShopFhBean.DateBean bean : data.getDate()) {
                            /*如果是进行搜索过来的则可以进行选择操作*/
                            if (xiaoqu_id != null && !xiaoqu_id.equals("")) {
                                bean.setC(false);
                            }
                            mFHList.add(bean);
                        }
                        if (data.getDate().size() < 15) {
                            mSmartRefreshLayout.setEnableLoadmore(false);
                        }
                    }
                    mShopFhAdapter.notifyDataSetChanged();
                    fh_page++;
                } else if (code.equals("201")) {
                    mSmartRefreshLayout.finishRefresh();
                    mSmartRefreshLayout.finishLoadmore();
                    mFHList.clear();
                    mShopFhAdapter.notifyDataSetChanged();
                }
                /*进行判断是否显示可勾选*/
                if (xiaoqu_id != null && !xiaoqu_id.equals("") && mFHList.size() != 0) {
                    mShopFhAdapter.setShowImg(true);
                    mActivity.setTitleRight(isWhat + "");
                } else {
                    mShopFhAdapter.setShowImg(false);
                    mActivity.setTitleRight(0 + "");
                }
            }
        });

    }

    /*进行批量发货*/
    public void gotoPLFh() {
        DialogUtil.getmInstance(this.getActivity()).showRemindDialog().setRemindTvContent("进行批量发货？", "取消", "确定").setOnTvClickListener(new DialogUtil.OnTvClickListener() {
            @Override
            public void OnTvClickListener(int view_id) {
                switch (view_id) {
                    case R.id.dialog_remind_cancle:

                        break;
                    case R.id.dialog_remind_sure:
                        /*这里通过判断 mFHList 中的数据的c是否为true  为true则是进行选择的通过拼接过去进行发货展示*/
                        if (mFHList != null && mFHList.size() != 0) {
                            String order_sns = "";
                            for (ShopFhBean.DateBean bean : mFHList) {
                                order_sns = order_sns + bean.getOrderSn() + ",";
                            }
                            piliangfahuo(order_sns.substring(0, order_sns.length() - 1));
                        } else {
                            showToast("无可发货商品");
                        }
                        break;
                }

            }
        });
    }

    /*进行通知取货*/
    public void gotoTzQh() {
        DialogUtil.getmInstance(this.getActivity()).showRemindDialog().setRemindTvContent("通知取货？", "取消", "确定").setOnTvClickListener(new DialogUtil.OnTvClickListener() {
            @Override
            public void OnTvClickListener(int view_id) {
                switch (view_id) {
                    case R.id.dialog_remind_cancle:

                        break;
                    case R.id.dialog_remind_sure:
                        /*这里通过判断 mFHList 中的数据的c是否为true  为true则是进行选择的通过拼接过去进行发货展示*/
                        if (mOwnList.size() != 0) {
                            toTongzhi();
                        } else {
                            showToast("无可通知用户");
                        }
                        break;
                }

            }
        });
    }

    public void toTongzhi() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("keywords", zt_keyword);
        map.put("huodong", zt_huodong_id);
        map.put("province_id", zt_xiaoqu_id);
        map.put("biaoshi", "1");
        map.put("page", zt_page + "");
        map.put("size", "15");

        toGetData(map, PENDINGLIST, false, ShopZtBean.class, new CallNetBack<ShopZtBean>() {
            @Override
            public void callNetBack(ShopZtBean data) {
                String code = data.getCode();
                String info = data.getInfo();
                showToast(info);
                if (code.equals("200")) {
                } else if (code.equals("201")) {
                }

            }
        });

    }

    // TODO: 2018/4/27   /*批量发货接口--还没有进行上传订单号的字段*/
    public void piliangfahuo(String order_sns) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
//        map.put("order_sn", order_sns);
        map.put("province_id", sheng_id);
        map.put("city", shi_id);
        map.put("district", qu_id);
        map.put("districts", xiaoqu_id);
        map.put("huodong", huodong_id);
        map.put("biaoshi", "1");
        toGetData(map, DELIVERYLIST, false, ShopFhBean.class, new CallNetBack<ShopFhBean>() {
            @Override
            public void callNetBack(ShopFhBean data) {
                String code = data.getCode();
                String info = data.getInfo();
                showToast(info);
                if (code.equals("200")) {
                    fh_page = 1;
                    getWaitFh();
                } else if (code.equals("201")) {
                }
            }
        });
    }


    /*设置自提参数--在MainActivity的onActivityForResult中会进行调用*/
    public void setZtCanshu(String zt_keyword, String zt_huodong_id, String zt_xiaoqu_id, String zt_biaoshi) {
        this.zt_keyword = zt_keyword;
        this.zt_huodong_id = zt_huodong_id;
        this.zt_xiaoqu_id = zt_xiaoqu_id;
        this.zt_biaoshi = zt_biaoshi;
        getWaitOwn();
    }

    public void setZtWzCanshu(String zt_huodong, String zt_xiaoqu, String zt_keyword) {
        this.zt_keyword = zt_keyword;
        this.zt_huodong = zt_huodong;
        this.zt_xiaoqu = zt_xiaoqu;
        if (zt_huodong.equals("") && zt_xiaoqu.equals("") && zt_keyword.equals("")) {
            mShopTvSearch.setText("");
        } else {
            mShopTvSearch.setText(zt_xiaoqu + ((zt_huodong == null || zt_huodong.equals("")) ? "" : "-") + zt_huodong + ((zt_keyword == null || zt_keyword.equals("")) ? "" : "-") + zt_keyword);
        }
    }

    public void resetZtCanshu() {
        this.zt_keyword = "";
        this.zt_huodong_id = "";
        this.zt_xiaoqu_id = "";
        this.zt_biaoshi = "";
        this.zt_keyword = "";
        this.zt_huodong = "";
        this.zt_xiaoqu = "";
    }

    /*待自提--这里的biaoshi传“”因为是列表，进行通知的时候传1*/
    public void getWaitOwn() {
        mString_explain = ShopFragment.this.getActivity().getResources().getStringArray(R.array.shop_ziti);

        mShopTvSearch.setHint("筛选订单");
        isWhat = 2;
        mActivity.setTitleRight(isWhat + "");

        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("keywords", zt_keyword);
        map.put("huodong", zt_huodong_id);
        map.put("province_id", zt_xiaoqu_id);
        map.put("biaoshi", "");
        map.put("page", zt_page + "");
        map.put("size", "15");
        if (zt_page == 1) {
            mOwnList.clear();
            mShopZtAdapter.notifyDataSetChanged();
        }

        toGetData(map, PENDINGLIST, false, ShopZtBean.class, new CallNetBack<ShopZtBean>() {
            @Override
            public void callNetBack(ShopZtBean data) {
                String code = data.getCode();
                String info = data.getInfo();
                showToast(info);
                if (code.equals("200")) {
                    mSmartRefreshLayout.finishRefresh();
                    mSmartRefreshLayout.finishLoadmore();
                    if (zt_page == 1) {
                        mOwnList.clear();
                        mOwnList.addAll(data.getDate());
                        if (data.getDate().size() < 15) {
                            mSmartRefreshLayout.setEnableLoadmore(false);
                        }
                    } else {
                        for (ShopZtBean.DateBean bean : data.getDate()) {
                            mOwnList.add(bean);
                        }
                        if (data.getDate().size() < 15) {
                            mSmartRefreshLayout.setEnableLoadmore(false);
                        }
                    }
                    mShopZtAdapter.notifyDataSetChanged();
                    zt_page++;
                } else if (code.equals("201")) {
                    mSmartRefreshLayout.finishRefresh();
                    mSmartRefreshLayout.finishLoadmore();
                    mOwnList.clear();
                    mShopZtAdapter.notifyDataSetChanged();
                }
                   /*进行判断是否显示可勾选*/
                if (zt_xiaoqu_id == null && zt_keyword == null && zt_huodong_id == null) {
                    mShopFhAdapter.setShowImg(false);
                    mActivity.setTitleRight(0 + "");
                } else {
                    if (zt_xiaoqu_id.equals("") && zt_keyword.equals("") && zt_huodong_id.equals("")) {
                        mShopFhAdapter.setShowImg(false);
                        mActivity.setTitleRight(0 + "");
                    } else {
                        mShopFhAdapter.setShowImg(true);
                        mActivity.setTitleRightT();
                    }
                }
            }
        });

    }

    /*待通知的操作*/
    public void gotoRemind() {
        DialogUtil.getmInstance(this.getActivity()).showRemindDialog().setRemindTvContent("确认通知取货？", "取消", "确定").setOnTvClickListener(new DialogUtil.OnTvClickListener() {
            @Override
            public void OnTvClickListener(int view_id) {
                switch (view_id) {
                    case R.id.dialog_remind_cancle:

                        break;
                    case R.id.dialog_remind_sure:
                        toReimd();
                        break;
                }

            }
        });
    }

    /*进行通知的接口操作*/
    public void toReimd() {
        showToast("开发中...");
//        Map<String,String> map=new HashMap<>();
//        map.put("uid",uid);
//        map.put("biaoshi","1");
//
//        toGetData(map, PENDINGLIST, false, HttpBean.class, new CallNetBack<HttpBean>() {
//            @Override
//            public void callNetBack(HttpBean data) {
//
//            }
//        });


    }

    /*已提货的操作*/
    public void gotoYiTH(final String orderId, final String order_prom_type) {
        DialogUtil.getmInstance(this.getActivity()).showRemindDialog().setRemindTvContent("确认已提货？", "取消", "确定").setOnTvClickListener(new DialogUtil.OnTvClickListener() {
            @Override
            public void OnTvClickListener(int view_id) {
                switch (view_id) {
                    case R.id.dialog_remind_cancle:

                        break;
                    case R.id.dialog_remind_sure:
                        pickUp(orderId, order_prom_type);
                        break;
                }

            }
        });

    }

    /*进行待自提*/
    public void pickUp(String order_id, String order_prom_type) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("order_id", order_id);
        if (order_prom_type.equals("6")) {
            map.put("biaoshi", "6");
        }

        toGetData(map, PICKEDUP, false, HttpBean.class, new CallNetBack<HttpBean>() {
            @Override
            public void callNetBack(HttpBean data) {
                int code = data.getCode();
                String info = data.getInfo();
                showToast(info);
                if (code == 200) {
                    zt_page = 1;
//                    getWaitOwn();
                    mSmartRefreshLayout.autoRefresh();
                }
            }
        });

    }

    /*用来判断右上角显示什么(0--批量发货 1--无 2--通知发货*/
    public void showWhat() {
        boolean isAll = false;
        if (mActivity != null) {
            for (int i = 0; i < rbs.length; i++) {
                RadioButton rb = rbs[i];
                if (rb.isChecked()) {
                    if (i == 1) {
                        /*在待发货中如果不是通过搜索过来的就不能进行批量发货(隐藏掉批量发货功能)*/
                        if (xiaoqu_id != null && !xiaoqu_id.equals("") && mFHList.size() != 0) {
                            mActivity.setTitleRight(i + "");
                        } else {
                            mActivity.setTitleRight(0 + "");
                        }
                    } else {
                        mActivity.setTitleRight(i + "");
                    }
                    isAll = true;
                }
            }
            if (!isAll) {
                mActivity.setTitleRight(0 + "");
            }
        }
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.autoRefresh();
        }else {
            showToast("请稍候刷新");
        }
    }


    public void changeColor(int pos) {
        for (int i = 0; i < rbs.length; i++) {
            RadioButton rb = rbs[i];
            if (i == pos) {
                rb.setTextColor(ShopFragment.this.getResources().getColor(R.color.black));
            } else {
                rb.setTextColor(ShopFragment.this.getResources().getColor(R.color.gray_nine));
            }
        }
    }

    /*语音通知*/
    @Override
    public void onInit(int status) {
        if (status == tts.SUCCESS) {
            // Toast.makeText(MainActivity.this,"成功输出语音",
            // Toast.LENGTH_SHORT).show();
            // Locale loc1=new Locale("us");
            // Locale loc2=new Locale("china");
            int result1 = tts.setLanguage(Locale.US);
            int result2 = tts.setLanguage(Locale.CHINESE);
            if (result1 == TextToSpeech.LANG_MISSING_DATA
                    || result1 == TextToSpeech.LANG_NOT_SUPPORTED
                    || result2 == TextToSpeech.LANG_MISSING_DATA
                    || result2 == TextToSpeech.LANG_NOT_SUPPORTED)

            {
                showToast("数据丢失或不支持");
            }
        }

    }
}
