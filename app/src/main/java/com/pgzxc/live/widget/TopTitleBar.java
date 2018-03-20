package com.pgzxc.live.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.pgzxc.live.R;

/**
 * 顶部标题栏的布局封装
 */
public class TopTitleBar extends FrameLayout {

    private ImageView mLeftIv;//左侧按钮
    private TextView mTitleTv;//标题textView
    private ImageView mRightIv;//右侧图片按钮
    private TextView mRightTv;//右侧文字按钮
    private TextView mLeftTv;//左侧侧文字按钮

    public TopTitleBar(Context context) {
        this(context, null);
    }

    public TopTitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_top_title_bar, this);//解析布局
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLeftIv = (ImageView) findViewById(R.id.top_title_bar_left_iv);
        mTitleTv = (TextView) findViewById(R.id.top_title_bar_tv);
        mRightIv = (ImageView) findViewById(R.id.top_title_bar_right_iv);
        mRightTv = (TextView) findViewById(R.id.top_title_bar_right_tv);
        mLeftTv = (TextView) findViewById(R.id.top_title_bar_left_tv);
    }

    /**
     * 设置左侧按钮的图片资源和点击事件
     */
    public void setLeftImageView(int resId, OnClickListener listener) {
        mLeftIv.setImageResource(resId);
        mLeftIv.setVisibility(VISIBLE);
        mLeftIv.setOnClickListener(listener);
    }

    /**
     * 设置左侧按钮的点击事件，资源为返回icon
     */
    public void setLeftImageView(OnClickListener listener) {
        setLeftImageView(R.drawable.drawable_left, listener);
    }

    /**
     * 设置标题文字
     */
    public void setTitleTextView(String text) {
        mTitleTv.setText(text);
    }

    /**
     * 设置标题资源
     */
    public void setTitleTextView(int resId) {
        setTitleTextView(getResources().getString(resId));

    }

    /**
     * 设置右侧图片按钮的图片资源和点击事件
     */
    public void setRightImageView(int resId, OnClickListener listener) {
        mRightIv.setImageResource(resId);
        mRightIv.setVisibility(VISIBLE);
        mRightIv.setOnClickListener(listener);
    }

    /**
     * 获取右侧图片按钮的图片资源和点击事件
     */
    public ImageView getRightImageView() {
        return mRightIv;
    }

    /**
     * 设置右侧文字按钮的文字和点击事件
     */
    public void setRightTextView(String text, OnClickListener listener) {
        mRightTv.setVisibility(VISIBLE);
        mRightTv.setText(text);
        mRightTv.setOnClickListener(listener);
    }

    /**
     * 设置右侧文字按钮的文字和点击事件
     */
    public void setRightTextViewImage(String text, int resId, OnClickListener listener) {
        mRightTv.setVisibility(VISIBLE);
        mRightTv.setText(text);
        mRightTv.setOnClickListener(listener);
        Drawable img = getResources().getDrawable(resId);
// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
        mRightTv.setCompoundDrawables(null, null, img, null); //设置右图标
    }

    /**
     * 设置左侧文字按钮的文字和点击事件
     */
    public void setLeftTextView(String text, OnClickListener listener) {
        mLeftTv.setVisibility(VISIBLE);
        mLeftTv.setText(text);
        mLeftTv.setOnClickListener(listener);
    }

    /**
     * 设置右侧文字按钮的文字资源和点击事件
     */
    public void setRightTextView(int resId, OnClickListener listener) {
        setRightTextView(getResources().getString(resId), listener);
    }

    /**
     * 设置左侧侧文字按钮的文字资源和点击事件
     */
    public void setLeftTextView(int resId, OnClickListener listener) {
        setLeftTextView(getResources().getString(resId), listener);
    }

    public void enableRightTextView() {
        mRightTv.setEnabled(true);
    }

    public void disableRightTextView() {
        mRightTv.setEnabled(false);
    }

    /**
     * 设置右侧文字按钮的文字资源
     */
    public void setRightText(int resId) {
        setRightText(getResources().getString(resId));
    }

    /**
     * 设置左侧文字按钮的文字资源
     */
    public void setLeftText(int resId) {
        setLeftText(getResources().getString(resId));
    }

    /**
     * 设置右侧文字按钮的文字
     */
    public void setRightText(String text) {
        mRightTv.setVisibility(VISIBLE);
        mRightTv.setText(text);
    }

    /**
     * 设置右侧文字按钮的文字
     */
    public void setLeftText(String text) {
        mLeftTv.setVisibility(VISIBLE);
        mLeftTv.setText(text);
    }

}
