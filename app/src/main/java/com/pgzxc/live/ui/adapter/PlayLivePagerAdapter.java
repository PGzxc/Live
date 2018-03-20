package com.pgzxc.live.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.pgzxc.live.data.constants.ViewPagerType;
import com.pgzxc.live.ui.fragment.player.ChatFragment;
import com.pgzxc.live.ui.fragment.player.NoFragment;

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
