package com.superc.shangjiaban.ui.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.bean.DateUtil;
import com.superc.shangjiaban.bean.ShopZtBean;
import com.superc.shangjiaban.others.CustomListener;
import com.superc.shangjiaban.utils.ShareUtil;
import com.superc.shangjiaban.utils.ToastUtil;

import java.util.List;

/**
 * Created by user on 2018/3/8.
 */

public class ShopZtAdapter extends RecyclerView.Adapter<ShopZtAdapter.ViewHolder> {
    private Context mContext;
    private List<ShopZtBean.DateBean> mClassList;
    private LayoutInflater mInflater;
    private CustomListener mCustomListener;
    private String role_id = "";
    private OnItemClickListener mOnItemClickListener;

    public ShopZtAdapter(Context context, List<ShopZtBean.DateBean> classList) {
        mContext = context;
        mClassList = classList;
        mInflater = LayoutInflater.from(mContext);
        role_id = (String) ShareUtil.getInstance(mContext).get("role_id", "");
    }

    public void setCustomListener(CustomListener customListener) {
        mCustomListener = customListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.layout_shop_zt, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder vh, final int position) {
        ShopZtBean.DateBean exampleClass = mClassList.get(position);
        vh.mtv_name.setText(exampleClass.getConsignee());
        vh.mtv_danhao.setText(exampleClass.getOrderSn());
        vh.mtv_time.setText(DateUtil.getStrTime(exampleClass.getConfirmTime()));
        vh.mtv_num.setText(exampleClass.getGoodsNum());
        vh.mtv_guige.setText(exampleClass.getSpecKeyName());
        vh.mtv_zongjia.setText(exampleClass.getGoodsPrice());
        vh.mtv_phone.setText(exampleClass.getMobile());
        if (exampleClass.getShippingTime().equals("0")) {
            vh.mtv_xdtime.setVisibility(View.GONE);
        } else {
            vh.mtv_xdtime.setVisibility(View.VISIBLE);
            vh.mtv_xdtime.setText(DateUtil.getStrTime(exampleClass.getShippingTime()));
        }
        vh.mtv_tishi.setText("已通知待自提");
        String state = exampleClass.getState();
        switch (state) {
            case "1":
                vh.mtv_tishi.setText("待通知");
//                vh.mtv_rightTs.setText("待通知");
                vh.mtv_rightTs.setVisibility(View.GONE);
                break;
            case "2":
                vh.mtv_tishi.setText("已通知待自提");
                vh.mtv_rightTs.setVisibility(View.VISIBLE);
                vh.mtv_rightTs.setText("已完成");
                break;
            case "3":
                vh.mtv_tishi.setText("已提货");
                vh.mtv_rightTs.setVisibility(View.GONE);
                break;
        }
        vh.mtv_rightTs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (role_id.equals("1") || role_id.equals("2") || role_id.equals("3")) {
                    if (mCustomListener != null) {
                        mCustomListener.CustomeListener(R.id.shop_zt_rightts, position);
                    }
                } else {
                    vh.mtv_rightTs.setVisibility(View.GONE);
                    ToastUtil.showToast(mContext, "无法进行该操作");
                }
            }
        });
        if (role_id.equals("1") || role_id.equals("2") || role_id.equals("3")) {
            String order_prom_type = exampleClass.getOrder_prom_type();
            if (order_prom_type.equals("6")) {
                vh.mtv_tishi.setText("已通知待自提");
                vh.mtv_rightTs.setText("已完成");
                vh.mtv_rightTs.setVisibility(View.VISIBLE);
                vh.mtv_tishi.setVisibility(View.GONE);
                vh.mtv_tv_guige.setVisibility(View.GONE);
                vh.mtv_guige.setVisibility(View.GONE);
                vh.mtv_xdtime.setVisibility(View.GONE);
                vh.mtv_tv_xdTime.setVisibility(View.GONE);
            } else {
                if (!state.equals("1")) {
                    vh.mtv_rightTs.setVisibility(View.VISIBLE);
                }
            }
        } else {
            vh.mtv_rightTs.setVisibility(View.GONE);
        }
        vh.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.OnItemClickListener(position);
                }
            }
        });
        String shipping_code = exampleClass.getShipping_code();
        if(shipping_code.equals("1000")){//自提
            vh.mtv_what.setText("自提");
        }else {//配送
            vh.mtv_what.setText("配送");
        }

    }

    @Override
    public int getItemCount() {
        return mClassList == null ? 0 : mClassList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mtv_name;
        private TextView mtv_danhao;
        private TextView mtv_time;
        private TextView mtv_tishi;
        private TextView mtv_num;
        private TextView mtv_guige;
        private TextView mtv_zongjia;
        private TextView mtv_phone;
        private TextView mtv_xdtime;
        private TextView mtv_rightTs;
        private TextView mtv_tv_guige;
        private TextView mtv_tv_xdTime;
        private TextView mtv_what;
        private ConstraintLayout mConstraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mtv_name = itemView.findViewById(R.id.shop_zt_name);
            mtv_danhao = itemView.findViewById(R.id.shop_zt_danhao);
            mtv_time = itemView.findViewById(R.id.shop_zt_time);
            mtv_tishi = itemView.findViewById(R.id.shop_zt_ts);
            mtv_num = itemView.findViewById(R.id.shop_zt_num);
            mtv_guige = itemView.findViewById(R.id.shop_zt_guige);
            mtv_zongjia = itemView.findViewById(R.id.shop_zt_zongjia);
            mtv_phone = itemView.findViewById(R.id.shop_zt_phone);
            mtv_xdtime = itemView.findViewById(R.id.shop_zt_xdtime);
            mtv_rightTs = itemView.findViewById(R.id.shop_zt_rightts);
            mConstraintLayout = itemView.findViewById(R.id.ship_zt_cons);
            mtv_tv_guige = itemView.findViewById(R.id.textView75);
            mtv_tv_xdTime = itemView.findViewById(R.id.textView90);
            mtv_what=itemView.findViewById(R.id.shop_zt_what);
        }
    }

    public interface OnItemClickListener {
        void OnItemClickListener(int position);
    }
}
