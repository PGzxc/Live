package com.pgzxc.live.listener;

/**
 * Created by admin on 2018/1/31.
 */

public class PlayEvent {

    public interface PlayCallBack {
        int EVENT_PLAY_START = 1;
        int EVENT_PLAY_PAUSE = 2;
        int EVENT_PLAY_STOP = 3;
        int EVENT_PLAY_COMPLETION = 4;
        int EVENT_PLAY_ERROR = 5;
        int EVENT_PLAY_DESTORY = 6;
        int EVENT_PLAY_RESUME = 7;
        int EVENT_PLAY_SEEK_COMPLETED = 8;
        int EVENT_PLAY_INFO_BUFFERING_START = 9;
        int EVENT_PLAY_INFO_BUFFERING_END = 10;
        int EVENT_PLAY_TOGGLE_DEFINITION = 11;
        int EVENT_PLAY_INFO_VIDEO_ROTATE_CHANGED = 12;
        void onEvent(int what, String message);

    }

}
