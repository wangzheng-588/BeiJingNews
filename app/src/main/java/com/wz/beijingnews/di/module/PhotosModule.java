package com.wz.beijingnews.di.module;

import com.wz.beijingnews.common.http.ApiService;
import com.wz.beijingnews.common.model.PhotosModel;
import com.wz.beijingnews.presenter.contract.PhotoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wz on 17-6-5.
 */

@Module
public class PhotosModule {

    PhotoContract.View mView;

    public PhotosModule(PhotoContract.View view) {
        this.mView = view;
    }

    @Provides
    public PhotosModel provideModel(ApiService apiService){
        return new PhotosModel(apiService);
    }

    @Provides
    public PhotoContract.View provideView(){
        return mView;
    }
}
