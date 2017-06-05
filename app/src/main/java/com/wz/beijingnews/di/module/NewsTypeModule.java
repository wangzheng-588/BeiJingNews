package com.wz.beijingnews.di.module;

import com.wz.beijingnews.common.http.ApiService;
import com.wz.beijingnews.common.model.NewsTypeModel;
import com.wz.beijingnews.presenter.contract.NewsTypeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wz on 17-6-5.
 */

@Module
public class NewsTypeModule {

    private NewsTypeContract.View mView;

    public NewsTypeModule(NewsTypeContract.View view) {
        mView = view;
    }

    @Provides
    public NewsTypeModel provideNewsTypeModel(ApiService apiService){
        return new NewsTypeModel(apiService);
    }

    @Provides
    public NewsTypeContract.View provideView(){
        return mView;
    }
}
