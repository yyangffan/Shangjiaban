package com.superc.shangjiaban.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.bean.UserZhuanZhangBean;
import com.superc.shangjiaban.others.CustomListener;
import com.superc.shangjiaban.utils.ShareUtil;

import java.util.List;

/**
 * Created by user on 2018/3/1.
 */

public class ZhuanZhangAdapter extends RecyclerView.Adapter<ZhuanZhangAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<UserZhuanZhangBean.DateBean> mClassList;
    private OnViewClickListener mOnViewClickListener;
    private CustomListener mCustomListener;
    private String role_id="";

    public ZhuanZhangAdapter(Context context, List<UserZhuanZhangBean.DateBean> mClassList) {
        mContext = context;
        this.mClassList = mClassList;
        mInflater = LayoutInflater.from(context);
        role_id= (String) ShareUtil.getInstance(context).get("role_id","");
    }

    public void setOnViewClickListener(OnViewClickListener onViewClickListener) {
        mOnViewClickListener = onViewClickListener;
    }

    public void setCustomListener(CustomListener customListener) {
        mCustomListener = customListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.layout_zhuanzhang, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        UserZhuanZhangBean.DateBean exampleClass = mClassList.get(position);
        setContent(vh.mtv_name,exampleClass.getUserName());
        setContent(vh.mtv_number,exampleClass.getAdminId());
        setContent(vh.mtv_baiyin,exampleClass.getMoney());
        setContent(vh.mtv_huangjin,exampleClass.getAccount());
        setContent(vh.mtv_phone,exampleClass.getLinkman());
        setContent(vh.mtv_time,exampleClass.getPhone());
        vh.mtv_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnViewClickListener!=null){
                    mOnViewClickListener.OnViewClickListener(R.id.userlist_sendMsg,position);
                }
            }
        });
        vh.mtv_zhuanz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCustomListener!=null){
                    mCustomListener.CustomeListener(R.id.zhuanzhang_tv,position);
                }
            }
        });
        if(role_id.equals("1")){
            vh.mtv_zhuanz.setVisibility(View.VISIBLE);
        }else {
            vh.mtv_zhuanz.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mClassList == null ? 0: mClassList.size() ;
    }

    public void setContent(View v,String content) {
        if(v instanceof TextView){
            ((TextView) v).setText(content==null?"":content);
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
        private TextView mtv_zhuanz;

        public ViewHolder(View itemView) {
            super(itemView);
            mtv_number=itemView.findViewById(R.id.userlist_number);
            mtv_name=itemView.findViewById(R.id.userlist_name);
            mtv_msg=itemView.findViewById(R.id.userlist_sendMsg);
            mtv_baiyin=itemView.findViewById(R.id.userlist_baiyin_jia);
            mtv_huangjin=itemView.findViewById(R.id.userlist_huangjin_jia);
            mtv_phone=itemView.findViewById(R.id.userlist_phone);
            mtv_time=itemView.findViewById(R.id.userlist_time);
            mtv_zhuanz=itemView.findViewById(R.id.zhuanzhang_tv);
        }
    }

    public interface OnViewClickListener{
        void OnViewClickListener(int view_id, int position);
    }

}
