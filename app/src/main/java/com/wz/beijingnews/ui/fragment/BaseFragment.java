package com.wz.beijingnews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wz.beijingnews.AppApplication;
import com.wz.beijingnews.di.component.AppComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wz on 17-6-2.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder mBind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayoutResID(),container,false);
        mBind = ButterKnife.bind(this, view);
        init();
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AppComponent appComponent = ((AppApplication)(getActivity().getApplication())).getAppComponent();
        setupAppComponent(appComponent);
        initData();
    }

    protected abstract void setupAppComponent(AppComponent appComponent);


    protected void init(){}
    protected  void initData(){}

    protected abstract int setLayoutResID();



    @Override
    public void onDestroyView() {
        if (mBind!=mBind.EMPTY){
            mBind.unbind();
        }
        super.onDestroyView();
    }

}
