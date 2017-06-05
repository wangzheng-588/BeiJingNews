package com.wz.beijingnews.di.module;

import com.wz.beijingnews.common.http.ApiService;
import com.wz.beijingnews.common.model.NewsTypeModel;
import com.wz.beijingnews.presenter.contract.LeftMenuContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wz on 17-6-3.
 */

@Module
public class LeftMenuModule {
    private final LeftMenuContract.View mView;

    public LeftMenuModule(LeftMenuContract.View view) {
        this.mView = view;
    }


    @Provides
    public NewsTypeModel provideModel(ApiService apiService) {
        return new NewsTypeModel(apiService);
    }

    @Provides
    public LeftMenuContract.View provideView(){
        return mView;
    }

}
