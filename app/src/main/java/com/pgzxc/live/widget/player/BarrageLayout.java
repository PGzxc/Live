package com.pgzxc.live.widget.player;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.florent37.viewanimator.AnimationBuilder;
import com.github.florent37.viewanimator.ViewAnimator;
import com.pgzxc.live.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ========================================
 * 描 述：弹幕布局
 * ========================================
 */
public class BarrageLayout extends LinearLayout {
    @BindView(R.id.rel_barrage_one)
    RelativeLayout RelBarrageOne;
    @BindView(R.id.rel_barrage_two)
    RelativeLayout RelBarrageTwo;

    int count = 0;
    int screenWidth;

    public BarrageLayout(Context context) {
        super(context);
        init(context, null);
    }

    public BarrageLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BarrageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            final View barrageView = (View) msg.obj;
            if (what == 0) {
                RelBarrageOne.addView(barrageView);
            } else {
                RelBarrageTwo.addView(barrageView);
            }
            barrageView.measure(0, 0);
            int barrageWidth = barrageView.getMeasuredWidth();
            AnimationBuilder builder = ViewAnimator.animate(barrageView).translationX(screenWidth, -barrageWidth).interpolator(new LinearInterpolator()).duration(5000);
            builder.onStop(() -> removeView(barrageView));
            builder.start();
        }
    };

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_barrage_layout, this);
        ButterKnife.bind(this);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();

    }

    //    public synchronized void addBarrage(String msgContent, String username) {
//        int i = count % 2;
//        Message message = handler.obtainMessage();
//        message.what = i;
//        message.obj = newBarrageView(msgContent, username);
//        handler.sendMessage(message);
//        count++;
//    }
    public synchronized void addBarrage(String msgContent, String username, int aratar) {
        int i = count % 2;
        Message message = handler.obtainMessage();
        message.what = i;
        message.obj = newBarrageView(msgContent, username, aratar);
        handler.sendMessage(message);
        count++;
    }

    private View newBarrageView(String msgContent, String username, int avatar) {
        View barrageView = LayoutInflater.from(getContext()).inflate(R.layout.live_widget_right_barrage, null);
        TextView nameView = barrageView.findViewById(R.id.name);
        TextView contentView = barrageView.findViewById(R.id.content);
        ImageView imagAvatar = barrageView.findViewById(R.id.avatar);
        nameView.setText(username);
        contentView.setText(msgContent);
        imagAvatar.setImageResource(avatar);
        return barrageView;
    }
}
