package com.pgzxc.live.listener;

import com.pgzxc.live.data.bean.LiveRoom;

/**
 * 监听PlayerLive获取数据
 */

public interface LiveRoomListener {

    void onIntentReceive(LiveRoom liveRoom);
}
