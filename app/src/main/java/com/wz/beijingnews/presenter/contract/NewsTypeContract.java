package com.wz.beijingnews.presenter.contract;

import com.wz.beijingnews.bean.NewsTypeChildBean;
import com.wz.beijingnews.ui.BaseView;

import java.util.List;

/**
 * Created by wz on 17-6-2.
 */

public interface NewsTypeContract {

    interface View extends BaseView{
       void showNewsTypeTitle(List<NewsTypeChildBean> value);

        void showError();

        void showLeftMenuTitle(List<String> value);
        void showLoading();
        void dismissLoading();
    }
}
