package com.superc.shangjiaban.ui;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
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
import com.superc.shangjiaban.bean.UserGodListBean;
import com.superc.shangjiaban.bean.UserListBean;
import com.superc.shangjiaban.bean.UserZhuanZhangBean;
import com.superc.shangjiaban.others.CustomListener;
import com.superc.shangjiaban.others.DialogUtil;
import com.superc.shangjiaban.others.PopUpUtil;
import com.superc.shangjiaban.ui.adapter.GodListAdapter;
import com.superc.shangjiaban.ui.adapter.UserListAdapter;
import com.superc.shangjiaban.ui.adapter.ZhuanZhangAdapter;
import com.superc.shangjiaban.utils.ShareUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.superc.shangjiaban.base.Constant.ACCOUNTSLIST;
import static com.superc.shangjiaban.base.Constant.APPROVAL;
import static com.superc.shangjiaban.base.Constant.DOSENDMSG;
import static com.superc.shangjiaban.base.Constant.GOLDALLOWANCE;
import static com.superc.shangjiaban.base.Constant.PLAYMONEY;
import static com.superc.shangjiaban.base.Constant.USERLIST;
import static com.superc.shangjiaban.base.Otehers.REQUESTCODE_USRE;

/********************************************************************
 @version: 1.0.0
 @description: 用户管理界面--上部选择的不同更换不同的数据给RecycleView
 @author: user
 @time: 2018/3/1 14:41
 @变更历史:
 ********************************************************************/
public class UserFragment extends BaseFragment {


