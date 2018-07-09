package com.superc.shangjiaban.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.slide.SlideBackActivity;
import com.superc.shangjiaban.utils.ToastUtil;
import com.umeng.analytics.MobclickAgent;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Activity的基础--SlideBackActivity实现了左侧边缘滑动返回
 */

public abstract class BaseActivity extends SlideBackActivity implements View.OnClickListener {
    private ViewGroup mRootView;
    private TextView mtv_title;
    private TextView mtv_right;
    private ImageView mimgv_back;
    private ImageView mimgv_small;
    private ImageView mimgv_big;
    private RequestQueue mRequestQueue;
    private PopupWindow mPopupWindow;
    private List<Activity> mActivityList;

    @Override
    public void setContentView(int layoutResID) {
        setContentView(LayoutInflater.from(this).inflate(layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        mActivityList = new ArrayList<>();
        mRootView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.action_bar_activity_layout, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.BELOW, R.id.title_bar_container);
        mRootView.addView(view, 1, params);
        super.setContentView(mRootView);
        mtv_title = (TextView) findViewById(R.id.title_text_center);
        mtv_right = (TextView) findViewById(R.id.title_text_right);
        mimgv_back = (ImageView) findViewById(R.id.left_back_image_view);
        mimgv_small = (ImageView) findViewById(R.id.title_imgv_samll);
        mimgv_big = (ImageView) findViewById(R.id.title_imgv_big);

        mActivityList.add(this);
        mRequestQueue = NoHttp.newRequestQueue();
        init();
        getData();
    }

    public void finishAll() {
        for (Activity ac : mActivityList) {
            ac.finish();
        }
    }


    /*需要写这个方法到里面--否则不执行*/
    public abstract void initListener();

    public abstract void init();

    public abstract void getData();

    /**
     * @param title               中间显示的标题
     * @param title_right         右边显示的提示             为0或null--不显示
     * @param show_left           是否显示左边的返回按钮      false--不显示  true--显示
     * @param img_smallid         右边的小图标              0--不显示
     * @param img_bigid           右边的大图标              0--不显示
     * @param onViewClickListener 用来实现监听的             OnSmallV...--小图标监听   OnBig...--大图标监听
     */

    public void setFinalContent(Object title, Object title_right, boolean show_left, int img_smallid, int img_bigid, final OnViewClickListener onViewClickListener) {
        if (title instanceof String) {
            mtv_title.setText((String) title);
            mtv_title.setVisibility((String) title == null ? View.GONE : View.VISIBLE);
        } else if (title instanceof Integer) {
            mtv_title.setText((Integer) title);
            mtv_title.setVisibility((Integer) title == 0 ? View.GONE : View.VISIBLE);
        } else {
            mtv_title.setText((CharSequence) title);
            mtv_title.setVisibility(title == null ? View.GONE : View.VISIBLE);
        }
        if (title_right instanceof String) {
            mtv_right.setText((String) title_right);
            mtv_right.setVisibility((String) title_right == null ? View.GONE : View.VISIBLE);
        } else if (title_right instanceof Integer) {
            mtv_right.setText((Integer) title_right);
            mtv_right.setVisibility((Integer) title_right == 0 ? View.GONE : View.VISIBLE);
        } else {
            mtv_right.setText((CharSequence) title_right);
            mtv_right.setVisibility(title_right == null ? View.GONE : View.VISIBLE);
        }
        mimgv_back.setVisibility(show_left ? View.VISIBLE : View.GONE);
        mimgv_small.setVisibility(img_smallid == 0 ? View.GONE : View.VISIBLE);
        if (img_smallid != 0) {
            mimgv_small.setImageResource(img_smallid);
        }
        mimgv_big.setVisibility(img_bigid == 0 ? View.GONE : View.VISIBLE);
        mimgv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mtv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onViewClickListener != null) {
                    onViewClickListener.OnSmallClickListener();
                }
            }
        });
        mimgv_small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onViewClickListener != null) {
                    onViewClickListener.OnSmallClickListener();
                }
            }
        });
        mimgv_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onViewClickListener != null) {
                    onViewClickListener.OnBigClickListener();
                }
            }
        });
    }

    /**
     * @param title     标题名称
     * @param show_left 是否显示左侧返回按钮
     */
    public void setFinalContentL(Object title, boolean show_left) {
        setFinalContent(title, null, show_left, 0, 0, null);
    }

    /**
     * @param title               标题名称
     * @param right_title         右侧标题名称
     * @param img_small           右侧图片id
     * @param onViewClickListener 监听
     */
    public void setFinalContentRs(Object title, Object right_title, int img_small, OnViewClickListener onViewClickListener) {
        setFinalContent(title, right_title, false, img_small, 0, onViewClickListener);
    }


    /**
     * @param msg 弹出提示类型--只支持String和Int索引
     */
    public void showToast(Object msg) {
        if (msg instanceof String) {
            ToastUtil.showToast(BaseActivity.this, (String) msg);
        } else {
            ToastUtil.showToast(BaseActivity.this, (int) msg);
        }
    }

    /**
     * @param bundle 跳转时携带的数据
     * @param cls    跳转到的Activity名称class
     */
    public void stActivity(Class cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * @param map   访问接口所携带的数据集合
     * @param url   访问的接口
     * @param isGet 是否为get请求  true--get请求  false--post请求
     */
    public void toGetData(Map<String, String> map, final String url, boolean isGet, final CallNetBack backListener) {

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(url, isGet ? RequestMethod.GET : RequestMethod.POST);
        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
        String enter = "?";
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
//            if(value instanceof String){
            request.add(entry.getKey(), entry.getValue());
            enter += (entry.getKey() + "=" + entry.getValue() + "&");
        }
        Log.e("访问接口及参数", url + "    " + (enter.length() != 1 ? url + enter.substring(0, enter.length() - 1) + "\n" : "无参数"));
        mRequestQueue.add(2, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                if (backListener != null) {
                    backListener.callNetBack(response.get());
                    String[] split = url.split("/");
                    Log.e("访问:" + split[split.length - 2] + "/" + split[split.length - 1] + " 返回数据", "\n" + (response.get() != null ? response.get().toString() : "无返回数据"));
                }
            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {
                ToastUtil.showToast(BaseActivity.this, "网络异常");
                String[] split = url.split("/");
                Log.e("访问:" + split[split.length - 2] + "/" + split[split.length - 1] + " 接口时出错:", "\n" + (response.get() != null ? response.get().toString() : response.toString()));
            }

            @Override
            public void onFinish(int what) {
            }
        });
    }

    public interface CallNetBack {
        /* @param data  服务器返回内容*/
        void callNetBack(JSONObject data);
    }

    public abstract class OnViewClickListener {

        public void OnSmallClickListener() {
        }

        ;

        public void OnBigClickListener() {
        }

        ;
    }

    /*加载Loading 如下两个*/
    public void showLoadPop() {
        mPopupWindow = new PopupWindow(this);
        mPopupWindow.setContentView(LayoutInflater.from(this).inflate(R.layout.layout_load_popup, null));
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

    public void hideLoadPop() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
