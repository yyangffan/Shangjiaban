package com.superc.shangjiaban.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.base.AppAppLication;
import com.superc.shangjiaban.base.BaseFragment;
import com.superc.shangjiaban.base.Constant;
import com.superc.shangjiaban.bean.HomeIndexBean;
import com.superc.shangjiaban.bean.HttpBean;
import com.superc.shangjiaban.ui.activity.AddAplyActivity;
import com.superc.shangjiaban.ui.activity.WuyeEditActivity;
import com.superc.shangjiaban.utils.ShareUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.superc.shangjiaban.base.Constant.ADDAPPLY;

/**
 * 百姓物业界面
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.home_today)
    TextView mHomeToday;
    @BindView(R.id.home_month_shouyi)
    TextView mHomeMonthShouyi;
    @BindView(R.id.home_all_shouyi)
    TextView mHomeAllShouyi;
    @BindView(R.id.home_tixian)
    TextView mHomeTixian;
    @BindView(R.id.home_tixianjilu)
    TextView mHomeTixianjilu;
    @BindView(R.id.home_weibangding)
    TextView mHomeWeibangding;
    @BindView(R.id.home_gobangding)
    TextView mHomeGobangding;
    @BindView(R.id.home_god_num)
    TextView mHomeGodNum;
    @BindView(R.id.home_xiaoqu_num)
    TextView mHomeXiaoquNum;
    @BindView(R.id.home_dingdan_num)
    TextView mHomeDingdanNum;
    @BindView(R.id.home_huiyuan_num)
    TextView mHomeHuiyuanNum;
    @BindView(R.id.home_app_banben)
    TextView mHomeAppBanben;
    @BindView(R.id.home_app_updateTime)
    TextView mHomeAppUpdateTime;
    @BindView(R.id.home_app_develop)
    TextView mHomeAppDevelop;
    @BindView(R.id.home_app_banquan)
    TextView mHomeAppBanquan;
    @BindView(R.id.home_yinhangka)
    LinearLayout mHomeYinhangka;

    private String uid = "";
    private MainActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void init() {
        uid = (String) ShareUtil.getInstance(this.getActivity()).get("uid", "");
        mActivity = (MainActivity) this.getActivity();

    }

    public void getData() {
        Map<String, String> map = new HashMap<>();
        if(uid==null||uid.equals("")){
            uid = (String) ShareUtil.getInstance(AppAppLication.getmContext()).get("uid", "");
        }
        map.put("uid", uid);
        toGetData(map, Constant.SYSTEM, true, HomeIndexBean.class, new CallNetBack<HomeIndexBean>() {
            @Override
            public void callNetBack(HomeIndexBean data) {
                String code = data.getCode();
//                showToast(data.getInfo());
                if (code.equals("200")) {
                    if(mHomeGodNum!=null) {
                        setMsg(data.getDate());
                    }else {
                        showToast("请稍后重试");
                    }
                }

            }
        });
    }

    public void setMsg(HomeIndexBean.DateBean bean) {
        mHomeGodNum.setText(bean.getCommission());
        mHomeAllShouyi.setText(bean.getCommission());
        mHomeXiaoquNum.setText(bean.getXiaoqu());
        mHomeDingdanNum.setText(bean.getOrder());
        mHomeHuiyuanNum.setText(bean.getUsers());
        mHomeToday.setText(bean.getSale().getToday_sale());
        mHomeMonthShouyi.setText(bean.getSale().getThismonth());

        String status = bean.getStatus();
        if (status.equals("0")) {
            mHomeYinhangka.setVisibility(View.VISIBLE);
        } else {
            mHomeYinhangka.setVisibility(View.INVISIBLE);
        }


    }


    @OnClick({R.id.home_tixian, R.id.home_tixianjilu, R.id.home_gobangding,R.id.home_yinhangka, R.id.home_rlone, R.id.home_rltwo, R.id.home_rlthree, R.id.home_rlfour})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_tixian:
                panduanZhanghu();
                break;
            case R.id.home_tixianjilu:
                mActivity.setSelectPage(3);
                mActivity.setguanjiaWhat(2);
                break;
            case R.id.home_gobangding:
            case R.id.home_yinhangka:
                stActivity(WuyeEditActivity.class, null);
                break;
            case R.id.home_rlone:
            case R.id.home_god_num:
                mActivity.setSelectPage(1);
                mActivity.setUserWhat(1);
                break;
            case R.id.home_rltwo:
            case R.id.home_xiaoqu_num:
                mActivity.setSelectPage(2);
                mActivity.setXiaoquWhat(0);
                break;
            case R.id.home_rlthree:
            case R.id.home_dingdan_num:
                mActivity.setSelectPage(4);
                mActivity.setdingdanWhat(0);
                break;
            case R.id.home_rlfour:
            case R.id.home_huiyuan_num:
                mActivity.setSelectPage(1);
                mActivity.setUserWhat(0);
                break;
        }
    }

    /*判断是否添加了提款账号*/
    public void panduanZhanghu() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("act", "");
        map.put("money", "");
        toGetData(map, ADDAPPLY, false, HttpBean.class, new CallNetBack<HttpBean>() {
            @Override
            public void callNetBack(HttpBean data) {
                int code = data.getCode();
                String info = data.getInfo();
                if (code == 201) {
                    stActivity(WuyeEditActivity.class, null);
                    showToast(info);
                } else if (code == 203) {
                    stActivity(AddAplyActivity.class, null);
                }
            }
        });
    }
}
