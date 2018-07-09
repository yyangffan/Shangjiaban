package com.superc.shangjiaban.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.superc.shangjiaban.R;
import com.superc.shangjiaban.base.Constant;
import com.superc.shangjiaban.bean.OrderDetailBean;

import java.util.List;

/**
 * Created by user on 2018/3/13.
 */

public class OrderDetailLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<OrderDetailBean.CountBean> mClassList;
    private LayoutInflater mInflater;

    public OrderDetailLvAdapter(Context context,List<OrderDetailBean.CountBean> classList) {
        mContext = context;
        mClassList = classList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mClassList == null ? 0 : mClassList.size();
    }

    @Override
    public Object getItem(int i) {
        return mClassList == null ? null : mClassList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mClassList == null ? 0 : i;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        ViewHolder vh = null;
        if (contentView == null) {
            vh = new ViewHolder();
            contentView = mInflater.inflate(R.layout.layout_order_item, viewGroup, false);
            vh.mtv_name = contentView.findViewById(R.id.order_item_name);
            vh.mtv_style = contentView.findViewById(R.id.order_item_style);
            vh.mtv_money = contentView.findViewById(R.id.order_item_money);
            vh.mtv_num = contentView.findViewById(R.id.order_item_num);
            vh.mimgv=contentView.findViewById(R.id.imageView7);
            contentView.setTag(vh);
        }else {
            vh= (ViewHolder) contentView.getTag();
        }
        OrderDetailBean.CountBean map = mClassList.get(position);
        vh.mtv_name.setText(map.getGoodsName());
        vh.mtv_style.setText(map.getSpecKeyName());
        vh.mtv_money.setText(map.getShopPrice());
        vh.mtv_num.setText("x"+map.getGoodsNum());
        Glide.with(mContext).load(Constant.REQUESTURL_IMAGE+map.getOriginalImg()).into(vh.mimgv);

        return contentView;
    }

    class ViewHolder {
        private TextView mtv_name;
        private TextView mtv_style;
        private TextView mtv_money;
        private TextView mtv_num;
        private ImageView mimgv;
    }

}
