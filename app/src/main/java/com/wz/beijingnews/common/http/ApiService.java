package com.wz.beijingnews.common.http;

import com.wz.beijingnews.bean.NewsTypeBaseBean;
import com.wz.beijingnews.bean.NewsTypeChildBean;
import com.wz.beijingnews.bean.NewsTypeDataBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by wz on 17-6-2.
 */

public interface ApiService {


    @GET("static/api/news/categories.json")
    Call<NewsTypeBaseBean<NewsTypeDataBean<NewsTypeChildBean>>> getNewsType();
}
