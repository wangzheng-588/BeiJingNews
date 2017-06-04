package com.wz.beijingnews.di.module;

import com.google.gson.Gson;
import com.wz.beijingnews.AppApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wz on 17-6-3.
 */

@Module
public class AppModule {

    private final AppApplication mAppApplication;

    public AppModule(AppApplication appApplication) {
        this.mAppApplication = appApplication;
    }

    @Provides
    @Singleton
    public AppApplication provideApplication(){
        return mAppApplication;
    }

    @Provides
    @Singleton
    public Gson provideGson(){
        return new Gson();
    }
}
