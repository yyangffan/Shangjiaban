package com.superc.shangjiaban.others;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.superc.shangjiaban.R;

/**
 * 各种dialog的收集类
 */

public class DialogUtil {
    private LayoutInflater mInflater;
    private OnTvClickListener mOnTvClickListener;

    private AlertDialog.Builder mBuilder;
    private AlertDialog mDialog;
    private TextView mTv_one;
    private TextView mTv_two;
    private TextView mTv_three;
    private ImageView mimgv;
    private TextView mTv_title;
    private TextView mTv_cancle;
    private TextView mTv_sure;
    private LinearLayout mll_chose;
    private RelativeLayout mrv_remind;
    private ConstraintLayout mConstraintLayout;

    private static DialogUtil mInstance;
    private View mV;

    private DialogUtil(Context context) {
        mInflater = LayoutInflater.from(context);
        mBuilder = new AlertDialog.Builder(context);
        mV = mInflater.inflate(R.layout.dialog_chose, null);
        mBuilder.setView(mV);
        mDialog = mBuilder.create();
        mDialog.setCanceledOnTouchOutside(false);
        initViews();
    }

    public static DialogUtil getmInstance(Context context) {
        if (mInstance == null) {
            synchronized (DialogUtil.class) {
                if (mInstance == null) {
                    mInstance = new DialogUtil(context);
                }
            }
        }
        return mInstance;
    }

    public void setOnTvClickListener(OnTvClickListener onTvClickListener) {
        mOnTvClickListener = onTvClickListener;
    }

    private void initViews() {
        mTv_one = mV.findViewById(R.id.dialog_chose_one);
        mTv_two = mV.findViewById(R.id.dialog_chose_two);
        mTv_three = mV.findViewById(R.id.dialog_chose_three);
        mll_chose = mV.findViewById(R.id.dialog_ll_chose);
        mrv_remind=mV.findViewById(R.id.dialog_rl_remind);
        mimgv=mV.findViewById(R.id.dialog_remind_imgv);
        mTv_title=mV.findViewById(R.id.dialog_remind_title);
        mTv_cancle=mV.findViewById(R.id.dialog_remind_cancle);
        mTv_sure=mV.findViewById(R.id.dialog_remind_sure);
        mConstraintLayout=mV.findViewById(R.id.dialog_cons);
    }


    /*弹出选择框*/
    public DialogUtil showChoseDialog() {
        mConstraintLayout.setVisibility(View.GONE);
        mll_chose.setVisibility(View.VISIBLE);
        Window window = mDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));/*背景透明的属性*/
        mDialog.show();
        setChoseTvContent(null,null,null);
        return this;
    }

    /**
     * @param one   第一个View显示内容----默认(拍照)
     * @param two   第二个View显示内容----默认(相册)
     * @param three 第三个View显示内容----默认(取消)
     * @return
     */
    public DialogUtil setChoseTvContent(String one, String two, String three) {
        config(mTv_one, one, R.id.dialog_chose_one);
        config(mTv_two, two, R.id.dialog_chose_two);
        config(mTv_three, three, R.id.dialog_chose_three);
        return this;
    }
    /*弹出提示框*/
    public DialogUtil showRemindDialog() {
        mConstraintLayout.setVisibility(View.GONE);
        mrv_remind.setVisibility(View.VISIBLE);
//        Window window = mDialog.getWindow();
//        window.setGravity(Gravity.CENTER);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
        this.setRemindTvContent(null,null,null);
        return this;
    }

    /**
     * @param title     提示语----默认(提示)
     * @param cancle_t  取消按钮显示内容----默认(否)
     * @param sure_t    确定按钮显示内容----默认(是)
     * @return
     */
    public DialogUtil setRemindTvContent(String title,String cancle_t,String sure_t) {
        if(mTv_title!=null&&title!=null){
            mTv_title.setText(title);
        }
        config(mTv_cancle,cancle_t,R.id.dialog_remind_cancle);
        config(mTv_sure,sure_t,R.id.dialog_remind_sure);
        return this;
    }

    /**
     * @param imgv_resource 展示提示时候的图片
     */
    public  void setImgvResource(int imgv_resource){
        mimgv.setImageResource(imgv_resource);
    }

    /**
     * @param view      目标View
     * @param content   要设置的View内容
     * @param view_id   目标View的Id，提供给监听事件
     */
    private void config(TextView view, String content, final int view_id) {
        if (view != null) {
            if (content != null) {
                view.setText(content);
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                    if (mOnTvClickListener != null) {
                        mOnTvClickListener.OnTvClickListener(view_id);
                    }
                }
            });
        } else {
            Log.e("DialogUtile", "View为空");
        }
    }

    public interface OnTvClickListener {
        void OnTvClickListener(int view_id);
    }
}
