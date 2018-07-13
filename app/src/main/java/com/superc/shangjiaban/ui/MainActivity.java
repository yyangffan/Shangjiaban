package com.superc.shangjiaban.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.base.AppAppLication;
import com.superc.shangjiaban.base.BaseActivity;
import com.superc.shangjiaban.base.Constant;
import com.superc.shangjiaban.jiguang.SetJPushAlias;
import com.superc.shangjiaban.others.MyViewPager;
import com.superc.shangjiaban.others.PublicBean;
import com.superc.shangjiaban.utils.ShareUtil;
import com.superc.shangjiaban.views.TabContainerView;
import com.superc.shangjiaban.views.TabFragmentAdapter;

import org.json.JSONObject;

import java.util.HashMap;

import static com.superc.shangjiaban.base.Otehers.REQESTCODE_VILLAG;
import static com.superc.shangjiaban.base.Otehers.REQUESTCODE_ALLDDSEARCH;
import static com.superc.shangjiaban.base.Otehers.REQUESTCODE_ALLDZITI;
import static com.superc.shangjiaban.base.Otehers.REQUESTCODE_DDSEARCH;
import static com.superc.shangjiaban.base.Otehers.REQUESTCODE_USRE;
import static com.superc.shangjiaban.base.Otehers.REQUESTCODE_YJ;


public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    /*tab图标集合*/
    private final int ICONS_RES[][] = {{R.drawable.sy1, R.drawable.sy2},
            {R.drawable.yh1, R.drawable.yh2},
            {R.drawable.xq1, R.drawable.xq2}, {R.drawable.gj1, R.drawable.gj2},
            {R.drawable.dd1, R.drawable.dd2}};

    /*tab 颜色值*/
    private final int[] TAB_COLORS = new int[]{R.color.gray_nine,
            R.color.blue_bot};
    private Fragment[] fragments = null;
    private HomeFragment mHomeFragment;//首页
    private UserFragment mUserFragment;//用户
    private VillageFragment mVillageFragment;//小区
    private PropertyFragment mPropertyFragment;//管家
    private ShopFragment mShopFragment;//订单
    private MyViewPager mPager;
    private TabContainerView mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setSlideable(false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFinalContent(R.string.name_home, null, false, 0, R.drawable.sz, new OnViewClickListener() {
            @Override
            public void OnBigClickListener() {
                super.OnBigClickListener();
                MainActivity.this.startActivityForResult(new Intent(MainActivity.this, SettingActivity.class), 100);
//                stActivity(SettingActivity.class,null);
            }
        });

        initListener();
    }

    @Override
    public void initListener() {
        //开启订单轮询进行语音提醒(由于ios不能再锁屏状态下提醒所以才舍弃了这种办法，使用了推送的方式来进行提醒)
//        Intent intent = new Intent(this, MyService.class);
//        this.startService(intent);
    }


    @Override
    public void init() {
        mHomeFragment = new HomeFragment();
        mUserFragment = new UserFragment();
        mVillageFragment = new VillageFragment();
        mPropertyFragment = new PropertyFragment();
        mShopFragment = new ShopFragment();
        fragments = new Fragment[]{mHomeFragment, mUserFragment, mVillageFragment, mPropertyFragment, mShopFragment};
        TabFragmentAdapter mAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragments);
        mPager = (MyViewPager) findViewById(R.id.tab_pager);
