package com.wz.beijingnews.presenter.contract;

import com.wz.beijingnews.bean.NewsTypeChildBean;
import com.wz.beijingnews.bean.NewsTypeDataBean;
import com.wz.beijingnews.ui.BaseView;

import java.util.List;

/**
 * Created by wz on 17-6-2.
 */

public interface NewsTypeContract {

    interface View extends BaseView{
        void showResult(List<NewsTypeDataBean<NewsTypeChildBean>> data);

        void showNoData();

        void showError();
    }
}
