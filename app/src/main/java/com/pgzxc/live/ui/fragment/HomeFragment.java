package com.pgzxc.live.ui.fragment;

import android.support.v4.view.ViewPager;

import com.flyco.tablayout.listener.OnTabSelectListener;
import com.pgzxc.live.R;
import com.pgzxc.live.databinding.FragmentHomeBinding;
import com.pgzxc.live.ui.adapter.HomePagerAdapter;
import com.pgzxc.live.ui.base.BaseFragment;

/**
 * 首页
 */

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

    }

    @Override
    protected void initData() {
        super.initData();
        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(getChildFragmentManager(), getResources().getStringArray(R.array.home_Title));
        mDataBinding.viewPagerHome.setAdapter(homePagerAdapter);
        mDataBinding.slidingTabLayoutHome.setViewPager(mDataBinding.viewPagerHome);
    }


    @Override
    protected void setListener() {
        super.setListener();
        mDataBinding.slidingTabLayoutHome.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mDataBinding.viewPagerHome.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        mDataBinding.viewPagerHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mDataBinding.slidingTabLayoutHome.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
