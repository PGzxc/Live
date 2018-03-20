package com.pgzxc.live.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.pgzxc.live.R;
import java.util.List;

/**
 * 垂直的ViewPager
 */

public class VerticalPagerAdapter extends PagerAdapter {
    private List<String> mVideoUrls;

    public VerticalPagerAdapter(List<String> mVideoUrls) {
        this.mVideoUrls = mVideoUrls;
    }

    @Override
    public int getCount() {
        return mVideoUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_room_item, null);
        view.setId(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(container.findViewById(position));
    }
}
