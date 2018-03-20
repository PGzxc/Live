package com.pgzxc.live.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.pgzxc.live.R;
import org.kymjs.kjframe.utils.DensityUtils;
import org.kymjs.kjframe.widget.KJViewPager;
import java.util.ArrayList;
import java.util.List;

/**
 * KJViewPager知识点
 */

public class KJViewPagerIndicator implements KJViewPager.OnViewChangeListener {
    private int size;
    private List<ImageView> dotViewLists = new ArrayList<>();

    public KJViewPagerIndicator(Context context, LinearLayout dotLayout, int size) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //为小圆点左右添加间距
            params.leftMargin = DensityUtils.dip2px(context, 5);
            params.rightMargin = DensityUtils.dip2px(context, 5);
            //手动给小圆点一个大小
            params.height = DensityUtils.dip2px(context, 15);
            params.width = DensityUtils.dip2px(context, 15);
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.circle_enable);
            } else {
                imageView.setBackgroundResource(R.drawable.circle_disable);
            }
            //为LinearLayout添加ImageView
            dotLayout.addView(imageView, params);
            dotViewLists.add(imageView);
        }

    }

    @Override
    public void OnViewChange(int position) {
        for (int i = 0; i < size; i++) {
            //选中的页面改变小圆点为选中状态，反之为未选中
            if ((position % size) == i) {
                ((View) dotViewLists.get(i)).setBackgroundResource(R.drawable.circle_enable);
            } else {
                ((View) dotViewLists.get(i)).setBackgroundResource(R.drawable.circle_disable);
            }
        }
    }
}