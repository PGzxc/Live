package com.pgzxc.live.ui.fragment.recent;

import android.os.Bundle;

import com.pgzxc.live.R;
import com.pgzxc.live.databinding.FragmentRecentGameBinding;
import com.pgzxc.live.ui.base.BaseFragment;

/**
 * 附近-游戏直播
 */

public class RecentGameFragment extends BaseFragment<FragmentRecentGameBinding> {
    public static RecentGameFragment newInstance() {

        Bundle args = new Bundle();
        RecentGameFragment fragment = new RecentGameFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_recent_game;
    }
}
