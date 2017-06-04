package com.wz.beijingnews;

import android.app.Application;
import android.content.Context;

import com.wz.beijingnews.di.component.AppComponent;
import com.wz.beijingnews.di.component.DaggerAppComponent;
import com.wz.beijingnews.di.module.AppModule;
import com.wz.beijingnews.di.module.HttpModule;

/**
 * Created by wz on 17-6-2.
 */

public class AppApplication extends Application {

    public AppApplication getContext(Context context){
        return (AppApplication) context.getApplicationContext();
    }

    public AppComponent mAppComponent;

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule((AppApplication)getApplicationContext()))
                .httpModule(new HttpModule()).build();
    }
}
