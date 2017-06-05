package com.wz.beijingnews.di.component;

import com.wz.beijingnews.di.FragmentScope;
import com.wz.beijingnews.di.module.NewsTypeModule;
import com.wz.beijingnews.ui.fragment.NewsFragment;

import dagger.Component;

/**
 * Created by wz on 17-6-5.
 */

@FragmentScope
@Component(modules = NewsTypeModule.class,dependencies = AppComponent.class)
public interface NewsTypeComponent {

    void inject(NewsFragment fragment);
}
