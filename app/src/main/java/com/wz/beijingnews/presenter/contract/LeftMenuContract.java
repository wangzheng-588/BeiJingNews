package com.wz.beijingnews.presenter.contract;

import com.wz.beijingnews.ui.BaseView;

import java.util.List;

/**
 * Created by wz on 17-6-3.
 */

public interface LeftMenuContract {

    interface View extends BaseView{
        void showLeftMenuTitle(List<String> value);
        void showError();
    }
}
