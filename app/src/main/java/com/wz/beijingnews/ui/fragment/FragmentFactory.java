package com.wz.beijingnews.ui.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wz on 17-6-3.
 */

public class FragmentFactory {

    private static Map<Integer, Fragment> mFragments = new HashMap<>();


    public static Fragment createFragment(int position) {
        Fragment fragment = null;
        fragment = mFragments.get(position);  //在集合中取出来Fragment
        if (fragment == null)   //如果在集合中没有取出来，需要重新创建
        {
            if (position == 0) {
                fragment = new NewsFragment();
            } else if (position == 1) {
                fragment = new VideoFragment();
            } else if (position == 2) {
                fragment = new MeFragment();
            } else if (position == 3) {
                fragment = new SpecialFragment();
            } else if (position == 4) {
                fragment = new PhotosFragment();
            } else if (position == 5) {
                fragment = new InteractFragment();
            } else if (position == 6) {
                fragment = new VoteFragment();
            }
            if (fragment != null) {
                mFragments.put(position, fragment);
            }
        }
        return fragment;

    }
}
