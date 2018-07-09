package com.superc.shangjiaban.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.bean.DateUtil;
import com.superc.shangjiaban.bean.FeedBackBean;
import com.superc.shangjiaban.others.CustomListener;

import java.util.List;

/**
 * Created by user on 2018/3/5.
 */

public class FeedBackAdapter extends RecyclerView.Adapter<FeedBackAdapter.ViewHolder> {
    private Context mContext;
    private List<FeedBackBean.DateBean> mClassList;
    private LayoutInflater mInflater;
    private CustomListener mCustomListener;

    public FeedBackAdapter(Context context, List<FeedBackBean.DateBean> classList) {
        mContext = context;
        mClassList = classList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=mInflater.inflate(R.layout.layout_feedback,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    public void setCustomListener(CustomListener customListener) {
        mCustomListener = customListener;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        FeedBackBean.DateBean exampleClass=mClassList.get(position);
        vh.mtv_id.setText("ID"+exampleClass.getId());
        vh.mtv_city.setText(exampleClass.getCommunityId());
        vh.mtv_time.setText(DateUtil.getStrTime(exampleClass.getAddTime()));
        vh.mtv_name.setText(" "+exampleClass.getNickname());
        vh.mtv_phone.setText(exampleClass.getPhone());
        vh.mtv_content.setText(exampleClass.getContent());
        vh.mtv_xiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCustomListener!=null){
                    mCustomListener.CustomeListener(R.id.feedback_xiangqing,position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mClassList == null ? 0 : mClassList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mtv_id;
        private TextView mtv_city;
        private TextView mtv_name;
        private TextView mtv_time;
        private TextView mtv_phone;
        private TextView mtv_content;
        private TextView mtv_xiangqing;

        public ViewHolder(View itemView) {
            super(itemView);
            mtv_id=itemView.findViewById(R.id.village_all_id);
            mtv_city=itemView.findViewById(R.id.village_all_city);
            mtv_name=itemView.findViewById(R.id.feedback_username);
            mtv_time=itemView.findViewById(R.id.village_all_sheng);
            mtv_phone=itemView.findViewById(R.id.village_all_shi);
            mtv_content=itemView.findViewById(R.id.feedback_content);
            mtv_xiangqing=itemView.findViewById(R.id.feedback_xiangqing);
        }
    }


}
