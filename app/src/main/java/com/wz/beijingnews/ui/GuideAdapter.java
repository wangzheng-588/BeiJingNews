package com.wz.beijingnews.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by wz on 17-6-2.
 */

public class GuideAdapter extends PagerAdapter {

    private final int[] mImageIDs;
    private final Context mContext;

    public GuideAdapter(Context context, int[] imageIDs) {
        this.mContext = context;
        this.mImageIDs = imageIDs;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setBackgroundResource(mImageIDs[position]);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mImageIDs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
