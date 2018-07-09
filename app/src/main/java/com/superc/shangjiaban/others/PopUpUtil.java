package com.superc.shangjiaban.others;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.ui.adapter.PopupLvAdapter;

/**
 * 上面的那个i弹出的说明的提示
 * 里面是一个PopUpwindow--一个ListView<String>用来展示需要说明的内容
 * 也就是需要传入一个List<String>和需要展示的位置的View
 */

public class PopUpUtil {

    private final PopupWindow mPopupWindow;
    private Context mContext;
    private String[] mString_explain;
    private PopupLvAdapter mPopupLvAdapter;
    private boolean isShow = false;
    private final ListView mLv;

    public PopUpUtil(Context context) {
        mContext = context;
        View v = LayoutInflater.from(context).inflate(R.layout.popup_explain, null);
        mPopupWindow = new PopupWindow(context);
        mPopupWindow.setContentView(v);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        mLv = v.findViewById(R.id.popup_lv);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                isShow=false;
            }
        });
    }

    public void showPop(String[] mlist, View v) {
        mString_explain = mlist;
        mPopupLvAdapter = new PopupLvAdapter(mContext, mString_explain);
        mLv.setAdapter(mPopupLvAdapter);
        mPopupLvAdapter.notifyDataSetChanged();
        if (!isShow) {
            mPopupWindow.showAsDropDown(v);
            mPopupWindow.setFocusable(true);/*重要的一句*/
        } else {
            mPopupWindow.dismiss();
        }
        isShow = !isShow;
    }


    public void setShow(boolean show) {
        isShow = show;
    }
}
