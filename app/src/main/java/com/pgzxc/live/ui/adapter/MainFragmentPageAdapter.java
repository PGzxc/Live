package com.pgzxc.live.ui.adapter;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;

/**
 * author：Administrator on 2016/12/26 09:46
 * description:文件说明
 * version:版本
 */
public class MainFragmentPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    public MainFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }
    public MainFragmentPageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
