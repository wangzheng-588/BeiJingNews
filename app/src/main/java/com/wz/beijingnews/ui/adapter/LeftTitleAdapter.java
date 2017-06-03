package com.wz.beijingnews.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wz.beijingnews.R;

import java.util.List;

/**
 * Created by wz on 17-6-2.
 */

public class LeftTitleAdapter extends BaseAdapter {

    private final List<String> mStrings;
    private final Context mContext;
    private int prePosition;

    public LeftTitleAdapter(Context context, List<String> strings) {
        this.mContext = context;
        this.mStrings = strings;
    }

    @Override
    public int getCount() {
        return mStrings==null?0:mStrings.size();
    }

    @Override
    public String getItem(int position) {
        return mStrings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView==null){
            convertView = View.inflate(mContext, R.layout.list_item_left_title,null);
            holder = new ViewHolder();
            holder.mTvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTvTitle.setText(mStrings.get(position));

        if (prePosition==position){
            holder.mTvTitle.setEnabled(true);
        } else {
            holder.mTvTitle.setEnabled(false);
        }

        return convertView;
    }

    public void setPosition(int prePosition) {
        this.prePosition = prePosition;
    }

    static class ViewHolder{
         TextView mTvTitle;
    }
}
