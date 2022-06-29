package com.pgzxc.live.ui.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


/**
 * 直播页适配器
 */

public class PlayLivePagerAdapter extends FragmentPagerAdapter {

    public PlayLivePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
//            case ViewPagerType.ZERO:
//                return NoFragment.newInstance();
//            case ViewPagerType.FIRST:
//                return ChatFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

}
