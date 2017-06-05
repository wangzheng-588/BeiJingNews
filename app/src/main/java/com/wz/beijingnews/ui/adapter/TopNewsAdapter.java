package com.wz.beijingnews.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wz.beijingnews.bean.TopNewsBean;
import com.wz.beijingnews.common.Constacts;

import java.util.List;

/**
 * Created by wz on 17-6-5.
 */

public class TopNewsAdapter extends PagerAdapter {

    private final Context mContext;
    private List<com.wz.beijingnews.bean.TopNewsBean> mTopNewsBean;

    public TopNewsAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mTopNewsBean.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        String topimage = Constacts.BASE_URL+(mTopNewsBean.get(position).getTopimage()).substring(1);
        Glide.with(mContext).load(topimage)
                .into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setData(List<TopNewsBean> value) {
        this.mTopNewsBean = value;
    }


    public void setTitle(TextView view, int position){
        view.setText(mTopNewsBean.get(position).getTitle());
    }
}
