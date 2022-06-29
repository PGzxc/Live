package com.pgzxc.live.ui.adapter;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.pgzxc.live.data.constants.ViewPagerType;
import com.pgzxc.live.ui.fragment.recent.RecentGameFragment;
import com.pgzxc.live.ui.fragment.recent.RecentLiveFragment;
import com.pgzxc.live.ui.fragment.recent.RecentSmallVideoFragment;

/**
 * 附近适配器
 */

public class RecentPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;

    public RecentPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case ViewPagerType.ZERO:
                return RecentLiveFragment.newInstance();
            case ViewPagerType.FIRST:
                return RecentSmallVideoFragment.newInstance();
            case ViewPagerType.SECOND:
                return RecentGameFragment.newInstance();
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