//        mPager.setScrollble(false);
        //设置当前可见Item左右可见page数，次范围内不会被销毁
        mPager.setOffscreenPageLimit(1);
        mPager.setAdapter(mAdapter);
        mTabLayout = (TabContainerView) findViewById(R.id.ll_tab_container);
        mTabLayout.setOnPageChangeListener(this);
        mTabLayout.initContainer(getResources().getStringArray(R.array.tab_main_title), ICONS_RES, TAB_COLORS, false);
        int width = getResources().getDimensionPixelSize(R.dimen.tab_icon_width);
        int height = getResources().getDimensionPixelSize(R.dimen.tab_icon_height);
        mTabLayout.setContainerLayout(R.layout.tab_container_view, R.id.iv_tab_icon, R.id.tv_tab_text, width, height);
        mTabLayout.setViewPager(mPager);
        mPager.setCurrentItem(getIntent().getIntExtra("tab", 0));
        Log.e("跳转时的current", getIntent().getIntExtra("tab", 0) + "");
        if (mHomeFragment != null) {
            mHomeFragment.getData();
        }
    }

    /*设置跳转*/
    public void setSelectPage(int num) {
        mPager.setCurrentItem(num);
        mTabLayout.setTextNormalColor(R.color.gray_nine);
    }

    /*以下设置默认选中哪一个--跳转的时候进行使用*/
    public void setUserWhat(int what) {//用户
        if (what == 0) {
//            mUserFragment.userlist();
            mUserFragment.refreshUserlist();
        } else if (what == 1) {
//            mUserFragment.huangjin();
            mUserFragment.refreshHuangjin();
        }
    }

    public void setXiaoquWhat(int what) {//小区
        if (what == 0) {
//            mVillageFragment.xiaoquList();
            mVillageFragment.refreshXiaoquList();
        }
    }

    public void setguanjiaWhat(int what) {//管家
        if (what == 2) {
//            mPropertyFragment.yongjinJIlu();
            mPropertyFragment.refreshYongjinjilu();
        }
    }

    public void setdingdanWhat(int what) {//订单
        if (what == 0) {
//            mShopFragment.dingdanAll();
            mShopFragment.refreshDingdanAll();
        }
    }
    /*-------------------------------*/


    @Override
    public void getData() {

    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        toChange(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void toChange(int pos) {
        for (int index = 0, len = fragments.length; index < len; index++) {
            fragments[index].onHiddenChanged(index != pos);
        }
        if (pos == 1) {
            setFinalContentL(R.string.name_user, false);
        } else if (pos == 2) {
            setFinalContentRs(R.string.name_Village, "添加小区", R.drawable.plus, new OnViewClickListener() {
                @Override
                public void OnSmallClickListener() {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isAdd", true);
                    stActivity(AddVillageActivity.class, null);
                }
            });
            if (mVillageFragment != null) {
                mVillageFragment.refresh();
            }

        } else if (pos == 3) {
            setFinalContentL(R.string.name_property, false);
        } else if (pos == 4) {
            if (mShopFragment != null) {
                if (!mShopFragment.isHidden()) {
                    mShopFragment.showWhat();
                }
            }
        } else {
            setFinalContent(R.string.name_home, null, false, 0, R.drawable.sz, new OnViewClickListener() {
                @Override
                public void OnBigClickListener() {
                    super.OnBigClickListener();
//                    stActivity(SettingActivity.class,null);
                    MainActivity.this.startActivityForResult(new Intent(MainActivity.this, SettingActivity.class), 100);
                }
            });
            if (mHomeFragment != null) {
                mHomeFragment.getData();
            }
        }
    }

    public void setTitleRight(String isWhat) {
//        setFinalContentL(R.string.name_shop, false);

        switch (isWhat) {
            case "0":
                setFinalContentL(R.string.name_shop, false);
                break;
            case "1":
                setFinalContentRs(R.string.name_shop, "批量发货", R.drawable.tz, new OnViewClickListener() {
                    @Override
                    public void OnSmallClickListener() {
                        mShopFragment.gotoPLFh();
                    }
                });
                break;
            case "2":
                /*去掉下面的--不需要右上角的小标提示了*/
                setFinalContentL(R.string.name_shop, false);
//                setFinalContentRs(R.string.name_shop, "通知取货", R.drawable.tz, new OnViewClickListener() {
//                    @Override
//                    public void OnSmallClickListener() {
//                        mShopFragment.gotoTongzhi();
//                    }
//                });
                break;
//
        }
    }

    public void setTitleRightT() {
        setFinalContentRs(R.string.name_shop, "通知取货", R.drawable.tz, new OnViewClickListener() {
            @Override
            public void OnSmallClickListener() {
                mShopFragment.gotoTzQh();
            }
        });
    }

    long stT = 0;
    long endT = 0;

    @Override
    public void onBackPressed() {
        stT = System.currentTimeMillis();
        if (stT - endT >= 2000) {
            showToast("双击退出");
            endT = stT;
            return;
        }
        logout();
        super.onBackPressed();
    }

    public void logout() {
        new SetJPushAlias("", this).cancleAlias();
        ShareUtil.getInstance(this).remove("uid");
        toGetData(new HashMap<String, String>(), Constant.LOGNOUT, false, new CallNetBack() {
            @Override
            public void callNetBack(JSONObject data) {

            }
        });
    }

    //finish之后是不执行的
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);//设置新的intent

    }

    @Override
    protected void onResume() {
        super.onResume();
        int tab = getIntent().getIntExtra("tab", 0);
        boolean isJp = (boolean) ShareUtil.getInstance(this).get("isJp", false);
        if (tab != 0 && isJp) {
            mShopFragment.refreshDingdanAll();
            mPager.setCurrentItem(tab);
            ShareUtil.getInstance(AppAppLication.getmContext()).put("isJp", false);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        int tab = getIntent().getIntExtra("tab", 0);
        boolean isJp = (boolean) ShareUtil.getInstance(this).get("isJp", false);
        if (tab != 0 && isJp) {
            mShopFragment.refreshDingdanAll();
            mPager.setCurrentItem(tab);
            ShareUtil.getInstance(AppAppLication.getmContext()).put("isJp", false);
        }
        if (mHomeFragment != null) {
            mHomeFragment.getData();
        }
        if (mVillageFragment != null) {
            mVillageFragment.refresh();
        }
        if (mPropertyFragment != null) {
            mPropertyFragment.refresh();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            switch (requestCode) {
                case REQUESTCODE_USRE:/*黄金抵费记录进行搜索*/
                    if (extras != null) {
                        String st_time = extras.getString("st_time");
                        String ed_time = extras.getString("ed_time");
                        String keyword = extras.getString("keyword");
//                        mUserFragment.getGodList(keyword, st_time, ed_time, "1");
                        mUserFragment.setGodCanshu(st_time, ed_time, keyword);/*需要将page传过去进行第一页的查询*/
                        Log.e("黄金抵费执行搜索", "st_time=" + st_time + " ed_time=" + ed_time + " keyword=" + keyword);
                    }

                    break;
                case REQESTCODE_VILLAG:/*小区搜索的返回*/
                    if (extras != null) {
                        String sheng_id = extras.getString("sheng_id");
                        String shi_id = extras.getString("shi_id");
                        String xian_id = extras.getString("xian_id");
                        String keyword = extras.getString("keyword");
                        String sheng = extras.getString("sheng");
                        String shi = extras.getString("shi");
                        String xian = extras.getString("xian");
                        mVillageFragment.all_page = 1;
                        mVillageFragment.getAllVillage(sheng_id, shi_id, xian_id, keyword);
                        mVillageFragment.setCityMsg(sheng, shi, xian, keyword);
                        Log.e("小区搜索参数", "sheng_id=" + sheng_id + " shi_id=" + shi_id + " xian_id=" + xian_id + " keyword=" + keyword);
                    }

                    break;
                case REQUESTCODE_YJ:/*佣金提现记录的返回*/
                    if (extras != null) {
                        String st_time = extras.getString("st_time");
                        String ed_time = extras.getString("ed_time");
                        String keyword = extras.getString("keyword");
                        mPropertyFragment.jilu_page = 1;
                        mPropertyFragment.getYjJl(keyword, st_time, ed_time);/*需要将page传过去进行第一页的查询*/
                        mPropertyFragment.setMsg(keyword, st_time, ed_time);
                    }
                    break;
                case REQUESTCODE_DDSEARCH:/*待发货的返回*/
                    if (extras != null) {
                        String sheng_id = extras.getString("sheng_id");
                        String shi_id = extras.getString("shi_id");
                        String quxian_id = extras.getString("quxian_id");
                        String qu_id = extras.getString("qu_id");
                        String sheng = extras.getString("sheng");
                        String shi = extras.getString("shi");
                        String quxian = extras.getString("quxian");
                        String qu = extras.getString("qu");
                        String huodong = extras.getString("huodong");
                        String huodong_id = extras.getString("huodong_id");
                        mShopFragment.fh_page = 1;
                        mShopFragment.setFhSearchCanshu(sheng_id, shi_id, quxian_id, qu_id, huodong_id);
                        mShopFragment.setFhCanshu(sheng, shi, quxian, qu, huodong);
                    } else {
                        mShopFragment.fh_page = 1;
                        mShopFragment.setFhSearchCanshu("", "", "", "", "");
                        mShopFragment.setFhCanshu("", "", "", "", "");
                    }
                    break;
                case REQUESTCODE_ALLDDSEARCH:/*所有订单搜索的返回*/
                    if (extras != null) {
                        PublicBean publicBean = (PublicBean) extras.getSerializable("data");
                        mShopFragment.all_page = 1;
                        mShopFragment.setCanshu(publicBean);
                    } else {
                        PublicBean publicBean = new PublicBean("", "", "", "", "", "", "", "", "", "", "");
                        mShopFragment.all_page = 1;
                        mShopFragment.setCanshu(publicBean);
                    }
                    break;
                case REQUESTCODE_ALLDZITI:
                    if (extras != null) {
                        String xiaoqu = extras.getString("xiaoqu");
                        String xiaoqu_id = extras.getString("xiaoqu_id");
                        String huodong = extras.getString("huodong");
                        String huodong_id = extras.getString("huodong_id");
                        String keyword = extras.getString("keyword");
                        mShopFragment.zt_page = 1;
                        mShopFragment.setZtCanshu(keyword, huodong_id, xiaoqu_id, "");
                        mShopFragment.setZtWzCanshu(huodong, xiaoqu, keyword);
                    } else {
                        mShopFragment.zt_page = 1;
                        mShopFragment.setZtCanshu("", "", "", "");
                        mShopFragment.setZtWzCanshu("", "", "");
                    }

                    break;
                case 100:
                    MainActivity.this.finish();
                    break;

            }
        }
    }
}
