package com.wz.beijingnews.common.model;

import com.wz.beijingnews.bean.NewsTypeBaseBean;
import com.wz.beijingnews.bean.NewsTypeChildBean;
import com.wz.beijingnews.bean.NewsTypeDataBean;
import com.wz.beijingnews.common.http.ApiService;
import com.wz.beijingnews.common.http.HttpManager;

import retrofit2.Callback;

/**
 * Created by wz on 17-6-2.
 */

public class NewsTypeModel {

    public void getNewsType(Callback<NewsTypeBaseBean<NewsTypeDataBean>> callback){
        HttpManager manager = new HttpManager();
        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
        apiService.getNewsType().enqueue(callback);
    }
}
