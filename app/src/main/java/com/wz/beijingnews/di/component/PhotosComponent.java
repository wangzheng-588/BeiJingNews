package com.wz.beijingnews.di.component;

import com.wz.beijingnews.di.FragmentScope;
import com.wz.beijingnews.di.module.PhotosModule;
import com.wz.beijingnews.ui.fragment.PhotosFragment;

import dagger.Component;

/**
 * Created by wz on 17-6-5.
 */

@FragmentScope
@Component(modules = PhotosModule.class,dependencies = AppComponent.class)
public interface PhotosComponent {

    void inject(PhotosFragment fragment);
}
