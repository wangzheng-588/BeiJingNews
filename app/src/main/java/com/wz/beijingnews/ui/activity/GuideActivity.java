package com.wz.beijingnews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wz.beijingnews.R;
import com.wz.beijingnews.common.utils.DisplayUtil;
import com.wz.beijingnews.common.utils.SPUtil;
import com.wz.beijingnews.ui.adapter.GuideAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends AppCompatActivity {

    private static final int[] imageIDs = new int[]{R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.btn_start)
    Button mBtnStart;
    @BindView(R.id.iv_point_red)
    ImageView mIvPointRed;
    @BindView(R.id.ll_point_group)
    LinearLayout mLlPointGroup;

    private int mPointDIs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        init();
        initAdapter();
        initPoint();
        initListener();
    }

    private void init() {
        mLlPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                mLlPointGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mPointDIs = mLlPointGroup.getChildAt(1).getLeft() - mLlPointGroup.getChildAt(0).getLeft();
            }
        });
    }

    private void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(DisplayUtil.dp2px(GuideActivity.this, 12), DisplayUtil.dp2px(GuideActivity.this, 12));
                params.leftMargin = (int) ((positionOffset + position) * mPointDIs);
                mIvPointRed.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == imageIDs.length - 1) {
                    mBtnStart.setVisibility(View.VISIBLE);
                } else {
                    mBtnStart.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initPoint() {
        ImageView grayPoint;
        for (int i = 0; i < imageIDs.length; i++) {
            grayPoint = new ImageView(this);
            grayPoint.setBackgroundResource(R.drawable.point_gray);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DisplayUtil.dp2px(this, 12), DisplayUtil.dp2px(this, 12));
            if (i != 0) {
                layoutParams.leftMargin = DisplayUtil.dp2px(this, 10);
                grayPoint.setLayoutParams(layoutParams);
            }
            mLlPointGroup.addView(grayPoint, layoutParams);
        }
    }

    private void initAdapter() {
        GuideAdapter guideAdapter = new GuideAdapter(this, imageIDs);
        mViewPager.setAdapter(guideAdapter);
    }

    @OnClick(R.id.btn_start)
    public void onViewClicked() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
        SPUtil.putBoolean(this,"IS_FIRST",true);
    }
}
