package com.superc.shangjiaban.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.bean.VillageAllBean;
import com.superc.shangjiaban.utils.ShareUtil;
import com.superc.shangjiaban.utils.ToastUtil;

import java.util.List;

/**
 * Created by user on 2018/3/5.
 */

public class VillageAllAdapter extends RecyclerView.Adapter<VillageAllAdapter.ViewHolder>{
    private Context mContext;
    private List<VillageAllBean.UsersBean> mClassList;
    private LayoutInflater mInflater;
    private OnViewClickListener mOnViewClickListener;
    private String uid="";
    private String role_id="";


    public VillageAllAdapter(Context context, List<VillageAllBean.UsersBean> classList,String uid) {
        mContext = context;
        mClassList = classList;
        this.uid=uid;
        mInflater=LayoutInflater.from(mContext);
        role_id= (String) ShareUtil.getInstance(mContext).get("role_id","");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=mInflater.inflate(R.layout.layout_village_all,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    public void setOnViewClickListener(OnViewClickListener onViewClickListener) {
        mOnViewClickListener = onViewClickListener;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        VillageAllBean.UsersBean exampleClass=mClassList.get(position);
        final String wuid = exampleClass.getWuid();
        vh.mtv_name.setText("ID"+exampleClass.getId());
        vh.mtv_city.setText(exampleClass.getCommunityId());
        vh.mtv_sheng.setText(exampleClass.getShengname());
        vh.mtv_shi.setText(exampleClass.getShiname());
        vh.mtv_xian.setText(exampleClass.getQuname());

        vh.mtv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnViewClickListener!=null){
                    mOnViewClickListener.OnViewClickListener(R.id.village_all_look,position);
                }
            }
        });
        vh.mimgv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(role_id.equals("1")||role_id.equals("2")||role_id.equals("3")){
                    if(uid.equals(wuid)){
                        if(mOnViewClickListener!=null){
                            mOnViewClickListener.OnViewClickListener(R.id.village_all_delete,position);
                        }
                    }else {
                        ToastUtil.showToast(mContext,"不是自己创建的，不可进行操作");
                    }
                }

            }
        });
        if(role_id.equals("1")||role_id.equals("2")||role_id.equals("3")){
            vh.mtv_edit.setVisibility(View.VISIBLE);
            vh.mimgv_delete.setVisibility(View.VISIBLE);
        }else {
            vh.mtv_edit.setVisibility(View.INVISIBLE);
            vh.mimgv_delete.setVisibility(View.INVISIBLE);
        }

//        if(!uid.equals("1")){
//            if(!uid.equals(wuid)){
//                vh.mtv_edit.setVisibility(View.INVISIBLE);
//                vh.mimgv_delete.setVisibility(View.INVISIBLE);
//            }
//        }
    }

    @Override
    public int getItemCount() {
        return mClassList==null?0:mClassList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mtv_name;
        private TextView mtv_city;
        private TextView mtv_sheng;
        private TextView mtv_shi;
        private TextView mtv_xian;
        private TextView mtv_edit;
        private ImageView mimgv_delete;


        public ViewHolder(View itemView) {
            super(itemView);
            mtv_name=itemView.findViewById(R.id.village_all_id);
            mtv_city=itemView.findViewById(R.id.village_all_city);
            mtv_sheng=itemView.findViewById(R.id.village_all_sheng);
            mtv_shi=itemView.findViewById(R.id.village_all_shi);
            mtv_xian=itemView.findViewById(R.id.village_all_xian);
            mtv_edit=itemView.findViewById(R.id.village_all_look);
            mimgv_delete=itemView.findViewById(R.id.village_all_delete);
        }
    }

    public interface OnViewClickListener{
        void OnViewClickListener(int view_id,int postion);
    }
}
