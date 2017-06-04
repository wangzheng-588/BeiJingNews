package com.wz.beijingnews.di.component;

import com.wz.beijingnews.di.FragmentScope;
import com.wz.beijingnews.di.module.LeftMenuModule;
import com.wz.beijingnews.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by wz on 17-6-3.
 */

@FragmentScope
@Component(modules = LeftMenuModule.class,dependencies = AppComponent.class)
public interface LeftMenuComponent {

    void inject(MainActivity mainActivity);

}
