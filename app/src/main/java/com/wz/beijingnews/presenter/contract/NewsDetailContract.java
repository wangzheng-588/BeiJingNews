package com.wz.beijingnews.presenter.contract;

import com.wz.beijingnews.bean.NewsBean;
import com.wz.beijingnews.bean.NewsDataBean;
import com.wz.beijingnews.bean.TopNewsBean;
import com.wz.beijingnews.ui.BaseView;

import java.util.List;

/**
 * Created by wz on 17-6-3.
 */

public interface NewsDetailContract {

    interface View extends BaseView {
        void showResult(NewsDataBean<NewsBean, TopNewsBean> data);


        void showNoData();

        void showError(String msg);

        void showLoadMoreData(NewsDataBean<NewsBean, TopNewsBean> data);

        void showLoading();

        void dismissLoading();

        void showTopNews(List<TopNewsBean> value);
    }

}
