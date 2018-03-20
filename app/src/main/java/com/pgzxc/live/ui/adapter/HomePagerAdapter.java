package com.pgzxc.live.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.pgzxc.live.data.constants.ViewPagerType;
import com.pgzxc.live.ui.fragment.home.HomeChickenFragment;
import com.pgzxc.live.ui.fragment.home.HomeRecommendFragment;
import com.pgzxc.live.ui.fragment.home.HomeSmallVideoFragment;

/**
 * 首页适配器
 */

public class HomePagerAdapter extends FragmentPagerAdapter {

    private String[] titles;

    public HomePagerAdapter(FragmentManager fragmentManager, String[] titles) {
        super(fragmentManager);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case ViewPagerType.ZERO:
                return HomeRecommendFragment.newInstance();
            case ViewPagerType.FIRST:
                return HomeSmallVideoFragment.newInstance();
            case ViewPagerType.SECOND:
                return HomeChickenFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
