package com.pgzxc.live.eventbus;

/**
 * EventBus事件类型
 */

public interface AnyEventType {
    int PLAY_START = 1;//开始播放
    int GIF_SELECT = 2;//礼物被选中
    int CHAT_FRAGMENT_LIVE_CLOSE = 3;//播放面板关闭直播间
    int NO_FRAGMENT_LIVE_CLOSE = 4;//透明面板关闭直播间
    int CHAT_LIVE_ROOM = 5;//
}
