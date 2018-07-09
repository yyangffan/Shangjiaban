package com.superc.shangjiaban.ui;


import android.app.AlertDialog;
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
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.superc.shangjiaban.R;
import com.superc.shangjiaban.base.BaseFragment;
import com.superc.shangjiaban.bean.FeedBackBean;
import com.superc.shangjiaban.bean.HttpBean;
import com.superc.shangjiaban.bean.VillageAllBean;
import com.superc.shangjiaban.others.CustomListener;
import com.superc.shangjiaban.others.DialogUtil;
import com.superc.shangjiaban.others.PopUpUtil;
import com.superc.shangjiaban.ui.activity.SearchVillageActivity;
import com.superc.shangjiaban.ui.adapter.FeedBackAdapter;
import com.superc.shangjiaban.ui.adapter.VillageAllAdapter;
import com.superc.shangjiaban.utils.ShareUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.superc.shangjiaban.base.Constant.COMMUNITYLIST;
import static com.superc.shangjiaban.base.Constant.DELETEXIAOQU;
import static com.superc.shangjiaban.base.Constant.FEEDBACKLIST;
import static com.superc.shangjiaban.base.Otehers.REQESTCODE_VILLAG;

/**
 * 小区
 */
public class VillageFragment extends BaseFragment {


    @BindView(R.id.village_rb_all)
    RadioButton mVillageRbAll;
    @BindView(R.id.village_rb_msg)
    RadioButton mVillageRbMsg;
    @BindView(R.id.village_rg)
    RadioGroup mVillageRg;
    @BindView(R.id.village_edt_search)
    EditText mVillageEdtSearch;
    @BindView(R.id.village_imgv)
    ImageView mVillageImgv;
    @BindView(R.id.village_all_recy)
    RecyclerView mRv;
    @BindView(R.id.village_tv_search)
    TextView mVillageTvSearch;
    @BindView(R.id.smartrefresh)
    SmartRefreshLayout mSmartrefresh;

    private RadioButton[] rbs;
    /*所有小区*/
    private VillageAllAdapter mVillageAllAdapter;
    private List<VillageAllBean.UsersBean> mVillagAll;
    public int all_page = 1;
    /*反馈信息*/
    private FeedBackAdapter mFeedBackAdapter;
    private List<FeedBackBean.DateBean> mFeedList;
    private int feed_page = 1;


    private int isWhat = 0;
    private AlertDialog.Builder mBuilder;
    private TextView mTv_name;
    private TextView mTv_content;
    private TextView mTv_sure;
    private AlertDialog mAlertDialog;
    private PopUpUtil mPopUpUtil;
    private String[] mString_explain;
    private String uid = "";

