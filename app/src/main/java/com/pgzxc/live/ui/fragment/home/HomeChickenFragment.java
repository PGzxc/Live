package com.pgzxc.live.ui.fragment.home;

import android.os.Bundle;

import com.pgzxc.live.R;
import com.pgzxc.live.databinding.FragmentHomeEatChickenBinding;
import com.pgzxc.live.ui.base.BaseFragment;

/**
 * 首页-吃鸡
 */

public class HomeChickenFragment extends BaseFragment<FragmentHomeEatChickenBinding> {
    public static HomeChickenFragment newInstance() {

        Bundle args = new Bundle();
        HomeChickenFragment fragment = new HomeChickenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_home_eat_chicken;
    }

    @Override
    protected void setListener() {
        super.setListener();
    }
}
