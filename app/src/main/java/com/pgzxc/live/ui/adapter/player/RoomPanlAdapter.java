package com.pgzxc.live.ui.adapter.player;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.pgzxc.live.ui.base.BaseFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * ========================================
 * 描 述：房间面板的适配器
 * ========================================
 */
public class RoomPanlAdapter extends FragmentPagerAdapter {
    private final List<BaseFragment> mFragments = new ArrayList<>();

    public RoomPanlAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(BaseFragment fragment) {
        mFragments.add(fragment);
    }

    @Override
    public BaseFragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}