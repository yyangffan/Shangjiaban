package com.superc.shangjiaban.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.superc.shangjiaban.R;

import java.util.List;
import java.util.Map;

/**
 * 订单搜索的PopUpwindow的子界面
 */

public class ShopDdAdapter extends BaseAdapter{
    private Context mContext;
    private List<Map<String,String>> mMapList;
    private LayoutInflater mInflater;

    public ShopDdAdapter(Context context, List<Map<String, String>> mapList) {
        mContext = context;
        mMapList = mapList;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mMapList==null?0:mMapList.size();
    }

    @Override
    public Object getItem(int i) {
        return mMapList==null?null:mMapList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mMapList==null?0:i;
    }

    @Override
    public View getView(int i, View contentview, ViewGroup viewGroup) {
        ViewHolder vh=null;
        if(contentview==null){
            vh=new ViewHolder();
            contentview=mInflater.inflate(R.layout.layout_pop_item,viewGroup,false);
            vh.mTextView=contentview.findViewById(R.id.pop_item_tv);
            contentview.setTag(vh);
        }else {
            vh= (ViewHolder) contentview.getTag();
        }
        Map<String,String> map=mMapList.get(i);
        vh.mTextView.setText(map.get("content"));
        return contentview;
    }
    class ViewHolder {
        private TextView mTextView;
    }

}
