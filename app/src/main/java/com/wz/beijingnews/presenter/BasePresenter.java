package com.wz.beijingnews.presenter;

/**
 * Created by wz on 17-6-2.
 */

public class BasePresenter<M,V> {

    protected M mModel;
    protected V mView;

    public BasePresenter(M model, V view) {
        mModel = model;
        mView = view;
    }
}
