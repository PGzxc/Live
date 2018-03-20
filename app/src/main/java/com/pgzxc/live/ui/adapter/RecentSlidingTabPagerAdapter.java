package com.pgzxc.live.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 附近适配器
 */

public class RecentSlidingTabPagerAdapter extends FragmentPagerAdapter {
    private Class<?>[] fragmentClsArray;
    private Context mContext;
    private String[] array;

    public RecentSlidingTabPagerAdapter(FragmentActivity activity, Class<?>[] fragmentClsArray, String[] array) {
        super(activity.getSupportFragmentManager());
        this.fragmentClsArray = fragmentClsArray;
        this.array = array;
        this.mContext = activity;
    }

    @Override
    public Fragment getItem(int position) {
        return Fragment.instantiate(mContext, fragmentClsArray[position].getName());
    }

    @Override
    public int getCount() {
        return fragmentClsArray.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return array[position];
    }
}
