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
import com.superc.shangjiaban.bean.UserGodListBean;

import java.util.List;

/**
 * Created by user on 2018/3/5.
 */

public class GodListAdapter extends RecyclerView.Adapter<GodListAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<UserGodListBean.UsersBean> mClassList;
    private OnViewClickListener mOnViewClickListener;
    private OnItemClickListener mOnItemClickListener;
    private String role_id = "";

    public GodListAdapter(Context context, List<UserGodListBean.UsersBean> mClassList, String role_id) {
        mContext = context;
        this.mClassList = mClassList;
        mInflater = LayoutInflater.from(context);
        this.role_id = role_id;
    }

    public void setOnViewClickListener(OnViewClickListener onViewClickListener) {
        mOnViewClickListener = onViewClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setClassList(List<UserGodListBean.UsersBean> classList) {
        mClassList = classList;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.layout_godlist, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        UserGodListBean.UsersBean bean = mClassList.get(position);
        setContent(vh.mtv_name, bean.getNickname());
        setContent(vh.mtv_number, bean.getId());
        setContent(vh.mtv_baiyin, bean.getMoney());
        setContent(vh.mtv_huangjin, bean.getXiaoquname());
        setContent(vh.mtv_phone, bean.getPhone());
        setContent(vh.mtv_time, DateUtil.getStrTime(bean.getAddTime()));
        String state = bean.getState();
        String payMoney = bean.getPayMoney();
        if (payMoney.equals("1")) {
            vh.mtv_dakuan.setVisibility(View.VISIBLE);
            vh.mtv_dakuan.setText("未打款");
        } else if (payMoney.equals("2")) {
            vh.mtv_dakuan.setVisibility(View.VISIBLE);
            vh.mtv_dakuan.setText("已打款");
        }
        if (state.equals("2")) {
            vh.mtv_msg.setText("审批通过");
            vh.mtv_tongguo.setVisibility(View.GONE);
            vh.mimgv.setVisibility(View.GONE);
        } else {
            vh.mtv_msg.setText("待审批");
            vh.mtv_tongguo.setVisibility(View.VISIBLE);
            vh.mtv_dakuan.setVisibility(View.GONE);
            vh.mimgv.setVisibility(View.VISIBLE);
        }
        vh.mtv_tongguo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnViewClickListener != null) {
                    mOnViewClickListener.OnViewClickListener(R.id.god_istongguo, position);
                }
            }
        });
        if (role_id != null && role_id.equals("1")) {
            if (state.equals("2")) {
                vh.mtv_tongguo.setVisibility(View.GONE);
            } else {
                vh.mtv_tongguo.setVisibility(View.VISIBLE);
            }
        }
        boolean c = bean.isC();
        if (c) {
            vh.mimgv.setImageResource(R.drawable.dg1);
        } else {
            vh.mimgv.setImageResource(R.drawable.dg2);
        }
        vh.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.OnItemClickListener(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mClassList == null ? 0 : mClassList.size();
    }

    public void setContent(View v, String content) {
        if (v instanceof TextView) {
            ((TextView) v).setText(content == null ? "" : content);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mtv_number;
        private TextView mtv_name;
        private TextView mtv_msg;
        private TextView mtv_baiyin;
        private TextView mtv_huangjin;
        private TextView mtv_phone;
        private TextView mtv_time;
        private TextView mtv_dakuan;
        private TextView mtv_tongguo;
        private ImageView mimgv;
        private ConstraintLayout mConstraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mtv_number = itemView.findViewById(R.id.userlist_number);
            mtv_name = itemView.findViewById(R.id.userlist_name);
            mtv_msg = itemView.findViewById(R.id.userlist_sendMsg);
            mtv_baiyin = itemView.findViewById(R.id.userlist_baiyin_jia);
            mtv_huangjin = itemView.findViewById(R.id.userlist_huangjin_jia);
            mtv_phone = itemView.findViewById(R.id.userlist_phone);
            mtv_time = itemView.findViewById(R.id.userlist_time);
            mtv_dakuan = itemView.findViewById(R.id.god_isdakuan);
            mtv_tongguo = itemView.findViewById(R.id.god_istongguo);
            mimgv = itemView.findViewById(R.id.god_imgv);
            mConstraintLayout = itemView.findViewById(R.id.constran);
        }
    }

    public interface OnViewClickListener {
        void OnViewClickListener(int view_id, int position);
    }

    public interface OnItemClickListener {
        void OnItemClickListener(int position);
    }

}
