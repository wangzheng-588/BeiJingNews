package com.wz.beijingnews.presenter;

import com.wz.beijingnews.bean.NewsTypeBaseBean;
import com.wz.beijingnews.bean.NewsTypeChildBean;
import com.wz.beijingnews.bean.NewsTypeDataBean;
import com.wz.beijingnews.common.model.NewsTypeModel;
import com.wz.beijingnews.presenter.contract.NewsTypeContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wz on 17-6-2.
 */

public class NewsTypePresenter extends BasePresenter<NewsTypeModel,NewsTypeContract.View> {
    public NewsTypePresenter(NewsTypeModel model, NewsTypeContract.View view) {
        super(model, view);
    }

    public void requestDatas(){
        mView.showLoading();
        mModel.getNewsType(new Callback<NewsTypeBaseBean<NewsTypeDataBean<NewsTypeChildBean>>>() {
            @Override
            public void onResponse(Call<NewsTypeBaseBean<NewsTypeDataBean<NewsTypeChildBean>>> call, Response<NewsTypeBaseBean<NewsTypeDataBean<NewsTypeChildBean>>> response) {
                mView.showResult(response.body().getData());
            }

            @Override
            public void onFailure(Call<NewsTypeBaseBean<NewsTypeDataBean<NewsTypeChildBean>>> call, Throwable t) {

            }
        });
    }


}
