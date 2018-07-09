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
import com.superc.shangjiaban.bean.ShopAllListBean;
import com.superc.shangjiaban.others.CustomListener;

import java.util.List;

/**
 * Created by user on 2018/3/7.
 */

public class ShopAllAdapter extends RecyclerView.Adapter<ShopAllAdapter.ViewHolder> {
    private Context mContext;
    private List<ShopAllListBean.DateBean> mClassList;
    private LayoutInflater mInflater;
    private CustomListener mCustomListener;

    public ShopAllAdapter(Context context, List<ShopAllListBean.DateBean> classList) {
        mContext = context;
        mClassList = classList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.layout_shop_all, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void setCustomListener(CustomListener customListener) {
        mCustomListener = customListener;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        ShopAllListBean.DateBean exampleClass = mClassList.get(position);
        vh.mtv_name.setText(exampleClass.getConsignee());
        vh.mtv_danhao.setText(exampleClass.getOrderSn());
        vh.mtv_time.setText(DateUtil.getStrTime(exampleClass.getAddTime()));
        vh.mtv_money.setText("¥"+exampleClass.getTotalAmount());
        vh.mtv_phone.setText(exampleClass.getMobile());
        vh.mtv_chakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCustomListener != null) {
                    mCustomListener.CustomeListener(R.id.shop_all_chakan, position);
                }
            }
        });
        vh.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCustomListener != null) {
                    mCustomListener.CustomeListener(R.id.shop_all_chakan, position);
                }
            }
        });
        String orderStatus = exampleClass.getOrderStatus();
        switch (orderStatus){
            case "0":vh.mtv_orderStaus.setText("待确认");break;
            case "1":vh.mtv_orderStaus.setText("已确认");break;
            case "2":vh.mtv_orderStaus.setText("已收货");break;
            case "3":vh.mtv_orderStaus.setText("已取消");break;
            case "4":vh.mtv_orderStaus.setText("已完成");break;
            case "5":vh.mtv_orderStaus.setText("已作废");break;
            case "6":vh.mtv_orderStaus.setText("退货中");break;
            case "7":vh.mtv_orderStaus.setText("已退货");break;
            case "8":vh.mtv_orderStaus.setText("同意退货");break;
            case "9":vh.mtv_orderStaus.setText("拒绝退货");break;
            case "10":vh.mtv_orderStaus.setText("删除");break;
        }

        String payStatus = exampleClass.getPayStatus();
        switch (payStatus){
            case "0":  vh.mtv_zfstate.setText("未支付");break;
            case "1":  vh.mtv_zfstate.setText("已支付");break;
            case "2":  vh.mtv_zfstate.setText("部分支付");break;
        }
        String shippingStatus = exampleClass.getShippingStatus();
        switch (shippingStatus){
            case "0" : vh.mtv_fhstate.setText("未发货");break;
            case "1" : vh.mtv_fhstate.setText("已发货");break;
            case "2" : vh.mtv_fhstate.setText("部分发货");break;
            case "3" : vh.mtv_fhstate.setText("未自提");break;
            case "4" : vh.mtv_fhstate.setText("未配送");break;
        }
        String orderPromType = exampleClass.getOrderPromType();
        switch (orderPromType){
            case "0": vh.mtv_lx.setText("默认");break;
            case "1": vh.mtv_lx.setText("抢购");break;
            case "2": vh.mtv_lx.setText("团购");break;
            case "3": vh.mtv_lx.setText("优惠");break;
            case "4": vh.mtv_lx.setText("预售");break;
            case "5": vh.mtv_lx.setText("虚拟");break;
            case "6": vh.mtv_lx.setText("超市");break;
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
        private TextView mtv_money;
        private TextView mtv_zfstate;
        private TextView mtv_fhstate;
        private TextView mtv_lx;
        private TextView mtv_phone;
        private TextView mtv_chakan;
        private TextView mtv_orderStaus;
        private ConstraintLayout mConstraintLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            mtv_name = itemView.findViewById(R.id.shop_all_name);
            mtv_danhao = itemView.findViewById(R.id.shop_all_danhao);
            mtv_time = itemView.findViewById(R.id.shop_all_time);
            mtv_money = itemView.findViewById(R.id.shop_all_money);
            mtv_zfstate = itemView.findViewById(R.id.shop_all_zhifuzt);
            mtv_fhstate = itemView.findViewById(R.id.shop_all_fhzt);
            mtv_lx = itemView.findViewById(R.id.shop_all_lx);
            mtv_phone = itemView.findViewById(R.id.shop_all_phone);
            mtv_chakan = itemView.findViewById(R.id.shop_all_chakan);
            mtv_orderStaus=itemView.findViewById(R.id.textView45);
            mConstraintLayout=itemView.findViewById(R.id.shop_all_cons);


        }
    }
}
