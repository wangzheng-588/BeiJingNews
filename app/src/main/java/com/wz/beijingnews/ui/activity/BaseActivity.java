package com.wz.beijingnews.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wz on 17-6-2.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutRedID());
        mBind = ButterKnife.bind(this);
        init();
    }



    protected abstract int setLayoutRedID() ;

    protected abstract void init();


    @Override
    protected void onDestroy() {
        if (mBind!=mBind.EMPTY){
            mBind.unbind();
        }
        super.onDestroy();
    }
}
