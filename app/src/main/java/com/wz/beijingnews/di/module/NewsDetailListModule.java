package com.wz.beijingnews.di.module;

import com.wz.beijingnews.common.model.NewsDetailModel;
import com.wz.beijingnews.presenter.contract.NewsDetailContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wz on 17-6-3.
 */

@Module
public class NewsDetailListModule {

    private final NewsDetailContract.View mView;

    public NewsDetailListModule(NewsDetailContract.View view) {
        mView = view;
    }

    @Provides
    public NewsDetailContract.View provideView(){
        return mView;
    }

    @Provides
    public NewsDetailModel provideModel(){
        return new NewsDetailModel();
    }
}
