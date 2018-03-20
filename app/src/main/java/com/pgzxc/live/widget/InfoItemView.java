package com.pgzxc.live.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pgzxc.live.R;


/**
 * 我的 ui中item
 */
public class InfoItemView extends FrameLayout {

    ImageView mIvMyLeftIcon;
    TextView mTvMyLeftText;
    RelativeLayout mRlMyLeftText;
    EditText mTvMyRightText;
    ImageView mIvMyArrow;
    private Context mContext;
    boolean isNeedEnglish;
    private DigitsKeyListener mDigitsKeyListener;
    private boolean mIsEditable = true;

    public InfoItemView(Context context) {
        this(context, null);
    }

    public InfoItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        //初始化键盘输入限制
        mDigitsKeyListener = new DigitsKeyListener() {
            @Override
            protected char[] getAcceptedChars() {
                char[] data;
                if (isNeedEnglish) {
                    //可以输入英文和数字
                    data = getStringData(R.string.login_only_can_input).toCharArray();
                } else {
                    //只能输入数字
                    data = getStringData(R.string.login_only_can_number).toCharArray();
                }
                return data;
            }
        };
        initView(attrs);
    }

    /**
     * 获取右边文本框的文本内容
     *
     * @return
     */
    public String getRightText() {
        return mTvMyRightText.getText().toString().trim();
    }

    /**
     * 设置右边输入框的文本提示
     *
     * @param hint
     */
    public void setRightHint(String hint) {
        mTvMyRightText.setHint(hint);
    }

    private void initView(AttributeSet attrs) {
        View view = View.inflate(mContext, R.layout.view_infoitem, this);
        mIvMyLeftIcon = (ImageView) findViewById(R.id.iv_my_left_icon);
        mTvMyLeftText = (TextView) findViewById(R.id.tv_my_left_text);
        mRlMyLeftText = (RelativeLayout) findViewById(R.id.rl_my_left_text);
        mTvMyRightText = (EditText) findViewById(R.id.tv_my_right_text);
        mIvMyArrow = (ImageView) findViewById(R.id.iv_my_arrow);

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.MyTextItemView);
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int index = typedArray.getIndex(i);
            if (index == R.styleable.MyTextItemView_exist_icon) {
                int isExist = typedArray.getInt(index, 0);
                if (isExist == 1) {
                    mIvMyLeftIcon.setVisibility(GONE);
                }
            } else if (index == R.styleable.MyTextItemView_exist_right_text) {
                int isExist = typedArray.getInt(index, 0);
                if (isExist == 1) {
                    mTvMyRightText.setVisibility(VISIBLE);
                } else {
                    mTvMyRightText.setVisibility(GONE);
                }
            } else if (index == R.styleable.MyTextItemView_left_icon) {
                Drawable drawable = typedArray.getDrawable(index);
                mIvMyLeftIcon.setImageDrawable(drawable);

            } else if (index == R.styleable.MyTextItemView_right_text) {
                String right = typedArray.getString(index);
                mTvMyRightText.setText(right);

            } else if (index == R.styleable.MyTextItemView_left_text) {
                String left = typedArray.getString(index);
                mTvMyLeftText.setText(left);

            } else if (index == R.styleable.MyTextItemView_editorable) {
                int isExist = typedArray.getInt(index, 0);
                if (isExist == 1) {
                    mIsEditable = false;
                    mTvMyRightText.setKeyListener(null);
                }
            } else if (index == R.styleable.MyTextItemView_exist_arrow) {
                int isExist = typedArray.getInt(index, 0);
                if (isExist == 1) {
                    mIvMyArrow.setVisibility(VISIBLE);
                } else {
                    mIvMyArrow.setVisibility(GONE);
                }
            } else if (index == R.styleable.MyTextItemView_tipText) {
                String hint = typedArray.getString(index);
                mTvMyRightText.setHint(hint);
            } else if (index == R.styleable.MyTextItemView_inputType_m) {
                int type = typedArray.getInt(index, 0);
                if (type == 1) {
                    isNeedEnglish = false;
                    mTvMyRightText.setKeyListener(mDigitsKeyListener);
                } else if (type == 2) {
                    isNeedEnglish = true;
                    mTvMyRightText.setKeyListener(mDigitsKeyListener);
                }
            } else if (index == R.styleable.MyTextItemView_left_text_color) {
                int color = typedArray.getColor(index, getResources().getColor(R.color.black));
                mTvMyLeftText.setTextColor(color);
            } else if (index == R.styleable.MyTextItemView_right_text_color) {
                int color = typedArray.getColor(index, getResources().getColor(R.color.black));
                mTvMyRightText.setTextColor(color);
            } else if (index == R.styleable.MyTextItemView_text_gravity) {
                int gravity = typedArray.getInt(index, 0);
                if (gravity == 1) {
                    mTvMyRightText.setGravity(Gravity.LEFT);
                }
            }
        }
        typedArray.recycle();
    }

    public void setRightEditable(boolean isEditable) {
        if (!isEditable) {
            mTvMyRightText.setKeyListener(null);
        }
    }

    /**
     * 设置右边文本的颜色
     *
     * @param color
     */
    public void setRightTextColor(int color) {
        mTvMyRightText.setTextColor(color);
    }

    /**
     * 设置左边文本的颜色
     *
     * @param color
     */
    public void setLeftTextColor(int color) {
        mTvMyLeftText.setTextColor(color);
    }

    private String getStringData(int id) {
        return mContext.getResources().getString(id);
    }

    /**
     * 设置左边的文字
     *
     * @param text
     */
    public void setLeftText(CharSequence text) {
        mTvMyLeftText.setText(text);
    }

    /**
     * 设置右边的文字
     *
     * @param text
     */
    public void setRightText(String text) {
        mTvMyRightText.setText(text);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!mIsEditable) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
