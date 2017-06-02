package com.wz.beijingnews.presenter.contract;

import com.wz.beijingnews.bean.NewsTypeBaseBean;
import com.wz.beijingnews.bean.NewsTypeDataBean;
import com.wz.beijingnews.common.model.NewsTypeModel;
import com.wz.beijingnews.presenter.BasePresenter;

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
        mModel.getNewsType(new Callback<NewsTypeBaseBean<NewsTypeDataBean>>() {
            @Override
            public void onResponse(Call<NewsTypeBaseBean<NewsTypeDataBean>> call, Response<NewsTypeBaseBean<NewsTypeDataBean>> response) {
                if (response.isSuccessful()){
                    mView.showResult(response.body().getData());
                } else {
                    mView.showNoData();
                }
                mView.dismissLoading();
            }

            @Override
            public void onFailure(Call<NewsTypeBaseBean<NewsTypeDataBean>> call, Throwable t) {
                mView.dismissLoading();
                mView.showError();
            }
        });
    }


}
