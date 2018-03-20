package com.pgzxc.live.listener;

import android.view.View;

/**
 * ========================================
 * 描 述：发起直播点击监听
 * ========================================
 */
public interface OnLiveListener {

    /**
     * 点击切换摄像头
     */
    void onCamreClick(View view);

    /**
     * 点击切换闪光灯
     */
    void onLightClick(View view);

    /**
     * 点击切换声音
     */
    void onVoiceClick(View view);
}