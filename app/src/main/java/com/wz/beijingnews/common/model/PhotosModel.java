package com.wz.beijingnews.common.model;

import com.wz.beijingnews.bean.PhotosBaseBean;
import com.wz.beijingnews.bean.PhotosDataBean;
import com.wz.beijingnews.bean.PhotosNewsBean;
import com.wz.beijingnews.common.http.ApiService;

import io.reactivex.Observable;

/**
 * Created by wz on 17-6-5.
 */

public class PhotosModel {

    ApiService mApiService;

    public PhotosModel(ApiService apiService) {
        mApiService = apiService;
    }

    public Observable<PhotosBaseBean<PhotosDataBean<PhotosNewsBean>>> getPhotos(String url) {
        return mApiService.getPhotos(url);
    }
}
