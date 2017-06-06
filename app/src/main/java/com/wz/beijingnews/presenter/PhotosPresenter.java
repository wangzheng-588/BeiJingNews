package com.wz.beijingnews.presenter;

import com.wz.beijingnews.bean.PhotosBaseBean;
import com.wz.beijingnews.bean.PhotosDataBean;
import com.wz.beijingnews.bean.PhotosNewsBean;
import com.wz.beijingnews.common.model.PhotosModel;
import com.wz.beijingnews.presenter.contract.PhotoContract;

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
 * Created by wz on 17-6-5.
 */

public class PhotosPresenter extends BasePresenter<PhotosModel,PhotoContract.View>{
    @Inject
    public PhotosPresenter(PhotosModel model, PhotoContract.View view) {
        super(model, view);
    }

    public void getPhotos(String url){
        mModel.getPhotos(url).compose(new ObservableTransformer<PhotosBaseBean<PhotosDataBean<PhotosNewsBean>>, PhotosDataBean<PhotosNewsBean>>() {
            @Override
            public ObservableSource<PhotosDataBean<PhotosNewsBean>> apply(Observable<PhotosBaseBean<PhotosDataBean<PhotosNewsBean>>> upstream) {
                return upstream.flatMap(new Function<PhotosBaseBean<PhotosDataBean<PhotosNewsBean>>, ObservableSource<PhotosDataBean<PhotosNewsBean>>>() {
                    @Override
                    public ObservableSource<PhotosDataBean<PhotosNewsBean>> apply(final PhotosBaseBean<PhotosDataBean<PhotosNewsBean>> photosDataBeanPhotosBaseBean) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<PhotosDataBean<PhotosNewsBean>>() {
                            @Override
                            public void subscribe(ObservableEmitter<PhotosDataBean<PhotosNewsBean>> e) throws Exception {
                                PhotosDataBean<PhotosNewsBean> data = photosDataBeanPhotosBaseBean.getData();
                                e.onNext(data);
                                e.onComplete();
                            }
                        });
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new DefaultObserver<PhotosDataBean<PhotosNewsBean>>() {
            @Override
            public void onNext(PhotosDataBean<PhotosNewsBean> value) {
                mView.showPhotos(value);
            }

            @Override
            public void onError(Throwable e) {
                mView.showError(e.getMessage());
                mView.dismissLoading();
            }

            @Override
            public void onComplete() {
                mView.dismissLoading();
            }
        });
    }



    public void getMorePhotos(String url){
        mModel.getPhotos(url).compose(new ObservableTransformer<PhotosBaseBean<PhotosDataBean<PhotosNewsBean>>, PhotosDataBean<PhotosNewsBean>>() {
            @Override
            public ObservableSource<PhotosDataBean<PhotosNewsBean>> apply(Observable<PhotosBaseBean<PhotosDataBean<PhotosNewsBean>>> upstream) {
                return upstream.flatMap(new Function<PhotosBaseBean<PhotosDataBean<PhotosNewsBean>>, ObservableSource<PhotosDataBean<PhotosNewsBean>>>() {
                    @Override
                    public ObservableSource<PhotosDataBean<PhotosNewsBean>> apply(final PhotosBaseBean<PhotosDataBean<PhotosNewsBean>> photosDataBeanPhotosBaseBean) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<PhotosDataBean<PhotosNewsBean>>() {
                            @Override
                            public void subscribe(ObservableEmitter<PhotosDataBean<PhotosNewsBean>> e) throws Exception {
                                PhotosDataBean<PhotosNewsBean> data = photosDataBeanPhotosBaseBean.getData();
                                e.onNext(data);
                                e.onComplete();
                            }
                        });
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<PhotosDataBean<PhotosNewsBean>>() {
                    @Override
                    public void onNext(PhotosDataBean<PhotosNewsBean> value) {
                        mView.showMorePhotos(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                        mView.dismissLoading();
                    }

                    @Override
                    public void onComplete() {
                        mView.dismissLoading();
                    }
                });
    }
}
