package com.superc.shangjiaban.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.superc.shangjiaban.R;

/**
 * Created by user on 2018/3/6.
 */

public class PopupLvAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mStringList;
    private LayoutInflater mInflater;

    public PopupLvAdapter(Context context, String[] stringList) {
        mContext = context;
        mStringList = stringList;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mStringList==null?0:mStringList.length;
    }

    @Override
    public Object getItem(int i) {
        return mStringList==null?null:mStringList[i];
    }

    @Override
    public long getItemId(int i) {
        return mStringList==null?0:i;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        ViewHolder vh=null;
        if(contentView==null){
            vh=new ViewHolder();
            contentView=mInflater.inflate(R.layout.popup_lv_item,viewGroup,false);
            vh.mtv_content=contentView.findViewById(R.id.popup_lv_content);
            contentView.setTag(vh);
        }else {
            vh= (ViewHolder) contentView.getTag();
        }
        vh.mtv_content.setText(mStringList[position]);
        return contentView;
    }

    class ViewHolder{
        private TextView mtv_content;
    }

}
