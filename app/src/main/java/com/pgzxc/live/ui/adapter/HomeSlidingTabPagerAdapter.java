package com.pgzxc.live.ui.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;


/**
 * 首页适配器
 */

public class HomeSlidingTabPagerAdapter extends FragmentPagerAdapter {
    private Class<?>[] fragmentClsArray;
    private Context mContext;
    private String[] array;

    public HomeSlidingTabPagerAdapter(FragmentActivity activity, Class<?>[] fragmentClsArray, String[] array) {
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
