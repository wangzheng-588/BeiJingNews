package com.wz.beijingnews.ui.activity;

import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.nineoldandroids.view.ViewHelper;
import com.wz.beijingnews.AppApplication;
import com.wz.beijingnews.R;
import com.wz.beijingnews.common.model.NewsTypeModel;
import com.wz.beijingnews.di.component.AppComponent;
import com.wz.beijingnews.di.component.DaggerLeftMenuComponent;
import com.wz.beijingnews.di.module.LeftMenuModule;
import com.wz.beijingnews.presenter.LeftMenuPresenter;
import com.wz.beijingnews.presenter.contract.LeftMenuContract;
import com.wz.beijingnews.ui.adapter.LeftTitleAdapter;
import com.wz.beijingnews.ui.fragment.FragmentFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements LeftMenuContract.View {


    @BindView(R.id.iv_menu)
    ImageView mIvMenu;
    @BindView(R.id.fl_main)
    FrameLayout mFlMain;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.rb_home)
    RadioButton mRbHome;
    @BindView(R.id.rb_video)
    RadioButton mRbVideo;
    @BindView(R.id.rb_me)
    RadioButton mRbMe;
    @BindView(R.id.rg_main)
    RadioGroup mRgMain;
    @BindView(R.id.list_view)
    ListView mListView;

    private int prePosition;

    private boolean isOpen;
    private Fragment preFragment;
    private int mPosition;
    private ArrayList<Fragment> mFragments;
    private List<Fragment> mNewsDetailFragments;

    @Inject
     LeftMenuPresenter mPresenter;
    @Inject
     NewsTypeModel mModel;

    @Override
    protected int setLayoutRedID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        AppComponent appComponent = ((AppApplication) getApplication()).getAppComponent();
        DaggerLeftMenuComponent.builder().appComponent(appComponent).leftMenuModule(new LeftMenuModule(this))
                .build().inject(this);


        mPresenter.getLeftMenuTitle();

        initNewsDetailFragment();

        initFragment();

        initDrawerLayout();
        initRidaoGroup();
    }

    private void initNewsDetailFragment() {
        mNewsDetailFragments = new ArrayList<>();
        mNewsDetailFragments.add(FragmentFactory.createFragment(0));
        mNewsDetailFragments.add(FragmentFactory.createFragment(3));
        mNewsDetailFragments.add(FragmentFactory.createFragment(4));
        mNewsDetailFragments.add(FragmentFactory.createFragment(5));
        mNewsDetailFragments.add(FragmentFactory.createFragment(6));
    }

    private void initRidaoGroup() {
        mRgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch (checkedId) {
                    case R.id.rb_home:
                        mIvMenu.setVisibility(View.VISIBLE);
                        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                        mPosition = 0;
                        break;
                    case R.id.rb_video:
                        mIvMenu.setVisibility(View.GONE);
                        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        mPosition = 1;
                        break;
                    case R.id.rb_me:
                        mPosition = 2;
                        mIvMenu.setVisibility(View.GONE);
                        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        break;
                }
                Fragment fragment = mFragments.get(mPosition);
                changeFragment(fragment);
            }
        });

        mRgMain.check(R.id.rb_home);
    }


    private void initDrawerLayout() {
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                //改变DrawLayout侧栏透明度，若不需要效果可以不设置
                ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));

                ViewHelper.setTranslationX(mContent,
                        mMenu.getMeasuredWidth() * (1 - scale));

                ViewHelper.setPivotX(mContent, 0);
                ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);

                mContent.invalidate();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                isOpen = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isOpen = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(FragmentFactory.createFragment(0));
        mFragments.add(FragmentFactory.createFragment(1));
        mFragments.add(FragmentFactory.createFragment(2));
    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragment != preFragment) {
            if (fragment.isAdded()) {
                if (preFragment != null) {
                    ft.hide(preFragment);
                }
                ft.show(fragment);
            } else {
                if (preFragment != null) {
                    ft.hide(preFragment);
                }
                ft.add(R.id.fl_main, fragment);
            }
        }
        preFragment = fragment;
        ft.commit();

    }


    @OnClick(R.id.iv_menu)
    public void onViewClicked() {
        if (isOpen) {
            mDrawerLayout.closeDrawers();
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }



    /**
     * 初使化左侧菜单标题
     *
     * @param data
     */
    private void initLeftMenuTitle(List<String> data) {
        List<String> titles = new ArrayList<>(data.size());
        for (int i = 0; i < data.size(); i++) {
            String title = data.get(i);
            titles.add(title);
        }
        final LeftTitleAdapter leftTitleAdapter = new LeftTitleAdapter(this, titles);
        mListView.setAdapter(leftTitleAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeNewsDetailFragment(position);
                prePosition = position;
                leftTitleAdapter.setPosition(prePosition);
                leftTitleAdapter.notifyDataSetChanged();
            }
        });

    }

    private void changeNewsDetailFragment(int position) {
        Fragment fragment = mNewsDetailFragments.get(position);
        changeFragment(fragment);
    }


    @Override
    public void showError() {

    }

    @Override
    public void showLeftMenuTitle(List<String> value) {
        initLeftMenuTitle(value);
    }
}
