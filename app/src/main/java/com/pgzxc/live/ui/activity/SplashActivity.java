package com.pgzxc.live.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.pgzxc.live.utils.ActivityUtils;

/**
 * 启动页面，防止白屏
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.skipActivity(SplashActivity.this, WelcomeActivity.class);
    }
}
