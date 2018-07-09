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
import com.superc.shangjiaban.bean.UserListBean;

import java.util.List;

/**
 * Created by user on 2018/3/1.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<UserListBean.UsersBean> mClassList;
    private OnViewClickListener mOnViewClickListener;
    private OnItemClickListener mOnItemClickListener;

    public UserListAdapter(Context context, List<UserListBean.UsersBean> mClassList) {
        mContext = context;
        this.mClassList = mClassList;
        mInflater = LayoutInflater.from(context);
    }

    public void setOnViewClickListener(OnViewClickListener onViewClickListener) {
        mOnViewClickListener = onViewClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setClassList(List<UserListBean.UsersBean> classList) {
        mClassList = classList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.layout_userlist, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        UserListBean.UsersBean exampleClass = mClassList.get(position);
        setContent(vh.mtv_name, exampleClass.getNickname());
        setContent(vh.mtv_number, exampleClass.getUserid());
        setContent(vh.mtv_baiyin, exampleClass.getUserMoney());
        setContent(vh.mtv_huangjin, exampleClass.getUserGold());
        setContent(vh.mtv_phone, exampleClass.getPhone());
        setContent(vh.mtv_time, exampleClass.getInsertdate() == null ? "" : DateUtil.getStrTime(exampleClass.getInsertdate()));
        vh.mtv_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnViewClickListener != null) {
                    mOnViewClickListener.OnViewClickListener(R.id.userlist_sendMsg, position);
                }
            }
        });
        boolean c = exampleClass.isC();
        if (c) {
            vh.mimgv.setImageResource(R.drawable.dg1);
        } else {
            vh.mimgv.setImageResource(R.drawable.dg2);
        }
        vh.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener!=null){
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
        private ConstraintLayout mConstraintLayout;
        private TextView mtv_number;
        private TextView mtv_name;
        private TextView mtv_msg;
        private TextView mtv_baiyin;
        private TextView mtv_huangjin;
        private TextView mtv_phone;
        private TextView mtv_time;
        private ImageView mimgv;

        public ViewHolder(View itemView) {
            super(itemView);
            mConstraintLayout=itemView.findViewById(R.id.uselist_cons);
            mtv_number = itemView.findViewById(R.id.userlist_number);
            mtv_name = itemView.findViewById(R.id.userlist_name);
            mtv_msg = itemView.findViewById(R.id.userlist_sendMsg);
            mtv_baiyin = itemView.findViewById(R.id.userlist_baiyin_jia);
            mtv_huangjin = itemView.findViewById(R.id.userlist_huangjin_jia);
            mtv_phone = itemView.findViewById(R.id.userlist_phone);
            mtv_time = itemView.findViewById(R.id.userlist_time);
            mimgv = itemView.findViewById(R.id.userlist_duigou);
        }
    }

    public interface OnViewClickListener {
        void OnViewClickListener(int view_id, int position);
    }

    public interface  OnItemClickListener{
        void OnItemClickListener(int position);
    }

}
