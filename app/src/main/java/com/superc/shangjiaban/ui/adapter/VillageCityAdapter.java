package com.superc.shangjiaban.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.bean.VillageCityBean;

import java.util.List;

/**
 * Created by user on 2018/3/22.
 */

public class VillageCityAdapter extends BaseAdapter {
    private Context mContext;
    private List<VillageCityBean.DataBean.AreaBean> mVillageAllBeen;
    private LayoutInflater mInflater;

    public VillageCityAdapter(Context context, List<VillageCityBean.DataBean.AreaBean> villageAllBeen) {
        mContext = context;
        mVillageAllBeen = villageAllBeen;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mVillageAllBeen==null?0:mVillageAllBeen.size();
    }

    @Override
    public Object getItem(int i) {
        return mVillageAllBeen==null?null:mVillageAllBeen.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mVillageAllBeen==null?0:i;
    }

    @Override
    public View getView(int i, View contentView, ViewGroup viewGroup) {
        ViewHolder vh=null;
        if(contentView==null){
            vh=new ViewHolder();
            contentView=mInflater.inflate(R.layout.layout_pop_item,viewGroup,false);
            vh.mtv_title=contentView.findViewById(R.id.pop_item_tv);
            contentView.setTag(vh);
        }else {
            vh= (ViewHolder) contentView.getTag();
        }
        VillageCityBean.DataBean.AreaBean areaBean = mVillageAllBeen.get(i);
        vh.mtv_title.setText(areaBean.getName());


        return contentView;
    }

    class ViewHolder{
        private TextView mtv_title;
    }
}
