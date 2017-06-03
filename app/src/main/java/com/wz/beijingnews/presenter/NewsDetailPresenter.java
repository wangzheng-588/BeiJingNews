package com.wz.beijingnews.presenter;

import com.wz.beijingnews.bean.NewsBaseBean;
import com.wz.beijingnews.bean.NewsBean;
import com.wz.beijingnews.bean.NewsDataBean;
import com.wz.beijingnews.bean.TopNewsBean;
import com.wz.beijingnews.common.model.NewsDetailModel;
import com.wz.beijingnews.presenter.contract.NewsDetailContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wz on 17-6-3.
 */

public class NewsDetailPresenter extends BasePresenter<NewsDetailModel,NewsDetailContract.View> {


    public NewsDetailPresenter(NewsDetailModel model, NewsDetailContract.View view) {
        super(model, view);
    }

    public void requestDatas(String url){
        mModel.getNewsDetail(url, new Callback<NewsBaseBean<NewsDataBean<NewsBean, TopNewsBean>>>() {
            @Override
            public void onResponse(Call<NewsBaseBean<NewsDataBean<NewsBean, TopNewsBean>>> call, Response<NewsBaseBean<NewsDataBean<NewsBean, TopNewsBean>>> response) {
                if (response.isSuccessful()){
                    NewsDataBean<NewsBean, TopNewsBean> data = response.body().getData();
                    mView.showResult(data);
                }

                mView.dismissLoading();
            }

            @Override
            public void onFailure(Call<NewsBaseBean<NewsDataBean<NewsBean, TopNewsBean>>> call, Throwable t) {
                mView.showError();
                mView.dismissLoading();
            }
        });
    }

    public void loadMoreDatas(String moreUrl){
        mModel.loadMoreNewsDetail(moreUrl, new Callback<NewsBaseBean<NewsDataBean<NewsBean, TopNewsBean>>>() {
            @Override
            public void onResponse(Call<NewsBaseBean<NewsDataBean<NewsBean, TopNewsBean>>> call, Response<NewsBaseBean<NewsDataBean<NewsBean, TopNewsBean>>> response) {
                mView.showLoadMoreData(response.body().getData());
                mView.dismissLoading();
            }

            @Override
            public void onFailure(Call<NewsBaseBean<NewsDataBean<NewsBean, TopNewsBean>>> call, Throwable t) {
                mView.showError();
                mView.dismissLoading();
            }
        });
    }
}
