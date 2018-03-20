package com.pgzxc.live.ui.activity;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import com.pgzxc.live.R;
import com.pgzxc.live.data.preference.guide.GuidePreference;
import com.pgzxc.live.databinding.ActivityGuideBinding;
import com.pgzxc.live.ui.base.BaseActivity;
import com.pgzxc.live.utils.ActivityUtils;
import com.pgzxc.live.widget.KJViewPagerIndicator;

/**
 * 引导页
 */

public class GuideActivity extends BaseActivity<ActivityGuideBinding> {
    private static final int MAX_INDEX = 2;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initData() {
        super.initData();
        mDataBinding.guideViewPager.setOnViewChangeListener(new KJViewPagerIndicator(this, mDataBinding.linearDots, 3));
    }

    @Override
    public void setListener() {
        super.setListener();
        mDataBinding.tvStart.setOnClickListener(view -> enterApp());
        mDataBinding.guideViewPager.setOnTouchListener(new View.OnTouchListener() {//监听viewPager的触摸事件

            float downX;//手指按下时的横坐标

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://触摸事件为手指按下
                        downX = event.getX();//获取按下时的横坐标
                        break;
                    case MotionEvent.ACTION_MOVE://触摸事件为手指移动
                        //如果当前页为最后一页，拦截掉向左移动事件
                        if (mDataBinding.guideViewPager.getCurScreen() == MAX_INDEX && downX > event.getX()) {
                            return true;
                        }
                        //如果当前页为第一页，拦截掉向右移动事件
                        else if (mDataBinding.guideViewPager.getCurScreen() == 0 && downX < event.getX()) {
                            return true;
                        }
                        break;
                    case MotionEvent.ACTION_UP://触摸事件为手指放开
                        //如果当前页为最后一页，并且手指向左移动了超过200像素
                        if (mDataBinding.guideViewPager.getCurScreen() == MAX_INDEX && downX - event.getX() > 200) {
                            enterApp();//向右滑动进入主界面
                            return true;
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void enterApp() {
        ActivityUtils.skipActivity(GuideActivity.this, LoginActivity.class);
        GuidePreference.setShowGuide(this, false);
    }
}
