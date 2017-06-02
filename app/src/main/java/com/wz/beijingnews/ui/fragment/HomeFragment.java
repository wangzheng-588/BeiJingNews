package com.wz.beijingnews.ui.fragment;

import android.widget.FrameLayout;

import com.wz.beijingnews.R;

import butterknife.BindView;

/**
 * Created by wz on 17-6-2.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.fl_home)
    FrameLayout mFlHome;


    /**
     * 北京/中国/国际/文娱/体育/生活/旅游/科技/军事/财经/女性/倍儿
     * 新闻/专题/组图/互动/投票
     *
     * @return
     */

    @Override
    protected int setLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {


    }


}


