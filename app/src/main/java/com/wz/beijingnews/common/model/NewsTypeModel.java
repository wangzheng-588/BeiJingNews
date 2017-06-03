package com.wz.beijingnews.common.model;

import com.wz.beijingnews.bean.NewsTypeBaseBean;
import com.wz.beijingnews.bean.NewsTypeChildBean;
import com.wz.beijingnews.bean.NewsTypeDataBean;
import com.wz.beijingnews.common.http.ApiService;
import com.wz.beijingnews.common.http.HttpManager;

import io.reactivex.Observable;

/**
 * Created by wz on 17-6-2.
 */

public class NewsTypeModel {

    public Observable<NewsTypeBaseBean<NewsTypeDataBean<NewsTypeChildBean>>> getNewsType(){
        HttpManager manager = new HttpManager();
        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
        return apiService.getNewsType();
    }
}
