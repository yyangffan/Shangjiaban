package com.superc.shangjiaban.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.superc.shangjiaban.bean.ProPertyListBean;
import com.superc.shangjiaban.bean.PropertyYjBean;
import com.superc.shangjiaban.others.CustomListener;
import com.superc.shangjiaban.others.DialogUtil;
import com.superc.shangjiaban.others.PopUpUtil;
import com.superc.shangjiaban.ui.activity.AddAplyActivity;
import com.superc.shangjiaban.ui.activity.SearchTxJLActivity;
import com.superc.shangjiaban.ui.activity.WuyeEditActivity;
import com.superc.shangjiaban.ui.adapter.WuYeApplyAdapter;
import com.superc.shangjiaban.ui.adapter.WuYeJiLuAdapter;
import com.superc.shangjiaban.ui.adapter.WuYeUserListAdapter;
import com.superc.shangjiaban.utils.ShareUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.superc.shangjiaban.base.Constant.ADDAPPLY;
import static com.superc.shangjiaban.base.Constant.APPROVALCOMMISSION;
import static com.superc.shangjiaban.base.Constant.MAKEMONEY;
import static com.superc.shangjiaban.base.Constant.PROPERTYLIST;
import static com.superc.shangjiaban.base.Constant.REMITTANCE;
import static com.superc.shangjiaban.base.Constant.WITHDRAWALS;
import static com.superc.shangjiaban.base.Otehers.REQUESTCODE_YJ;

/**
 * 物业管理界面
 */
public class PropertyFragment extends BaseFragment {


    @BindView(R.id.property_rbuser_list)
    RadioButton mPropertyRbuserList;
    @BindView(R.id.property_yongjin_shenqing)
    RadioButton mPropertyYongjinShenqing;
    @BindView(R.id.property_yongjin_jilu)
    RadioButton mPropertyYongjinJilu;
    @BindView(R.id.property_rg)
    RadioGroup mPropertyRg;
    @BindView(R.id.property_user_list)
    EditText mPropertyUserList;
    @BindView(R.id.property_yongjinsq)
    TextView mPropertyYongjinsq;
    @BindView(R.id.property_shuoming)
    ImageView mPropertyShuoming;
    @BindView(R.id.property_rc)
    RecyclerView mPropertyRc;
    @BindView(R.id.property_tv_search)
    TextView mPropertyTvSearch;
    @BindView(R.id.rela)
    RelativeLayout mRela;
    @BindView(R.id.smartrefresh)
    SmartRefreshLayout mSmartRefreshLayout;
    private RadioButton[] rbs;

    /*物业用户列表*/
    private WuYeUserListAdapter mWuYeUserListAdapter;
    private List<ProPertyListBean.DateBean> mUserList;
    private int userlist_page = 1;
    /*佣金提现申请*/
    private WuYeApplyAdapter mWuYeApplyAdapter;
    private List<PropertyYjBean.DateBean> mYongJinSq;
    private int apply_page = 1;
    /*佣金提现记录*/
    private WuYeJiLuAdapter mWuYeJiLuAdapter;
    private List<PropertyYjBean.DateBean> mListJilu;
    public int jilu_page = 1;

    private PopUpUtil mPopUpUtil;
    private String[] mString_explain;


