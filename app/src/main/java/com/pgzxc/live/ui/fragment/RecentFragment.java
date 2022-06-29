package com.pgzxc.live.ui.fragment;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.listener.OnTabSelectListener;
import com.pgzxc.live.R;
import com.pgzxc.live.data.constants.ViewPagerType;
import com.pgzxc.live.databinding.FragmentRecentBinding;
import com.pgzxc.live.ui.adapter.RecentPagerAdapter;
import com.pgzxc.live.ui.base.BaseFragment;

/**
 * 附近
 */

public class RecentFragment extends BaseFragment<FragmentRecentBinding> {

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_recent;
    }


    @Override
    protected void initData() {
        super.initData();
        RecentPagerAdapter nearByPagerAdapter = new RecentPagerAdapter(getChildFragmentManager(), getResources().getStringArray(R.array.nearby_Title));
        mDataBinding.viewPagerRecent.setAdapter(nearByPagerAdapter);
        mDataBinding.slidingTabLayoutRecent.setViewPager(mDataBinding.viewPagerRecent);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mDataBinding.slidingTabLayoutRecent.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mDataBinding.viewPagerRecent.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        mDataBinding.viewPagerRecent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == ViewPagerType.FIRST) {
                    mDataBinding.tvRecentFilter.setVisibility(View.INVISIBLE);
                } else {
                    mDataBinding.tvRecentFilter.setVisibility(View.VISIBLE);
                }
                mDataBinding.slidingTabLayoutRecent.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
