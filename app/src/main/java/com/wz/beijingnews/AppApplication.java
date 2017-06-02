package com.wz.beijingnews;

import android.app.Application;
import android.content.Context;

/**
 * Created by wz on 17-6-2.
 */

public class AppApplication extends Application {

    public AppApplication getContext(Context context){
        return (AppApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