    @BindView(R.id.god_user_list)
    RadioButton mGodUserList;
    @BindView(R.id.god_god_jilu)
    RadioButton mGodGodJilu;
    @BindView(R.id.god_wait_money)
    RadioButton mGodWaitMoney;
    @BindView(R.id.god_rg)
    RadioGroup mGodRg;
    @BindView(R.id.god_search)
    EditText mGodSearch;
    @BindView(R.id.god_rv)
    RecyclerView mGodRv;
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.smartrefresh)
    SmartRefreshLayout mSmartrefresh;
    @BindView(R.id.shop_rb_choseall)
    RadioButton mShopRbChoseall;
    @BindView(R.id.god_rela)
    RelativeLayout mGodRela;
    @BindView(R.id.god_piliang)
    TextView mtvPiliang;

    private RadioButton[] rbs;
    /*用户列表*/
    private UserListAdapter mUserListAdapter;
    private List<UserListBean.UsersBean> mClassList;
    private int user_page = 1;
    /*黄金抵费记录*/
    private GodListAdapter mGodListAdapter;
    private List<UserGodListBean.UsersBean> mGodList;
    private int god_page = 1;
    /*待转账*/
    private ZhuanZhangAdapter mZhuanZhangAdapter;
    private List<UserZhuanZhangBean.DateBean> mZZList;
    private int zz_page = 1;

    private View mView;
    private AlertDialog.Builder mBuilder;
    private AlertDialog mDialog;
    private int isWhat = 0;

    private String[] mString_explain;/*说明集合*/
    private PopUpUtil mPopUpUtil;
    private String uid = "";
    private String role_id = "";
    private boolean isCheck = false;
    private boolean isCheck_godjl = false;

    private String st_time, ed_time, keyword = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_god, container, false);
        ButterKnife.bind(this, view);
        init();
        initOthers();
        return view;
    }

    @Override
    public void init() {
        mPopUpUtil = new PopUpUtil(this.getActivity());
        mImageView.requestFocus();
        mTvSearch.setOnClickListener(this);
        mImageView.setOnClickListener(this);
        mShopRbChoseall.setOnClickListener(this);
        mtvPiliang.setOnClickListener(this);
        mClassList = new ArrayList<>();
        mGodList = new ArrayList<>();
        mZZList = new ArrayList<>();
        mString_explain = new String[]{};


        rbs = new RadioButton[]{mGodUserList, mGodGodJilu, mGodWaitMoney};
        mGodRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.god_user_list://用户列表
                        userlist();
                        break;
                    case R.id.god_god_jilu://黄金抵费记录
//                        mGodRela.setVisibility(View.GONE);
                        huangjin();
                        break;
                    case R.id.god_wait_money://待转账
                        mGodRela.setVisibility(View.GONE);
                        changeColor(2);
                        mSmartrefresh.setEnableLoadmore(true);
                        mGodRv.setAdapter(mZhuanZhangAdapter);
                        getZhuanzhang();
                        resetGodCanshu();
                        break;
                }
            }
        });
        mSmartrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                switch (isWhat) {
                    case 0:
                        user_page = 1;
                        mSmartrefresh.setEnableLoadmore(true);
                        getUserList(mGodSearch.getText().toString());
                        break;
                    case 1:
                        god_page = 1;
                        mSmartrefresh.setEnableLoadmore(true);
                        getGodList(null, null, null, null);
                        break;
                    case 2:
                        zz_page = 1;
                        mSmartrefresh.setEnableLoadmore(true);
                        getZhuanzhang();
                        break;
                }
            }
        });
        mSmartrefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                switch (isWhat) {
                    case 0:
                        getUserList(mGodSearch.getText().toString());
                        break;
                    case 1:
                        getGodList(null, null, null, null);
                        break;
                    case 2:
                        getZhuanzhang();
                        break;
                }
            }
        });
        mGodSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isWhat == 0 || isWhat == 2) {
                    if (charSequence.length() > 0) {
                        mTvSearch.setVisibility(View.VISIBLE);
                    } else {
                        mTvSearch.setVisibility(View.GONE);
                    }
                }
                if (charSequence.length() == 0) {
                    if (isWhat == 0 || isWhat == 2) {
                        mSmartrefresh.autoRefresh();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    /*MainActivity跳转时用户列表的刷新*/
    public void refreshUserlist() {
        mGodUserList.setChecked(true);
    }

    public void userlist() {
        changeColor(0);
        user_page = 1;
        mSmartrefresh.setEnableLoadmore(true);
        mGodRv.setAdapter(mUserListAdapter);
        getUserList(null);
        resetGodCanshu();
    }
    /*MainActivity跳转时黄金抵费记录的刷新*/
    public void refreshHuangjin() {
        mGodGodJilu.setChecked(true);
    }

    public void huangjin() {
//        mGodGodJilu.setChecked(true);
        changeColor(1);
        god_page = 1;
        mSmartrefresh.setEnableLoadmore(true);
        mGodRv.setAdapter(mGodListAdapter);
        getGodList(null, null, null, null);
    }

    public void initOthers() {
        uid = (String) ShareUtil.getInstance(this.getActivity()).get("uid", "");
        role_id = (String) ShareUtil.getInstance(this.getActivity()).get("role_id", "");
        mUserListAdapter = new UserListAdapter(this.getActivity(), mClassList);
        mGodListAdapter = new GodListAdapter(this.getActivity(), mGodList, role_id);
        mZhuanZhangAdapter = new ZhuanZhangAdapter(this.getActivity(), mZZList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mGodRv.setLayoutManager(layoutManager);
        mGodRv.setAdapter(mUserListAdapter);
        getUserList(mGodSearch.getText().toString());//首先是用户列表
        mUserListAdapter.setOnViewClickListener(new UserListAdapter.OnViewClickListener() {
            @Override
            public void OnViewClickListener(int view_id, int position) {
                showRemindDialog(position, true);
            }
        });
        mUserListAdapter.setOnItemClickListener(new UserListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                UserListBean.UsersBean usersBean = mClassList.get(position);
                boolean c = usersBean.isC();
                usersBean.setC(!c);
                mUserListAdapter.notifyDataSetChanged();
                boolean isShow = false;
                for (UserListBean.UsersBean bean : mClassList) {
                    if (bean.isC()) {/*用来判断是否展示出下方的全选和批量发送*/
                        isShow = true;
                    }
                }
                if (isShow) {
                    mGodRela.setVisibility(View.VISIBLE);
                    mtvPiliang.setText("批量发送");
                } else {
                    mGodRela.setVisibility(View.GONE);
                }
            }
        });

        mGodListAdapter.setOnViewClickListener(new GodListAdapter.OnViewClickListener() {
            @Override
            public void OnViewClickListener(int view_id, final int position) {
                DialogUtil.getmInstance(UserFragment.this.getActivity()).showRemindDialog().setRemindTvContent("确定通过?", "否", "是").setOnTvClickListener(new DialogUtil.OnTvClickListener() {
                    @Override
                    public void OnTvClickListener(int view_id) {
                        switch (view_id) {
                            case R.id.dialog_remind_sure:
                                UserGodListBean.UsersBean bean = mGodList.get(position);
//                                if (bean.getState().equals("1")) {
//                                    bean.setState("2");
//                                }
//                                mGodListAdapter.setClassList(mGodList);
                                tongguoHuanji(bean.getId());

                                break;
                        }
                    }
                });
            }
        });
        mGodListAdapter.setOnItemClickListener(new GodListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                UserGodListBean.UsersBean bean = mGodList.get(position);
                boolean c = bean.isC();
                bean.setC(!c);
                mGodListAdapter.notifyDataSetChanged();
                boolean isShow = false;
                for (UserGodListBean.UsersBean ubean : mGodList) {
                    String state = ubean.getState();
                    if (!state.equals("2")) {
                        if (ubean.isC()) {/*用来判断是否展示出下方的全选和批量通过*/
                            isShow = true;
                        }
                    }
                }
                if (isShow) {
                    mGodRela.setVisibility(View.VISIBLE);
                    mtvPiliang.setText("批量通过");
                } else {
                    mGodRela.setVisibility(View.GONE);
                }
            }
        });
        mZhuanZhangAdapter.setCustomListener(new CustomListener() {
            @Override
            public void CustomeListener(int view_id, int postion) {
                final UserZhuanZhangBean.DateBean dateBean = mZZList.get(postion);

                DialogUtil.getmInstance(UserFragment.this.getActivity()).showRemindDialog().setRemindTvContent("确定已打款?", "否", "是").setOnTvClickListener(new DialogUtil.OnTvClickListener() {
                    @Override
                    public void OnTvClickListener(int view_id) {
                        switch (view_id) {
                            case R.id.dialog_remind_sure:
                                tongguoZhuanZhang(dateBean.getId());
                                break;
                        }
                    }
                });

            }
        });

        initDialog();
    }

    public void initDialog() {
        mBuilder = new AlertDialog.Builder(this.getActivity());
        mView = LayoutInflater.from(this.getActivity()).inflate(R.layout.dialog_user_send, null);
        mBuilder.setView(mView);
        mDialog = mBuilder.create();
    }


    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                if (isWhat == 0) {
                    mSmartrefresh.autoRefresh();
                } else if (isWhat == 2) {
                    showToast("开始搜索--待转账");
                }
                break;
            case R.id.imageView:
                mPopUpUtil.showPop(mString_explain, mImageView);
                break;
            case R.id.god_search:
                if (isWhat == 1) {
                    Intent intent = new Intent(this.getActivity(), SearchActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("st_time", st_time);
                    bundle.putString("ed_time", ed_time);
                    bundle.putString("keyword", keyword);
                    intent.putExtras(bundle);
                    ((MainActivity) this.getActivity()).startActivityForResult(intent, REQUESTCODE_USRE);
                }
                break;
            case R.id.shop_rb_choseall:
                if (isWhat == 0) {
                    if (isCheck) {
                        mShopRbChoseall.setChecked(false);
                        mShopRbChoseall.setText("全选");
                        mGodRela.setVisibility(View.GONE);
                    } else {
                        mShopRbChoseall.setChecked(true);
                        mShopRbChoseall.setText("取消全选");
                    }
                    isCheck = !isCheck;
                    for (UserListBean.UsersBean bean : mClassList) {
                        bean.setC(mShopRbChoseall.isChecked());
                    }
                    mUserListAdapter.notifyDataSetChanged();
                } else {
                    if (isCheck_godjl) {
                        mShopRbChoseall.setChecked(false);
                        mShopRbChoseall.setText("全选");
                        mGodRela.setVisibility(View.GONE);
                    } else {
                        mShopRbChoseall.setChecked(true);
                        mShopRbChoseall.setText("取消全选");
                    }
                    isCheck_godjl = !isCheck_godjl;
                    for (UserGodListBean.UsersBean ubean : mGodList) {
                        String state = ubean.getState();
                        if (!state.equals("2")) {
                            ubean.setC(mShopRbChoseall.isChecked());
                        }
                    }
                    mGodListAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.god_piliang:
                if (isWhat == 0) {/*进行批量发送通知(判断mClassList的isC是否为true)*/
                    showRemindDialog(-1, false);
                } else if (isWhat == 1) {/*进行批量发送黄金抵费记录(判断mGodList的isC是否为true)*/
                    showToast("批量通过抵费记录");
                    DialogUtil.getmInstance(UserFragment.this.getActivity()).showRemindDialog().setRemindTvContent("确定批量通过?", "否", "是").setOnTvClickListener(new DialogUtil.OnTvClickListener() {
                        @Override
                        public void OnTvClickListener(int view_id) {
                            switch (view_id) {
                                case R.id.dialog_remind_sure:
                                    String result = "";
                                    for (UserGodListBean.UsersBean bean : mGodList) {
                                        if (bean.isC()) {
                                            result += (bean.getId() + ",");
                                        }
                                    }
                                    tongguoHuanji(result.substring(0, result.length() - 1));
                                    break;
                            }
                        }
                    });
                }
                break;
        }
    }

    /*单个转账汇款操作*/
    public void tongguoZhuanZhang(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("arr", id);
        map.put("role_id", role_id);
        toGetData(map, PLAYMONEY, false, HttpBean.class, new CallNetBack<HttpBean>() {
            @Override
            public void callNetBack(HttpBean data) {
                if (data.getCode() == 200) {
                    zz_page = 1;
                    getZhuanzhang();
                }
                showToast(data.getInfo());
            }
        });


    }

    /*多个/单个 黄金抵费记录通过*/
    public void tongguoHuanji(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("arr", id);
        map.put("role_id", role_id);
        toGetData(map, APPROVAL, false, HttpBean.class, new CallNetBack<HttpBean>() {
            @Override
            public void callNetBack(HttpBean data) {
                if (data.getCode() == 200) {
                    god_page = 1;
                    getGodList(null, null, null, null);
                }
                showToast("批量或单个审批黄金抵费记录");
            }
        });


    }

    public void showRemindDialog(final int pos, final boolean is_one) {
        mDialog.show();
        final RadioButton rb_one = mView.findViewById(R.id.dialog_user_one);
        final RadioButton rb_all = mView.findViewById(R.id.dialog_user_all);
        TextView tv_name = mView.findViewById(R.id.dialog_user_name);
        TextView tv_send = mView.findViewById(R.id.dialog_user_send);
        TextView tvfour = mView.findViewById(R.id.textView4);
        tvfour.requestFocus();
        final EditText edt_content = mView.findViewById(R.id.dialog_user_content);
        if (is_one) {
            tv_name.setText(mClassList.get(pos).getNickname());
        } else {
            String names = "";
            for (UserListBean.UsersBean bean : mClassList) {
                if (bean.isC()) {
                    names += (bean.getNickname() + "、");
                }
            }
            tv_name.setText(names.substring(0, names.length() - 1));
        }
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edt_content.getText().toString())) {
                    showToast("发送内容不能为空");
                    return;
                }
                if (rb_one.isChecked()) {
                    if (is_one) {/*单个发送*/
                        toSendMsg("1", mClassList.get(pos).getUserid(), edt_content.getText().toString());
                    } else {/*批量发送*/
                        String ids = "";
                        for (UserListBean.UsersBean bean : mClassList) {
                            ids += (bean.getUserid() + ",");
                        }
                        toSendMsg("1", ids.substring(0, ids.length() - 1), edt_content.getText().toString());
                    }
                } else if (rb_all.isChecked()) {
                    String ids = "";
                    for (UserListBean.UsersBean bean : mClassList) {
                        ids += (bean.getUserid() + ",");
                    }
                    toSendMsg("2", ids.substring(0, ids.length() - 1), edt_content.getText().toString());
                }
            }
        });
    }

    /*站内信发送*/
    public void toSendMsg(String type, String ids, String content) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("text", content);
        map.put("type", type);
        if(!type.equals("2")) {
            map.put("user", ids);
        }
        map.put("biaoshi", "app");
        toGetData(map, DOSENDMSG, false, HttpBean.class, new CallNetBack<HttpBean>() {
            @Override
            public void callNetBack(HttpBean data) {
                showToast(data.getInfo());
                mDialog.dismiss();
            }
        });
    }


    /*选择用户列表时*/
    public void getUserList(String keyword) {
        mPopUpUtil.setShow(false);
        mString_explain = UserFragment.this.getActivity().getResources().getStringArray(R.array.user_userlist_note);
        if (isWhat != 0) {
            mGodSearch.setText("");
            mGodSearch.setHint("请输入昵称或手机号搜索");
        }
        isWhat = 0;
        if (user_page == 1) {
            mShopRbChoseall.setChecked(false);
            mShopRbChoseall.setText("全选");
            mGodRela.setVisibility(View.GONE);
        }

        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("page", user_page + "");
        map.put("keyword", keyword == null ? "" : keyword);
        map.put("size", "15");
        toGetData(map, USERLIST, false, UserListBean.class, new CallNetBack<UserListBean>() {
            @Override
            public void callNetBack(UserListBean data) {
                String code = data.getCode();
                String info = data.getInfo();
                showToast(info);
                if (code.equals("200")) {
                    mSmartrefresh.finishRefresh();
                    mSmartrefresh.finishLoadmore();
                    if (user_page == 1) {
                        mClassList.clear();
                        for (UserListBean.UsersBean bean : data.getUsers()) {
                            bean.setC(false);
                        }
                        mClassList.addAll(data.getUsers());
                        if (data.getUsers().size() < 15) {
                            mSmartrefresh.setEnableLoadmore(false);
                        }
                    } else {
                        for (UserListBean.UsersBean usersBean : data.getUsers()) {
                            usersBean.setC(false);
                            mClassList.add(usersBean);
                        }
                        if(data.getUsers().size()!=0){
                            isCheck=false;
                            mShopRbChoseall.setChecked(false);
                            mShopRbChoseall.setText("全选");
                        }

                        if (data.getUsers().size() < 15) {
                            mSmartrefresh.setEnableLoadmore(false);
                        }
                    }
                    mUserListAdapter.setClassList(mClassList);
                    mUserListAdapter.notifyDataSetChanged();
                    user_page++;
                }
            }
        });

        mGodSearch.setFocusable(true);
        mGodSearch.setFocusableInTouchMode(true);
    }

    /*设置黄金抵费的参数*/
    public void setGodCanshu(String st_time, String ed_time, String keyword) {
        this.st_time = st_time;
        this.ed_time = ed_time;
        this.keyword = keyword;
        String reslult = st_time + "、" + ed_time + "、" + keyword;
        if (reslult.startsWith("、")) {
            mGodSearch.setText(reslult.length() > 35 ? reslult.substring(1, 35) + "..." : reslult.substring(1, reslult.length()));
        } else {
            mGodSearch.setText(reslult.length() > 35 ? reslult.substring(0, 35) + "..." : reslult);
        }
        getGodList(keyword, st_time, ed_time, "1");
    }

    /*重置黄金抵费参数*/
    public void resetGodCanshu() {
        this.st_time = "";
        this.ed_time = "";
        this.keyword = "";
        mGodSearch.setText("");
    }

    /*选择黄金抵费记录时*/
    public void getGodList(String keyword, String st_time, String ed_time, final String page) {
        mPopUpUtil.setShow(false);
        mString_explain = UserFragment.this.getActivity().getResources().getStringArray(R.array.user_godlist_note);
        if (god_page == 1) {
            mShopRbChoseall.setChecked(false);
            mShopRbChoseall.setText("全选");
            mGodRela.setVisibility(View.GONE);
        }
        isWhat = 1;
        mGodSearch.setHint("搜索记录");

        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("nickname", keyword == null ? "" : keyword);
        map.put("start_time", st_time == null ? "" : st_time);
        map.put("end_time", ed_time == null ? "" : ed_time);
        if (page == null) {/*不是通过搜索过来的*/
            map.put("p", god_page + "");
        } else {/*是通过搜索过来的*/
            map.put("p", "1");
        }
        map.put("size", "15");

        toGetData(map, GOLDALLOWANCE, false, UserGodListBean.class, new CallNetBack<UserGodListBean>() {
            @Override
            public void callNetBack(UserGodListBean data) {
                mSmartrefresh.finishRefresh();
                mSmartrefresh.finishLoadmore();
                String code = data.getCode();
                String info = data.getInfo();
                if (code.equals("200")) {
                    if (god_page == 1) {
                        mGodList.clear();
                        for (UserGodListBean.UsersBean usersBean : data.getUsers()) {
                            usersBean.setC(false);
                        }
                        mGodList.addAll(data.getUsers());
                        if (data.getUsers().size() < 15) {
                            mSmartrefresh.setEnableLoadmore(false);
                        }
                    } else {
                        if (page == null) {/*不是通过搜索过来的*/
                            for (UserGodListBean.UsersBean usersBean : data.getUsers()) {
                                usersBean.setC(false);
                                mGodList.add(usersBean);
                            }
                            if (data.getUsers().size() < 15) {
                                mSmartrefresh.setEnableLoadmore(false);
                            }
                            if (data.getUsers().size() != 0) {
                                for (UserGodListBean.UsersBean usersBean : data.getUsers()) {
                                    if (!usersBean.equals("2")) {
                                        isCheck_godjl=false;
                                        mShopRbChoseall.setChecked(false);
                                        mShopRbChoseall.setText("全选");
                                    }
                                }
                            }
                        } else {/*是通过搜索过来的*/
                            mGodList.clear();
                            for (UserGodListBean.UsersBean usersBean : data.getUsers()) {
                                usersBean.setC(false);
                            }
                            mGodList.addAll(data.getUsers());
                            if (data.getUsers().size() < 15) {
                                mSmartrefresh.setEnableLoadmore(false);
                            }
                        }
                    }
                    god_page++;
                } else if (code.equals("201")) {
                    mGodList.clear();
                }
                showToast(info);
                mGodListAdapter.notifyDataSetChanged();
            }
        });
        mGodSearch.setFocusable(false);
        mGodSearch.setFocusableInTouchMode(false);
        mGodSearch.setOnClickListener(this);
    }

    /*待转账*/
    public void getZhuanzhang() {
        mPopUpUtil.setShow(false);
        mString_explain = UserFragment.this.getActivity().getResources().getStringArray(R.array.user_waitzhuanzhang_note);
        if (isWhat != 2) {
            mGodSearch.setText("");
            mGodSearch.setHint("请输入关键字搜索");
        }
        isWhat = 2;
        mGodSearch.setFocusable(true);
        mGodSearch.setFocusableInTouchMode(true);

        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("keywords", mGodSearch.getText().toString());
        map.put("page", zz_page + "");
        map.put("size", "15");
        toGetData(map, ACCOUNTSLIST, false, UserZhuanZhangBean.class, new CallNetBack<UserZhuanZhangBean>() {
            @Override
            public void callNetBack(UserZhuanZhangBean data) {
                String code = data.getCode();
                String info = data.getInfo();
                showToast(info);
                mSmartrefresh.finishRefresh();
                mSmartrefresh.finishLoadmore();
                if (code.equals("200")) {
                    if (zz_page == 1) {
                        mZZList.clear();
                        mZZList.addAll(data.getDate());
                        if (data.getDate().size() < 15) {
                            mSmartrefresh.setEnableLoadmore(false);
                        }
                    } else {
                        for (UserZhuanZhangBean.DateBean bean : data.getDate()) {
                            mZZList.add(bean);
                        }
                        if (data.getDate().size() < 15) {
                            mSmartrefresh.setEnableLoadmore(false);
                        }
                    }
                    mZhuanZhangAdapter.notifyDataSetChanged();
                    zz_page++;
                } else if (code.equals("201")) {
                    mZZList.clear();
                    mZhuanZhangAdapter.notifyDataSetChanged();
                } else {
                    mSmartrefresh.finishRefresh();
                    mSmartrefresh.finishLoadmore();
                }

            }
        });
    }

    public void changeColor(int pos) {
        for (int i = 0; i < rbs.length; i++) {
            RadioButton rb = rbs[i];
            if (i == pos) {
                rb.setTextColor(UserFragment.this.getResources().getColor(R.color.black));
            } else {
                rb.setTextColor(UserFragment.this.getResources().getColor(R.color.gray_nine));
            }
        }
    }

}
