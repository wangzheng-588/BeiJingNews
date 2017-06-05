package com.wz.beijingnews.presenter;

import com.wz.beijingnews.bean.NewsTypeBaseBean;
import com.wz.beijingnews.bean.NewsTypeChildBean;
import com.wz.beijingnews.bean.NewsTypeDataBean;
import com.wz.beijingnews.common.model.NewsTypeModel;
import com.wz.beijingnews.presenter.contract.NewsTypeContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wz on 17-6-2.
 */

public class NewsTypePresenter extends BasePresenter<NewsTypeModel,NewsTypeContract.View> {

    @Inject
    public NewsTypePresenter(NewsTypeModel model, NewsTypeContract.View view) {
        super(model, view);
    }


    /**
     * 请求新闻中心类型标题
     */
    public void getNewsTypeTitle(){
        mView.showLoading();
        mModel.getNewsType().compose(new ObservableTransformer<NewsTypeBaseBean<NewsTypeDataBean<NewsTypeChildBean>>, List<NewsTypeChildBean>>() {
            @Override
            public ObservableSource<List<NewsTypeChildBean>> apply(Observable<NewsTypeBaseBean<NewsTypeDataBean<NewsTypeChildBean>>> upstream) {
                return upstream.flatMap(new Function<NewsTypeBaseBean<NewsTypeDataBean<NewsTypeChildBean>>, ObservableSource<List<NewsTypeChildBean>>>() {
                    @Override
                    public ObservableSource<List<NewsTypeChildBean>> apply(final NewsTypeBaseBean<NewsTypeDataBean<NewsTypeChildBean>> newsTypeDataBeanNewsTypeBaseBean) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<List<NewsTypeChildBean>>() {
                            @Override
                            public void subscribe(ObservableEmitter<List<NewsTypeChildBean>> e) throws Exception {
                                List<NewsTypeChildBean> children = newsTypeDataBeanNewsTypeBaseBean.getData().get(0).getChildren();
                                e.onNext(children);
                                e.onComplete();
                            }
                        });
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new DefaultObserver<List<NewsTypeChildBean>>() {
            @Override
            public void onNext(List<NewsTypeChildBean> value) {
                mView.showNewsTypeTitle(value);
            }

            @Override
            public void onError(Throwable e) {
                mView.showError();
            }

            @Override
            public void onComplete() {
                mView.dismissLoading();
            }
        });

    }

}
