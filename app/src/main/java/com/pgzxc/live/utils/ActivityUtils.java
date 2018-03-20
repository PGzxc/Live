package com.pgzxc.live.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;

import com.pgzxc.live.R;
import com.pgzxc.live.api.req.IntentKey;
import com.pgzxc.live.data.bean.LiveRoom;

/**
 * Activity跳转
 */

public class ActivityUtils {

    public static void skipActivity(Activity activity, Class cls) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        activity.startActivity(intent);
        activity.finish();
        activity.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    public static void startActivity(Activity activity, Class cls) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    /**
     * 启动activity
     */
    public static void startActivity(Activity activity, Class<?> clazz, LiveRoom liveRoom) {
        Intent intent = new Intent(activity, clazz);
        intent.putExtra(IntentKey.LiveRoom, liveRoom);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }
}
