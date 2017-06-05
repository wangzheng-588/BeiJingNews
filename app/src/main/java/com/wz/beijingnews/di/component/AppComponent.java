package com.wz.beijingnews.di.component;

import com.wz.beijingnews.AppApplication;
import com.wz.beijingnews.common.http.ApiService;
import com.wz.beijingnews.di.module.AppModule;
import com.wz.beijingnews.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wz on 17-6-3.
 */


@Component(modules = {AppModule.class, HttpModule.class})
@Singleton
public interface AppComponent {

    ApiService getApiService();

    AppApplication getApplication();
}