    private int isWhat = 0;
    private String uid = "";
    private String role_id = "";
    private String mKeyword = "";
    private String mSt_time = "";
    private String mEd_time = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_property, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void init() {
        uid = (String) ShareUtil.getInstance(this.getActivity()).get("uid", "");
        role_id = (String) ShareUtil.getInstance(this.getActivity()).get("role_id", "");
        mPopUpUtil = new PopUpUtil(this.getActivity());
        mString_explain = new String[]{};
        rbs = new RadioButton[]{mPropertyRbuserList, mPropertyYongjinShenqing, mPropertyYongjinJilu};
        mPropertyShuoming.setOnClickListener(this);
        mRela.setOnClickListener(this);
        mPropertyUserList.setOnClickListener(this);
        mPropertyTvSearch.setOnClickListener(this);

        mUserList = new ArrayList<>();
        mYongJinSq = new ArrayList<>();
        mListJilu = new ArrayList<>();
        mWuYeUserListAdapter = new WuYeUserListAdapter(this.getActivity(), mUserList);
        mWuYeApplyAdapter = new WuYeApplyAdapter(this.getActivity(), mYongJinSq);
        mWuYeJiLuAdapter = new WuYeJiLuAdapter(this.getActivity(), mListJilu);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mPropertyRc.setLayoutManager(layoutManager);
        mPropertyRc.setAdapter(mWuYeUserListAdapter);

        getUserList();//首先获取物业用户列表数据
        mPropertyRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int view_id) {
                switch (view_id) {
                    case R.id.property_rbuser_list:
                        changeColor(0);
                        mPropertyUserList.setVisibility(View.VISIBLE);
                        mRela.setVisibility(View.INVISIBLE);
                        mPropertyRc.setAdapter(mWuYeUserListAdapter);
                        getUserList();
                        resetMsg();
                        break;
                    case R.id.property_yongjin_shenqing:
                        changeColor(1);
                        mPropertyUserList.setVisibility(View.INVISIBLE);
                        mRela.setVisibility(View.VISIBLE);
                        mPropertyRc.setAdapter(mWuYeApplyAdapter);
                        getYjSq();
                        resetMsg();
                        break;
                    case R.id.property_yongjin_jilu:
                        yongjinJIlu();
                        break;
                }

            }
        });
        mPropertyUserList.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isWhat != 2) {
                    if (charSequence.length() > 0) {
                        mPropertyTvSearch.setVisibility(View.VISIBLE);
                    } else {
                        mPropertyTvSearch.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mWuYeUserListAdapter.setCustomListener(new CustomListener() {
            @Override
            public void CustomeListener(int view_id, int postion) {
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("data", mUserList.get(postion));
                stActivity(WuyeEditActivity.class, null);
            }
        });
        mWuYeApplyAdapter.setCustomListener(new CustomListener() {
            @Override
            public void CustomeListener(int view_id, final int postion) {
                DialogUtil.getmInstance(PropertyFragment.this.getActivity()).showRemindDialog().setRemindTvContent("确定通过?", "否", "是").setOnTvClickListener(new DialogUtil.OnTvClickListener() {
                    @Override
                    public void OnTvClickListener(int view_id) {
                        switch (view_id) {
                            case R.id.dialog_remind_sure:
//                                PropertyYjBean.DateBean exampleClass = mYongJinSq.get(postion);
//                                mWuYeApplyAdapter.notifyDataSetChanged();
                                toEnter(mYongJinSq.get(postion).getId() + "");
                                break;
                        }
                    }
                });
            }
        });
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                switch (isWhat) {
                    case 0:
                        userlist_page = 1;
                        mSmartRefreshLayout.setEnableLoadmore(true);
                        getUserList();
                        break;
                    case 1:
                        apply_page = 1;
                        mSmartRefreshLayout.setEnableLoadmore(true);
                        getYjSq();
                        break;
                    case 2:
                        jilu_page = 1;
                        mSmartRefreshLayout.setEnableLoadmore(true);
                        getYjJl(mKeyword, mSt_time, mEd_time);
                        break;
                }
            }
        });
        mSmartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                switch (isWhat) {
                    case 0:
                        getUserList();
                        break;
                    case 1:
                        getYjSq();
                        break;
                    case 2:
                        getYjJl(mKeyword, mSt_time, mEd_time);
                        break;
                }
            }
        });
        mPropertyUserList.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    if (isWhat == 0) {
                        mSmartRefreshLayout.autoRefresh();
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mWuYeJiLuAdapter.setCustomListener(new CustomListener() {
            @Override
            public void CustomeListener(int view_id, final int postion) {
                DialogUtil.getmInstance(PropertyFragment.this.getActivity()).showRemindDialog().setRemindTvContent("确定已打款?", "否", "是").setOnTvClickListener(new DialogUtil.OnTvClickListener() {
                    @Override
                    public void OnTvClickListener(int view_id) {
                        switch (view_id) {
                            case R.id.dialog_remind_sure:
//                                PropertyYjBean.DateBean exampleClass = mYongJinSq.get(postion);
//                                mWuYeApplyAdapter.notifyDataSetChanged();
                                toEnterJilu(mListJilu.get(postion).getId() + "");
                                break;
                        }
                    }
                });

            }
        });
    }

    /*MainActivity跳转时佣金纪录的刷新*/
    public void refreshYongjinjilu() {
        mPropertyYongjinJilu.setChecked(true);
    }

    /*佣金提现记录变化*/
    public void yongjinJIlu() {
        mPropertyTvSearch.setVisibility(View.GONE);
        changeColor(2);
        mPropertyUserList.setVisibility(View.VISIBLE);
        mRela.setVisibility(View.INVISIBLE);
        mPropertyRc.setAdapter(mWuYeJiLuAdapter);
        getYjJl(mKeyword, mSt_time, mEd_time);
    }

    /*通过佣金提现申请*/
    public void toEnter(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("arr", id);
        map.put("role_id", role_id);
        toGetData(map, APPROVALCOMMISSION, false, HttpBean.class, new CallNetBack<HttpBean>() {
            @Override
            public void callNetBack(HttpBean data) {
                if (data.getCode() == 200) {
                    apply_page = 1;
                    getYjSq();
                }
                showToast(data.getInfo());
            }
        });

    }


    /*佣金提现记录已打款*/
    public void toEnterJilu(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("arr", id);
        map.put("role_id", role_id);
        toGetData(map, MAKEMONEY, false, HttpBean.class, new CallNetBack<HttpBean>() {
            @Override
            public void callNetBack(HttpBean data) {
                if (data.getCode() == 200) {
                    jilu_page = 1;
                    getYjJl(mKeyword, mSt_time, mEd_time);
                }
                showToast(data.getInfo());
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.property_shuoming:
                mPopUpUtil.showPop(mString_explain, mPropertyShuoming);
                break;
            case R.id.rela://添加申请
                panduanZhanghu();
                break;
            case R.id.property_user_list:
                if (isWhat == 0) {

                } else if (isWhat == 2) {
                    Intent intent = new Intent(this.getActivity(), SearchTxJLActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("st_time", mSt_time);
                    bundle.putString("ed_time", mEd_time);
                    bundle.putString("keyword", mKeyword);
                    intent.putExtras(bundle);
                    ((MainActivity) this.getActivity()).startActivityForResult(intent, REQUESTCODE_YJ);
                }
                break;
            case R.id.property_tv_search:
                if (isWhat == 0) {
                    userlist_page = 1;
                    getUserList();
                }
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

    /*获取物业用户列表*/
    public void getUserList() {
        mPopUpUtil.setShow(false);
        mString_explain = this.getActivity().getResources().getStringArray(R.array.property_userlist);
        if (isWhat != 0) {
            mPropertyUserList.setText("");
            mPropertyUserList.setHint("请输入关键字搜索");
        }
        isWhat = 0;
        mPropertyUserList.setFocusable(true);
        mPropertyUserList.setFocusableInTouchMode(true);

        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("keywords", mPropertyUserList.getText().toString());
        map.put("page", userlist_page + "");
        map.put("size", "15");
        toGetData(map, PROPERTYLIST, false, ProPertyListBean.class, new CallNetBack<ProPertyListBean>() {
            @Override
            public void callNetBack(final ProPertyListBean data) {
                String code = data.getCode();

                if (code.equals("200")) {
                    mSmartRefreshLayout.finishRefresh();
                    mSmartRefreshLayout.finishLoadmore();
                    PropertyFragment.this.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (userlist_page == 1) {
                                mUserList.clear();
                                mUserList.addAll(data.getDate());
                                if (data.getDate().size() < 15) {
                                    mSmartRefreshLayout.setEnableLoadmore(false);
                                }
                            } else {
                                for (ProPertyListBean.DateBean bean : data.getDate()) {
                                    mUserList.add(bean);
                                }
                                if (data.getDate().size() < 15) {
                                    mSmartRefreshLayout.setEnableLoadmore(false);
                                }
                            }
                            mWuYeUserListAdapter.notifyDataSetChanged();
                            userlist_page++;
                        }
                    });
                } else {
                    PropertyFragment.this.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mUserList.clear();
                            mWuYeUserListAdapter.notifyDataSetChanged();
                        }
                    });
                }
                showToast(data.getInfo());
            }
        });
    }

    /*佣金提现申请*/
    public void getYjSq() {
        isWhat = 1;
        mPopUpUtil.setShow(false);
        mString_explain = this.getActivity().getResources().getStringArray(R.array.property_tixianshenqing);
        mPropertyTvSearch.setVisibility(View.INVISIBLE);

        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("page", apply_page + "");
        map.put("size", "15");

        toGetData(map, WITHDRAWALS, false, PropertyYjBean.class, new CallNetBack<PropertyYjBean>() {
            @Override
            public void callNetBack(PropertyYjBean data) {
                int code = data.getCode();
                if (code == 200) {
                    mSmartRefreshLayout.finishRefresh();
                    mSmartRefreshLayout.finishLoadmore();
                    if (apply_page == 1) {
                        mYongJinSq.clear();
                        for (PropertyYjBean.DateBean bean : data.getDate()) {
                            String state = bean.getState();
                            if (state.equals("0")) {
                                mYongJinSq.add(bean);
                            }
                        }
                        if (data.getDate().size() < 15) {
                            mSmartRefreshLayout.setEnableLoadmore(false);
                        }
                    } else {
                        for (PropertyYjBean.DateBean bean : data.getDate()) {
                            String state = bean.getState();
                            if (state.equals("0")) {
                                mYongJinSq.add(bean);
                            }
                        }
                        if (data.getDate().size() < 15) {
                            mSmartRefreshLayout.setEnableLoadmore(false);
                        }
                    }
                    mWuYeApplyAdapter.notifyDataSetChanged();
                    apply_page++;
                } else {
                    mYongJinSq.clear();
                    mWuYeApplyAdapter.notifyDataSetChanged();
                }
                showToast(data.getInfo());
            }
        });
    }

    /*佣金提现记录*/
    public void getYjJl(String keyword, String st_time, String end_time) {
        isWhat = 2;
        mPopUpUtil.setShow(false);
        mString_explain = this.getActivity().getResources().getStringArray(R.array.property_tixianjillu);
//        mPropertyUserList.setText("");
        mPropertyUserList.setHint("搜索提现记录");
        mPropertyUserList.setFocusable(false);
        mPropertyUserList.setFocusableInTouchMode(false);

        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("page", jilu_page + "");
        map.put("size", "15");
        map.put("keyword", keyword);
        map.put("start_time", st_time);
        map.put("end_time", end_time);

        toGetData(map, REMITTANCE, false, PropertyYjBean.class, new CallNetBack<PropertyYjBean>() {
            @Override
            public void callNetBack(final PropertyYjBean data) {
                int code = data.getCode();
                if (code == 200) {
                    mSmartRefreshLayout.finishRefresh();
                    mSmartRefreshLayout.finishLoadmore();
                    PropertyFragment.this.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (jilu_page == 1) {
                                mListJilu.clear();
                                for (PropertyYjBean.DateBean bean : data.getDate()) {
                                    String state = bean.getState();
                                    if (state.equals("1")) {
                                        mListJilu.add(bean);
                                    }
                                }
                                if (data.getDate().size() < 15) {
                                    mSmartRefreshLayout.setEnableLoadmore(false);
                                }
                            } else {
                                for (PropertyYjBean.DateBean bean : data.getDate()) {
                                    String state = bean.getState();
                                    if (state.equals("1")) {
                                        mListJilu.add(bean);
                                    }
                                }
                                if (data.getDate().size() < 15) {
                                    mSmartRefreshLayout.setEnableLoadmore(false);
                                }
                            }
                            mWuYeJiLuAdapter.notifyDataSetChanged();
                            jilu_page++;
                        }
                    });
                } else {
                    PropertyFragment.this.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mYongJinSq.clear();
                            mWuYeJiLuAdapter.notifyDataSetChanged();
                        }
                    });
                }
                showToast(data.getInfo());
            }
        });
    }

    public void setMsg(String keyword, String st_time, String end_time) {
        this.mKeyword = keyword;
        this.mSt_time = st_time;
        this.mEd_time = end_time;
        String reslult = st_time + "、" + end_time + "、" + keyword;
        if (reslult.startsWith("、")) {
            mPropertyUserList.setText(reslult.length() > 35 ? reslult.substring(1, 35) + "..." : reslult.substring(1, reslult.length()));
        } else {
            mPropertyUserList.setText(reslult.length() > 35 ? reslult.substring(0, 35) + "..." : reslult);
        }
    }

    /*重置佣金记录搜索条件*/
    public void resetMsg() {
        this.mKeyword = "";
        this.mSt_time = "";
        this.mEd_time = "";
        mPropertyUserList.setText("");
    }

    public void changeColor(int pos) {
        for (int i = 0; i < rbs.length; i++) {
            RadioButton rb = rbs[i];
            if (i == pos) {
                rb.setTextColor(PropertyFragment.this.getResources().getColor(R.color.black));
            } else {
                rb.setTextColor(PropertyFragment.this.getResources().getColor(R.color.gray_nine));
            }
        }
    }

    public void refresh() {
//        if (isWhat == 1) {
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.autoRefresh();
        }
//        }
    }


}
