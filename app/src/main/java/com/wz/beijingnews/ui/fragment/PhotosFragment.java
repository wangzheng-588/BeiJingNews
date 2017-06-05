package com.wz.beijingnews.ui.fragment;

import android.util.Log;

import com.wz.beijingnews.R;
import com.wz.beijingnews.bean.PhotosDataBean;
import com.wz.beijingnews.bean.PhotosNewsBean;
import com.wz.beijingnews.di.component.AppComponent;
import com.wz.beijingnews.di.component.DaggerPhotosComponent;
import com.wz.beijingnews.di.module.PhotosModule;
import com.wz.beijingnews.presenter.PhotosPresenter;
import com.wz.beijingnews.presenter.contract.PhotoContract;

import javax.inject.Inject;

/**
 * Created by wz on 17-6-2.
 */

public class PhotosFragment extends ProgressFragment implements PhotoContract.View{

    @Inject
    PhotosPresenter mPresenter;


    @Override
    protected void setupAppComponent(AppComponent appComponent) {
        DaggerPhotosComponent.builder().appComponent(appComponent)
                .photosModule(new PhotosModule(this))
                .build().inject(this);
    }

    @Override
    protected void init() {
        mPresenter.getPhotos("static/api/news/10003/list_1.json");
    }

    @Override
    public int setLayout() {
        return R.layout.fragmnet_photos;
    }


    @Override
    public void showPhotos(PhotosDataBean<PhotosNewsBean> value) {
        Log.e("TAG",value.getNews().size()+"");
    }
}
