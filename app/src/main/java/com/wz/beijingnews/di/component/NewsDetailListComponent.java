package com.wz.beijingnews.di.component;

import com.wz.beijingnews.di.FragmentScope;
import com.wz.beijingnews.di.module.NewsDetailListModule;
import com.wz.beijingnews.ui.fragment.NewsDetailListFragment;

import dagger.Component;

/**
 * Created by wz on 17-6-3.
 */

@FragmentScope
@Component(modules = NewsDetailListModule.class,dependencies = AppComponent.class)
public interface NewsDetailListComponent {

    void inject(NewsDetailListFragment fragment);
}