    private String sheng_id = "";
    private String shi_id = "";
    private String xian_id = "";
    private String keyword = "";
    private String sheng = "";
    private String shi = "";
    private String xian = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_village, container, false);
        ButterKnife.bind(this, view);
        init();
        initOthers();
        return view;
    }

    @Override
    public void init() {
        uid = (String) ShareUtil.getInstance(this.getActivity()).get("uid", "");
        mPopUpUtil = new PopUpUtil(this.getActivity());
        mString_explain = new String[]{};
        mVillageEdtSearch.setOnClickListener(this);
        mVillageTvSearch.setOnClickListener(this);
        mVillageImgv.setOnClickListener(this);

        rbs = new RadioButton[]{mVillageRbAll, mVillageRbMsg};
        mVillageRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int view_id) {
                switch (view_id) {
                    case R.id.village_rb_all:
                        xiaoquList();
                        break;
                    case R.id.village_rb_msg:
                        setCityMsg("", "", "", "");
                        feed_page = 1;
                        mRv.setAdapter(mFeedBackAdapter);
                        changeColor(1);
                        feedBack();
                        break;
                }
            }
        });
    }
    /*MainActivity跳转时的刷新*/
    public void refreshXiaoquList() {
        all_page = 1;
        mSmartrefresh.setEnableLoadmore(true);
        getAllVillage(sheng_id, shi_id, xian_id, keyword);
    }

    public void xiaoquList() {
        all_page = 1;
        mVillagAll.clear();
        mRv.setAdapter(mVillageAllAdapter);
        changeColor(0);
        getAllVillage(sheng_id, shi_id, xian_id, keyword);
        if (sheng.equals("") && shi.equals("") && xian.equals("") && keyword.equals("")) {
            mVillageEdtSearch.setHint("筛选小区");
            mVillageEdtSearch.setText("");
        } else {
            mVillageEdtSearch.setText(sheng + ((shi == null || shi.equals("") ? "" : "-") + shi) + ((xian == null || xian.equals("") ? "" : "-") + xian) +
                    ((keyword == null || keyword.equals("") ? "" : "-") + keyword));
        }
    }

    public void initOthers() {
        uid = (String) ShareUtil.getInstance(this.getActivity()).get("uid", "");
        mVillagAll = new ArrayList<>();
        mFeedList = new ArrayList<>();
        mVillageAllAdapter = new VillageAllAdapter(this.getActivity(), mVillagAll, uid);
        mFeedBackAdapter = new FeedBackAdapter(this.getActivity(), mFeedList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(layoutManager);
        mRv.setAdapter(mVillageAllAdapter);

//        getAllVillage(sheng_id, shi_id, xian_id, keyword);//首先是所有小区


        mVillageAllAdapter.setOnViewClickListener(new VillageAllAdapter.OnViewClickListener() {
            @Override
            public void OnViewClickListener(int view_id, final int postion) {
                switch (view_id) {
                    case R.id.village_all_look:
                        showToast("查看编辑第" + postion + "条");
                        VillageAllBean.UsersBean exampleClass = mVillagAll.get(postion);
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("isAdd", false);
                        bundle.putString("id", exampleClass.getId());
                        stActivity(AddVillageActivity.class, bundle);
                        break;
                    case R.id.village_all_delete:
                        DialogUtil.getmInstance(VillageFragment.this.getActivity()).showRemindDialog().setRemindTvContent("确定删除?", "否", "是").setOnTvClickListener(new DialogUtil.OnTvClickListener() {
                            @Override
                            public void OnTvClickListener(int view_id) {
                                switch (view_id) {
                                    case R.id.dialog_remind_sure:
                                        VillageAllBean.UsersBean usersBean = mVillagAll.get(postion);
                                        deleteXiaoqu(usersBean.getId(), postion);
                                        break;
                                }
                            }
                        });

                        break;
                }


            }
        });
        mFeedBackAdapter.setCustomListener(new CustomListener() {
            @Override
            public void CustomeListener(int view_id, int postion) {
                showXqDialog(mFeedList.get(postion).getNickname(), mFeedList.get(postion).getContent());
            }
        });
        mVillageEdtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isWhat == 1) {
                    if (charSequence.length() > 0) {
                        mVillageTvSearch.setVisibility(View.VISIBLE);
                    } else {
                        mVillageTvSearch.setVisibility(View.GONE);
                        mSmartrefresh.autoRefresh();
                    }
                } else if (isWhat == 0) {
                    mVillageTvSearch.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mSmartrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                switch (isWhat) {
                    case 0:
                        all_page = 1;
                        mSmartrefresh.setEnableLoadmore(true);
                        getAllVillage(sheng_id, shi_id, xian_id, keyword);
                        break;
                    case 1:
                        feed_page = 1;
                        mSmartrefresh.setEnableLoadmore(true);
                        feedBack();
                        break;
                }
            }
        });

        mSmartrefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                switch (isWhat) {
                    case 0:
                        getAllVillage(sheng_id, shi_id, xian_id, keyword);
                        break;
                    case 1:
                        feedBack();
                        break;
                }
            }
        });


        initDialog();
    }

    /*初始化展示详情的dialog*/
    public void initDialog() {
        mBuilder = new AlertDialog.Builder(this.getActivity());
        View v = LayoutInflater.from(this.getActivity()).inflate(R.layout.dialog_xiangqing, null);
        mBuilder.setView(v);
        mTv_name = v.findViewById(R.id.dialog_xq_title);
        mTv_content = v.findViewById(R.id.dialog_xq_content);
        mTv_sure = v.findViewById(R.id.dialog_xq_sure);

        mAlertDialog = mBuilder.create();
        mAlertDialog.setCanceledOnTouchOutside(false);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.village_edt_search:
                if (isWhat == 0) {/*跳转到小区搜索*/
                    Intent intent = new Intent(this.getActivity(), SearchVillageActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("sheng", sheng);
                    bundle.putString("shi", shi);
                    bundle.putString("shi_id", shi_id);
                    bundle.putString("xian", xian);
                    bundle.putString("xian_id", xian_id);
                    bundle.putString("keyword", keyword);
                    intent.putExtras(bundle);
                    VillageFragment.this.getActivity().startActivityForResult(intent, REQESTCODE_VILLAG);
                }
                break;
            case R.id.village_imgv:
                mPopUpUtil.showPop(mString_explain, mVillageImgv);
                break;
            case R.id.village_tv_search:
                feed_page = 1;
                feedBack();
                break;
        }
    }

    /*所有小区*/
    public void getAllVillage(String sheng, String shi, String xian, String keyword) {
        isWhat = 0;
        mPopUpUtil.setShow(false);
        sheng_id = sheng;
        shi_id = shi;
        xian_id = xian;
        this.keyword = keyword;

        if (sheng_id.equals("") && shi_id.equals("") && xian_id.equals("") && keyword.equals("")) {
            mVillageEdtSearch.setHint("筛选小区");
            mVillageEdtSearch.setText("");
        }

        mVillageEdtSearch.setFocusable(false);
        mVillageEdtSearch.setFocusableInTouchMode(false);
        mVillageTvSearch.setVisibility(View.GONE);
        mString_explain = VillageFragment.this.getActivity().getResources().getStringArray(R.array.village_allxiaoqu);

        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("nickname", "");
        map.put("province_id", sheng);
        map.put("city_id", shi);
        map.put("p", all_page + "");
        map.put("size", "15");
        map.put("district_id", xian);
        map.put("key_word", keyword);
        map.put("cat_id", "");
        toGetData(map, COMMUNITYLIST, false, VillageAllBean.class, new CallNetBack<VillageAllBean>() {
            @Override
            public void callNetBack(VillageAllBean data) {
                String code = data.getCode();
                String info = data.getInfo();
                showToast(info);
                if (code.equals("200")) {
                    mSmartrefresh.finishRefresh();
                    mSmartrefresh.finishLoadmore();
                    if (all_page == 1) {
                        mVillagAll.clear();
                        mVillagAll.addAll(data.getUsers());
                        if (data.getUsers().size() < 15) {
                            mSmartrefresh.setEnableLoadmore(false);
                        }
                    } else {
                        for (VillageAllBean.UsersBean usersBean : data.getUsers()) {
                            mVillagAll.add(usersBean);
                        }
                        if (data.getUsers().size() < 15) {
                            mSmartrefresh.setEnableLoadmore(false);
                        }
                    }
                    mVillageAllAdapter.notifyDataSetChanged();
                    all_page++;
                } else if (code.equals("201")) {
                    mSmartrefresh.finishRefresh();
                    mSmartrefresh.finishLoadmore();
                    mVillagAll.clear();
                    mVillageAllAdapter.notifyDataSetChanged();

                }
            }
        });
    }

    /*进行小区搜索时设置EditText的内容*/
    public void setCityMsg(String sheng, String shi, String xian, String keyword) {
        this.sheng = sheng;
        this.shi = shi;
        this.xian = xian;
        this.keyword = keyword;
        if (sheng.equals("") && shi.equals("") && xian.equals("") && keyword.equals("")) {
            mVillageEdtSearch.setHint("筛选小区");
        } else {
            String result = sheng + ((shi == null || shi.equals("") ? "" : "-") + shi) + ((xian == null || xian.equals("") ? "" : "-") + xian) +
                    ((keyword == null || keyword.equals("") ? "" : "-") + keyword);
            if (result.startsWith("-")) {
                mVillageEdtSearch.setText(result.substring(1, result.length()));
            } else {
                mVillageEdtSearch.setText(result);
            }
        }
    }

    /*反馈信息*/
    public void feedBack() {
        mPopUpUtil.setShow(false);
        mString_explain = VillageFragment.this.getActivity().getResources().getStringArray(R.array.village_fankui);

        if (isWhat != 1) {
            mVillageEdtSearch.setText("");
            mVillageEdtSearch.setHint("请输入小区名称/用户昵称");
        }
        isWhat = 1;

        mVillageEdtSearch.setFocusable(true);
        mVillageEdtSearch.setFocusableInTouchMode(true);

        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("key_word", mVillageEdtSearch.getText().toString());
        map.put("page", feed_page + "");
        map.put("size", "15");

        toGetData(map, FEEDBACKLIST, false, FeedBackBean.class, new CallNetBack<FeedBackBean>() {
            @Override
            public void callNetBack(FeedBackBean data) {
                String code = data.getCode();
                String info = data.getInfo();
                showToast(info);
                if (code.equals("200")) {
                    mSmartrefresh.finishRefresh();
                    mSmartrefresh.finishLoadmore();
                    if (feed_page == 1) {
                        mFeedList.clear();
                        mFeedList.addAll(data.getDate());
                        if (data.getDate().size() < 15) {
                            mSmartrefresh.setEnableLoadmore(false);
                        }
                    } else {
                        for (FeedBackBean.DateBean bean : data.getDate()) {
                            mFeedList.add(bean);
                        }
                        if (data.getDate().size() < 15) {
                            mSmartrefresh.setEnableLoadmore(false);
                        }
                    }
                    mFeedBackAdapter.notifyDataSetChanged();
                    feed_page++;
                }
            }
        });

    }

    /*小区删除*/
    public void deleteXiaoqu(String xiaoquid, final int position) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", xiaoquid);
        toGetData(map, DELETEXIAOQU, false, HttpBean.class, new CallNetBack<HttpBean>() {
            @Override
            public void callNetBack(HttpBean data) {
                int code = data.getCode();
                if (code == 200) {
                    mVillagAll.remove(position);
                    mVillageAllAdapter.notifyDataSetChanged();
                }
                showToast(data.getInfo());
            }
        });
    }


    /*展示详情dialog*/
    public void showXqDialog(String name, String content) {
        mAlertDialog.show();
        mTv_name.setText(" " + name);
        mTv_content.setText(content);
        mTv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlertDialog.dismiss();
            }
        });

    }

    public void changeColor(int pos) {
        for (int i = 0; i < rbs.length; i++) {
            RadioButton rb = rbs[i];
            if (i == pos) {
                rb.setTextColor(VillageFragment.this.getResources().getColor(R.color.black));
            } else {
                rb.setTextColor(VillageFragment.this.getResources().getColor(R.color.gray_nine));
            }
        }
    }

    /*进行下来刷新的操作*/
    public void refresh() {
        if (isWhat == 0 && mSmartrefresh != null) {
            mSmartrefresh.autoRefresh();
        }
    }

}
