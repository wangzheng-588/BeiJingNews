package com.wz.beijingnews.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.wz.beijingnews.R;
import com.wz.beijingnews.bean.PhotosDataBean;
import com.wz.beijingnews.bean.PhotosNewsBean;
import com.wz.beijingnews.di.component.AppComponent;
import com.wz.beijingnews.di.component.DaggerPhotosComponent;
import com.wz.beijingnews.di.module.PhotosModule;
import com.wz.beijingnews.presenter.PhotosPresenter;
import com.wz.beijingnews.presenter.contract.PhotoContract;
import com.wz.beijingnews.ui.activity.MainActivity;
import com.wz.beijingnews.ui.adapter.PhotosAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by wz on 17-6-2.
 */

public class PhotosFragment extends ProgressFragment implements PhotoContract.View,MainActivity.PhotosTypeClickListener {

    @Inject
    PhotosPresenter mPresenter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.material_refresh)
    MaterialRefreshLayout mMaterialRefresh;



    private PhotosAdapter mAdapter;
    private String mPhotosMoreUrl;
    private boolean isGridPhotos;


    @Override
    protected void setupAppComponent(AppComponent appComponent) {
        DaggerPhotosComponent.builder().appComponent(appComponent)
                .photosModule(new PhotosModule(this))
                .build().inject(this);
    }

    @Override
    protected void init() {
        mAdapter = new PhotosAdapter(mContext);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.getPhotos("static/api/news/10003/list_1.json");
        mMaterialRefresh.setLoadMore(true);
        initListener();
    }

    private void initListener() {
        mMaterialRefresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                mPresenter.getPhotos("static/api/news/10003/list_1.json");
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
               mPresenter.getMorePhotos(mPhotosMoreUrl.substring(1));
            }
        });

        ((MainActivity)getActivity()).setPhotosTypeClickListener(this);
    }

    @Override
    public int setLayout() {
        return R.layout.fragmnet_photos;
    }


    @Override
    public void showPhotos(PhotosDataBean<PhotosNewsBean> value) {
        mPhotosMoreUrl = value.getMore();
        mAdapter.setPhotosNewsBeen((ArrayList<PhotosNewsBean>) value.getNews());

    }

    @Override
    public void showMorePhotos(PhotosDataBean<PhotosNewsBean> value) {
        mPhotosMoreUrl = value.getMore();
        mAdapter.addPhotosNewsBeen(value);
    }

    @Override
    public void showError(String msg) {
        showEmptyView(msg);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {
        mMaterialRefresh.finishRefresh();
        mMaterialRefresh.finishRefreshLoadMore();
    }

    @Override
    public void setPhotosTypeClickListener(View view) {

        if (isGridPhotos){
            view.setBackgroundResource(R.drawable.icon_pic_grid_type);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            mAdapter.notifyDataSetChanged();
            isGridPhotos = false;
        } else {

            view.setBackgroundResource(R.drawable.icon_pic_list_type);
            mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
            mAdapter.notifyDataSetChanged();
            isGridPhotos = true;
        }
    }
}
