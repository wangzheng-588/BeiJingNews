package com.wz.beijingnews.common.model;

import com.wz.beijingnews.bean.NewsTypeBaseBean;
import com.wz.beijingnews.bean.NewsTypeChildBean;
import com.wz.beijingnews.bean.NewsTypeDataBean;
import com.wz.beijingnews.common.http.ApiService;

import io.reactivex.Observable;

/**
 * Created by wz on 17-6-2.
 */

public class NewsTypeModel {

    ApiService mApiService;

    public NewsTypeModel(ApiService apiService) {
        mApiService = apiService;
    }

    public Observable<NewsTypeBaseBean<NewsTypeDataBean<NewsTypeChildBean>>> getNewsType(){
        return mApiService.getNewsType();
    }
}
