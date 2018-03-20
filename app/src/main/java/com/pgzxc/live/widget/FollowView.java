package com.pgzxc.live.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pgzxc.live.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 关注与粉丝
 */

public class FollowView extends LinearLayout {

    @BindView(R.id.tv_follow_num)
    TextView tvFollowNum;
    @BindView(R.id.tv_fans_num)
    TextView tvFansNum;
    TextView tvMyFollow;
    TextView tvMyFans;
    Context mContext;

    @BindView(R.id.rl_follow)
    RelativeLayout rlFollow;

    @BindView(R.id.rl_fans)
    RelativeLayout rlFans;

    public FollowView(@NonNull Context context) {
        this(context, null);
    }

    public FollowView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        View view = View.inflate(mContext, R.layout.view_follow, this);
        ButterKnife.bind(view);
        tvFollowNum = view.findViewById(R.id.tv_follow_num);
        tvFansNum = view.findViewById(R.id.tv_fans_num);
        tvMyFollow = view.findViewById(R.id.tv_my_follow);
        tvMyFans = view.findViewById(R.id.tv_my_fans);

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.FollowView);

        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.FollowView_follow_num:
                    String followNum = typedArray.getString(R.styleable.FollowView_follow_num);
                    tvFollowNum.setText(followNum);
                    break;
                case R.styleable.FollowView_fans_num:
                    String fansNum = typedArray.getString(R.styleable.FollowView_fans_num);
                    tvFansNum.setText(fansNum);
                    break;
                case R.styleable.FollowView_follow_typeFace:
                    String followTypeFace = typedArray.getString(R.styleable.FollowView_follow_typeFace);
                    Typeface typeFaceNum = getTypeFaceFromAsset(followTypeFace);
                    tvFansNum.setTypeface(typeFaceNum);
                    tvFollowNum.setTypeface(typeFaceNum);
                    break;
                case R.styleable.FollowView_content_typeFace:
                    String otherTypeFace = typedArray.getString(R.styleable.FollowView_content_typeFace);
                    Typeface typeFace = getTypeFaceFromAsset(otherTypeFace);
                    tvMyFollow.setTypeface(typeFace);
                    tvMyFans.setTypeface(typeFace);
                    break;
                default:
                    break;
            }
        }
        typedArray.recycle();
    }

    private Typeface getTypeFaceFromAsset(String fontPath) {
        if (fontPath != null) {
            return Typeface.createFromAsset(
                    getContext().getAssets(), fontPath);
        }
        return null;
    }


    /**
     * 设置关注人数
     *
     * @param followNum
     */
    public void setFollowNum(int followNum) {
        tvFollowNum.setText(String.valueOf(followNum));
    }

    /**
     * 设置粉丝人数
     * @param fansNum
     */
    public void setFansNum(int fansNum) {
        tvFansNum.setText(String.valueOf(fansNum));
    }

    public RelativeLayout getRlFollow() {
        return rlFollow;
    }
    public RelativeLayout getRlFans() {
        return rlFans;
    }


}
