package com.pgzxc.live.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * 拦截Imagview向下传递
 */

public class InterceptTouchImageView extends AppCompatImageView {

    public InterceptTouchImageView(Context context) {
        super(context);
    }

    public InterceptTouchImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptTouchImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            performClick();
//        }
//        return super.onTouchEvent(event);
//    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }
}
