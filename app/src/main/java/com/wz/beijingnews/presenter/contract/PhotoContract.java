package com.wz.beijingnews.presenter.contract;

import com.wz.beijingnews.bean.PhotosDataBean;
import com.wz.beijingnews.bean.PhotosNewsBean;
import com.wz.beijingnews.ui.BaseView;

/**
 * Created by wz on 17-6-5.
 */

public interface PhotoContract {

    interface View extends BaseView{

        void showPhotos(PhotosDataBean<PhotosNewsBean> value);

        void showMorePhotos(PhotosDataBean<PhotosNewsBean> value);


        void showError(String msg);

        void showLoading();

        void dismissLoading();

    }
}
