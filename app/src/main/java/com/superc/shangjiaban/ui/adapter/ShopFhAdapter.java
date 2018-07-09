package com.superc.shangjiaban.ui.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.bean.DateUtil;
import com.superc.shangjiaban.bean.ShopFhBean;
import com.superc.shangjiaban.others.CustomListener;
import com.superc.shangjiaban.utils.ShareUtil;

import java.util.List;

/**
 * Created by user on 2018/3/7.
 */

public class ShopFhAdapter extends RecyclerView.Adapter<ShopFhAdapter.ViewHolder> {
    private Context mContext;
    private List<ShopFhBean.DateBean> mClassList;
    private LayoutInflater mInflater;
    private CustomListener mCustomListener;
    private OnItemClickListener mOnItemClickListener;
    private boolean showImg = false;
    private String role_id="";
    private OnDuiGouClickListener mOnDuiGouClickListener;

    public ShopFhAdapter(Context context, List<ShopFhBean.DateBean> classList) {
        mContext = context;
        mClassList = classList;
        mInflater = LayoutInflater.from(mContext);
        role_id= (String) ShareUtil.getInstance(mContext).get("role_id","");
    }

    public void setShowImg(boolean showImg) {
        this.showImg = showImg;
    }

    public void setCustomListener(CustomListener customListener) {
        mCustomListener = customListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnDuiGouClickListener(OnDuiGouClickListener onDuiGouClickListener) {
        mOnDuiGouClickListener = onDuiGouClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.layout_shop_fh, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        ShopFhBean.DateBean exampleClass = mClassList.get(position);
        vh.mtv_name.setText(exampleClass.getConsignee());
        vh.mtv_danhao.setText(exampleClass.getOrderSn());
        vh.mtv_time.setText(DateUtil.getStrTime(exampleClass.getAddTime()));
        vh.mtv_num.setText(exampleClass.getGoodsNum());
        vh.mtv_money.setText(exampleClass.getGoodsPrice());
        vh.mtv_phone.setText(exampleClass.getMobile());
        vh.mtv_zftime.setText(DateUtil.getStrTime(exampleClass.getPayTime()));
        vh.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.OnItemClickListener(position);
                }
            }
        });
        vh.mtv_gofh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCustomListener != null) {
                    mCustomListener.CustomeListener(R.id.shop_fh_imgv, position);
                }
            }
        });
        String status = exampleClass.getShippingStatus();
        switch (status) {
            case "0":
                vh.mtv_fhts.setText("待发货");
                break;
            case "1":
                vh.mtv_fhts.setText("已发货");
                break;
            case "2":
                vh.mtv_fhts.setText("部分发货");
                break;
        }
        boolean c = exampleClass.isC();
        if (showImg) {
            vh.mimgv_duigou.setVisibility(View.VISIBLE);
            if (c) {
                vh.mimgv_duigou.setImageResource(R.drawable.dg1);
            } else {
//                vh.mimgv_duigou.setImageResource(R.drawable.dg2);
                vh.mimgv_duigou.setImageResource(R.drawable.dg1);
            }
        } else {
            vh.mimgv_duigou.setVisibility(View.GONE);
        }
        if(role_id.equals("1")){
            vh.mtv_gofh.setVisibility(View.VISIBLE);
        }else {
            vh.mtv_gofh.setVisibility(View.INVISIBLE);
        }
        vh.mimgv_duigou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnDuiGouClickListener!=null){
                    mOnDuiGouClickListener.OnDuiGouClickListener(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mClassList == null ? 0 : mClassList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mtv_name;
        private TextView mtv_danhao;
        private TextView mtv_time;
        private TextView mtv_num;
        private TextView mtv_money;
        private TextView mtv_phone;
        private TextView mtv_zftime;
        private TextView mtv_gofh;
        private ImageView mimgv_duigou;
        private TextView mtv_fhts;
        private ConstraintLayout mConstraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mtv_name = itemView.findViewById(R.id.shop_fh_name);
            mtv_danhao = itemView.findViewById(R.id.shop_fh_danhao);
            mtv_time = itemView.findViewById(R.id.shop_fh_time);
            mtv_num = itemView.findViewById(R.id.shop_fh_num);
            mtv_money = itemView.findViewById(R.id.shop_fh_money);
            mtv_phone = itemView.findViewById(R.id.shop_fh_phone);
            mtv_zftime = itemView.findViewById(R.id.shop_fh_zftime);
            mtv_gofh = itemView.findViewById(R.id.shop_fh_gofh);
            mimgv_duigou = itemView.findViewById(R.id.shop_fh_imgv);
            mtv_fhts = itemView.findViewById(R.id.shop_fh_fh);
            mConstraintLayout = itemView.findViewById(R.id.shop_fh_cons);
        }
    }

    public interface OnItemClickListener {
        void OnItemClickListener(int position);
    }

    public interface OnDuiGouClickListener{
        void OnDuiGouClickListener(int position);
    }
}
