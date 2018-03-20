package com.pgzxc.live.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pgzxc.live.R;

import org.kymjs.kjframe.widget.RoundImageView;

/**
 * 个人界面头部
 */

public class HeadInfoView extends FrameLayout {
    RoundImageView imageViewHead;
    TextView tvUerName;
    AppCompatImageView imageViewSex;
    AppCompatImageView imageViewRankLev;
    AppCompatImageView imageViewLiveLev;
    TextView tvInke;
    TextView tvInkeNum;
    TextView tvEdit;
    Typeface user_Typeface;
    Typeface other_Typeface;
    Context mContext;

    public HeadInfoView(@NonNull Context context) {
        this(context, null);
    }

    public HeadInfoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        View view = View.inflate(mContext, R.layout.view_head_info, this);
        imageViewHead = view.findViewById(R.id.iv_head_icon);
        tvUerName = view.findViewById(R.id.tv_user_text);
        imageViewSex = view.findViewById(R.id.iv_sex);
        imageViewRankLev = view.findViewById(R.id.iv_rank_lev);
        imageViewLiveLev = view.findViewById(R.id.iv_live_lev);
        tvInkeNum = view.findViewById(R.id.tv_inke_number);
        tvInke = view.findViewById(R.id.tv_inke);
        tvEdit = view.findViewById(R.id.tv_eidt);
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.HeadInfoView);
        user_Typeface = getTypeFaceFromAsset(typedArray.getString(R.styleable.HeadInfoView_user_typeFace));
        other_Typeface = getTypeFaceFromAsset(typedArray.getString(R.styleable.HeadInfoView_other_typeFace));
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.HeadInfoView_head_icon:
                    Drawable drawableHead = typedArray.getDrawable(index);
                    imageViewHead.setImageDrawable(drawableHead);
                    break;
                case R.styleable.HeadInfoView_user_text:
                    String user_text = typedArray.getString(index);
                    tvUerName.setText(user_text);
                    break;
                case R.styleable.HeadInfoView_user_sex:
                    int userSex = typedArray.getInt(index, 0);
                    if (userSex == 0) {
                        imageViewSex.setImageResource(R.drawable.ic_male);
                    } else {
                        imageViewSex.setImageResource(R.drawable.ic_female);
                    }
                    break;
                case R.styleable.HeadInfoView_user_typeFace:
                    tvUerName.setTypeface(user_Typeface);
                    break;
                case R.styleable.HeadInfoView_other_typeFace:
                    tvInke.setTypeface(other_Typeface);
                    tvInkeNum.setTypeface(other_Typeface);
                    tvEdit.setTypeface(other_Typeface);
                    break;
                case R.styleable.HeadInfoView_rank_icon:
                    Drawable drawableRank = typedArray.getDrawable(index);
                    imageViewRankLev.setImageDrawable(drawableRank);
                    break;
                case R.styleable.HeadInfoView_live_icon:
                    Drawable drawableLive = typedArray.getDrawable(index);
                    imageViewLiveLev.setImageDrawable(drawableLive);
                    break;
                case R.styleable.HeadInfoView_inke_number:
                    String inkeNum = typedArray.getString(index);
                    tvInkeNum.setText(inkeNum);
                    break;
                default:
                    break;
            }
        }
        typedArray.recycle();
    }


    public void setImageViewHead(int imageResource) {
        imageViewHead.setImageResource(imageResource);
    }

    public void setTvUerName(String uerName) {
        tvUerName.setText(uerName);
    }

    public void setImageViewSex(int imageResource) {
        imageViewSex.setImageResource(imageResource);
    }

    public void setImageViewRankLev(int imageResource) {
        imageViewRankLev.setImageResource(imageResource);
    }

    public void setImageViewLiveLev(int imageResource) {
        imageViewLiveLev.setImageResource(imageResource);
    }

    public void setTvInkeNum(String inkeNum) {
        tvInkeNum.setText(inkeNum);
    }

    private Typeface getTypeFaceFromAsset(String fontPath) {
        if (fontPath != null) {
            return Typeface.createFromAsset(
                    getContext().getAssets(), fontPath);
        }

        return null;
    }
}
