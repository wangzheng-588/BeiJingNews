package com.wz.beijingnews.di.module;

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

    //    NewsTypeModel model, LeftMenuContract.View view

    @Provides
    public NewsTypeModel provideModel() {
        return new NewsTypeModel();
    }

    @Provides
    public LeftMenuContract.View provideView(){
        return mView;
    }

}
