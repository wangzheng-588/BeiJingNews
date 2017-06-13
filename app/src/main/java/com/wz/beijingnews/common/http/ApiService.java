package com.wz.beijingnews.common.http;

import com.wz.beijingnews.bean.NewsBaseBean;
import com.wz.beijingnews.bean.NewsBean;
import com.wz.beijingnews.bean.NewsDataBean;
import com.wz.beijingnews.bean.NewsTypeBaseBean;
import com.wz.beijingnews.bean.NewsTypeChildBean;
import com.wz.beijingnews.bean.NewsTypeDataBean;
import com.wz.beijingnews.bean.PhotosBaseBean;
import com.wz.beijingnews.bean.PhotosDataBean;
import com.wz.beijingnews.bean.PhotosNewsBean;
import com.wz.beijingnews.bean.TopNewsBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * Created by wz on 17-6-2.
 */

public interface ApiService {


    @GET("static/api/news/categories.json")
    Observable<NewsTypeBaseBean<NewsTypeDataBean<NewsTypeChildBean>>> getNewsType();


    @GET
    Observable<NewsBaseBean<NewsDataBean<NewsBean,TopNewsBean>>> getNewsDetail(@Url String url);


    @GET
    Observable<NewsBaseBean<NewsDataBean<NewsBean,TopNewsBean>>> loadMoreNewsDetail(@Url String moreUrl);

    @GET
    Observable<NewsBaseBean<NewsDataBean<NewsBean,TopNewsBean>>> getTopNewsDetail(@Url String topNewsUrl);

    @GET
    Observable<PhotosBaseBean<PhotosDataBean<PhotosNewsBean>>> getPhotos(@Url String url);


    @Multipart
    @POST("FileUpload/FileUploadServlet")
    Call<ResponseBody> upload(@Part("description") RequestBody description,
                              @Part MultipartBody.Part file);

}
