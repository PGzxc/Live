package com.pgzxc.live.ui.application;

import android.app.Application;
import android.content.Context;

import com.hyphenate.easeui.EaseUI;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.pgzxc.live.utils.Utils;

/**
 * Application
 */

public class LiveApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EaseUI.getInstance().init(this, null);
        Logger.addLogAdapter(new AndroidLogAdapter());
        Utils.init(this);
    }
}
