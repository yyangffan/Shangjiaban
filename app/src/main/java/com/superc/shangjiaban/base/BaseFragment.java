package com.superc.shangjiaban.base;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.google.gson.Gson;
import com.superc.shangjiaban.R;
import com.superc.shangjiaban.utils.ToastUtil;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

/**
 * Fragment的基础
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private RequestQueue mRequestQueue;
    private PopupWindow mPopupWindow;

    /**
     * @param msg 弹出提示类型--只支持String和Int索引
     */
    public void showToast(Object msg) {
        if (msg instanceof String) {
            ToastUtil.showToast(this.getActivity(), (String) msg);
        } else {
            ToastUtil.showToast(this.getActivity(), (int) msg);
        }
    }

    /**
     * @param bundle 跳转时携带的数据
     * @param cls    跳转到的Activity名称class
     */
    public void stActivity(Class cls, Bundle bundle) {
        Intent intent = new Intent(this.getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public abstract void init();

    /**
     * @param map   访问接口所携带的数据集合
     * @param url   访问的接口
     * @param isGet 是否为get请求  true--get请求  false--post请求
     */
    public <T> void toGetData(Map<String, String> map, final String url, boolean isGet, final Class<T> type, final CallNetBack<T> backListener) {
        mRequestQueue = NoHttp.newRequestQueue();
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(url, isGet ? RequestMethod.GET : RequestMethod.POST);
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
//                    backListener.callNetBack(response.get());
                    T t = new Gson().fromJson(response.get().toString(), type);
                    backListener.callNetBack(t);

                    String[] split = url.split("/");
                    Log.e("访问:" + split[split.length - 2] + "/" + split[split.length - 1] + " 返回数据", "\n" + (response.get() != null ? response.get().toString() : "无返回数据"));
                }
            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {
                ToastUtil.showToast(BaseFragment.this.getActivity(), "网络异常");
                String[] split = url.split("/");
                Log.e("访问:" + split[split.length - 2] + "/" + split[split.length - 1] + " 接口时出错:", "\n" + (response.get() != null ? response.get().toString() : response.toString()));
            }

            @Override
            public void onFinish(int what) {
            }
        });
    }

    public interface CallNetBack<T> {
        /* @param data  服务器返回内容*/
         void callNetBack(T data);
    }

    /*加载Loading 如下两个*/
    public void showLoadPop() {
        mPopupWindow = new PopupWindow(this.getActivity());
        mPopupWindow.setContentView(LayoutInflater.from(this.getActivity()).inflate(R.layout.layout_load_popup, null));
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(this.getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

    public void hideLoadPop() {
        if(mPopupWindow!=null){
            mPopupWindow.dismiss();
        }
    }

}
