package com.superc.shangjiaban.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.bean.PropertyYjBean;
import com.superc.shangjiaban.others.CustomListener;
import com.superc.shangjiaban.utils.ShareUtil;

import java.util.List;

/**
 * 物业管理--佣金提现申请
 */

public class WuYeJiLuAdapter extends RecyclerView.Adapter<WuYeJiLuAdapter.ViewHolder> {
    private Context mContext;
    private List<PropertyYjBean.DateBean> mClassList;
    private LayoutInflater mInflater;
    private CustomListener mCustomListener;
    private String role_id="";

    public WuYeJiLuAdapter(Context context, List<PropertyYjBean.DateBean> classList) {
        mContext = context;
        mClassList = classList;
        mInflater = LayoutInflater.from(mContext);
        role_id= (String) ShareUtil.getInstance(mContext).get("role_id","");
    }

    public void setCustomListener(CustomListener customListener) {
        mCustomListener = customListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.layout_wy_apply, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        PropertyYjBean.DateBean exampleClass = mClassList.get(position);
        vh.mtv_id.setText("ID"+exampleClass.getAdminId()+"");
        vh.mtv_content.setText(exampleClass.getUserName());
        vh.mtv_name.setText(exampleClass.getMoney());
        vh.mtv_phone.setText(exampleClass.getPhone());
        vh.mtv_youxiang.setText(exampleClass.getChannel());
        vh.mtv_time.setText(exampleClass.getAccount());
        vh.mtv_chakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCustomListener != null) {
                    mCustomListener.CustomeListener(R.id.wy_userlist_chakan, position);
                }
            }
        });
        vh.mtv_money.setText("待打款");
        vh.mtv_chakan.setText("已打款");
        if(role_id.equals("1")){
            vh.mtv_chakan.setVisibility(View.VISIBLE);
        }else {
            vh.mtv_chakan.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return mClassList == null ? 0 : mClassList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mtv_id;
        private TextView mtv_content;
        private TextView mtv_money;
        private TextView mtv_name;
        private TextView mtv_phone;
        private TextView mtv_youxiang;
        private TextView mtv_time;
        private TextView mtv_chakan;

        public ViewHolder(View itemView) {
            super(itemView);
            mtv_id = itemView.findViewById(R.id.wy_userlist_id);
            mtv_content = itemView.findViewById(R.id.wy_userlist_city);
            mtv_money = itemView.findViewById(R.id.wy_userlist_yongjin);
            mtv_name = itemView.findViewById(R.id.wy_userlist_name);
            mtv_phone = itemView.findViewById(R.id.wy_userlist_phone);
            mtv_youxiang = itemView.findViewById(R.id.wy_userlist_youxiang);
            mtv_time = itemView.findViewById(R.id.wy_userlist_time);
            mtv_chakan = itemView.findViewById(R.id.wy_userlist_chakan);

        }
    }

}
