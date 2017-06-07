package com.wz.beijingnews.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.wz.beijingnews.R;
import com.wz.beijingnews.bean.PhotosNewsBean;
import com.wz.beijingnews.ui.adapter.PhotoViewAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by wz on 17-6-7.
 */

public class PhotoViewActivity extends BaseActivity {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private ArrayList<PhotosNewsBean> mMPhotosNewsBeen;
    private int mPosition;

    @Override
    protected int setLayoutRedID() {
        return R.layout.activity_photo_view;
    }

    @Override
    protected void init() {
        Bundle bundle = getIntent().getExtras();
        mMPhotosNewsBeen = (ArrayList<PhotosNewsBean>) bundle.getSerializable("photos");
        mPosition = bundle.getInt("position");

        PhotoViewAdapter adapter = new PhotoViewAdapter(this, mMPhotosNewsBeen);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(mPosition);

    }

}
