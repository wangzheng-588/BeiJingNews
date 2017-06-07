package com.wz.beijingnews.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.wz.beijingnews.bean.PhotosNewsBean;
import com.wz.beijingnews.common.Constacts;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by wz on 17-6-7.
 */

public class PhotoViewAdapter extends PagerAdapter {

    private Context mContext;
    ArrayList<PhotosNewsBean> mPhotosNewsBeen;

    public PhotoViewAdapter(Context context,ArrayList<PhotosNewsBean> photosNewsBeen) {
        mContext = context;
        mPhotosNewsBeen = photosNewsBeen;
    }

    @Override
    public int getCount() {
        return mPhotosNewsBeen == null?0:mPhotosNewsBeen.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(mContext);
        container.addView(photoView);
        Glide.with(mContext).load(Constacts.BASE_URL+mPhotosNewsBeen.get(position).getListimage())
                .into(photoView);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
