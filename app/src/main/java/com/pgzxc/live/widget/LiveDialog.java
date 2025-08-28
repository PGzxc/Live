package com.pgzxc.live.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.pgzxc.live.R;
import com.pgzxc.live.utils.KickBackAnimator;
import org.kymjs.kjframe.ui.ViewInject;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 直播弹出对话框
 */

public class LiveDialog extends AlertDialog implements OnClickListener {
    Context mContext;
    private RelativeLayout rootView;
    private LinearLayout linearAction;

    public LiveDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        Window win = this.getWindow();
        win.setGravity(Gravity.BOTTOM);
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
        win.setBackgroundDrawableResource(R.color.transparent);

    }

    public void showDialog() {
        show();
        showAnimation(linearAction);
    }

    private void initView() {
        setCanceledOnTouchOutside(true);
        View view = View.inflate(mContext, R.layout.dialog_live, null);
        rootView = view.findViewById(R.id.contentView);
        linearAction = view.findViewById(R.id.ll_action);
        view.findViewById(R.id.tv_live).setOnClickListener(this);
        view.findViewById(R.id.tv_audio).setOnClickListener(this);
        view.findViewById(R.id.tv_video).setOnClickListener(this);
        view.findViewById(R.id.ll_close).setOnClickListener(this);
        this.setContentView(view);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ll_close) {
            closeAnimation(linearAction);
        } else if (id == R.id.tv_live) {
            ViewInject.toast(mContext, "直播");
            closeAnimation(linearAction);
        } else if (id == R.id.tv_audio) {
            ViewInject.toast(mContext, "电台");
            closeAnimation(linearAction);
        } else if (id == R.id.tv_video) {
            ViewInject.toast(mContext, "小视频");
            closeAnimation(linearAction);
        }

//        switch (view.getId()) {
//            case R.id.ll_close:
//                closeAnimation(linearAction);
//                break;
//            case R.id.tv_live:
//                ViewInject.toast(mContext, "直播");
//                closeAnimation(linearAction);
//                break;
//            case R.id.tv_audio:
//                ViewInject.toast(mContext, "电台");
//                closeAnimation(linearAction);
//                break;
//            case R.id.tv_video:
//                ViewInject.toast(mContext, "小视频");
//                closeAnimation(linearAction);
//                break;
//        }

    }

    /**
     * 显示进入动画效果
     *
     * @param layout
     */
    private void showAnimation(ViewGroup layout) {

        //遍历根试图下的一级子试图
        for (int i = 0; i < layout.getChildCount(); i++) {
            final View child = layout.getChildAt(i);
            //忽略关闭组件
            if (child.getId() == R.id.ll_close) {
                continue;
            }
            //设置所有一级子试图的点击事件
            child.setOnClickListener(this);
            child.setVisibility(View.INVISIBLE);
            //延迟显示每个子试图(主要动画就体现在这里)
            Observable.timer(i * 50, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
                        child.setVisibility(View.VISIBLE);
                        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 600, 0);
                        fadeAnim.setDuration(300);
                        KickBackAnimator kickAnimator = new KickBackAnimator();
                        kickAnimator.setDuration(150);
                        fadeAnim.setEvaluator(kickAnimator);
                        fadeAnim.start();
                    });
        }
    }

    /**
     * 关闭动画效果
     *
     * @param layout
     */
    private void closeAnimation(ViewGroup layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            final View child = layout.getChildAt(i);
            if (child.getId() == R.id.ll_close) {
                continue;
            }
            Observable.timer((layout.getChildCount() - i - 1) * 30, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
                        child.setVisibility(View.VISIBLE);
                        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 0, 600);
                        fadeAnim.setDuration(100);
                        KickBackAnimator kickAnimator = new KickBackAnimator();
                        kickAnimator.setDuration(100);
                        fadeAnim.setEvaluator(kickAnimator);
                        fadeAnim.start();
                        fadeAnim.addListener(new Animator.AnimatorListener() {

                            @Override
                            public void onAnimationStart(Animator animation) {
                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                child.setVisibility(View.INVISIBLE);

                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {
                            }
                        });
                    });
            Observable.timer((layout.getChildCount() - i) * 30 + 80, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> dismiss());
        }
    }
}
