package com.pgzxc.live.player;
import com.pgzxc.live.player.PlayerCallback;
import com.pgzxc.live.player.PlayRecord;

interface IPlayerAidl {

    void initUI();
    void play(in PlayRecord playRecord);
    boolean isPlaying();
    void stop();
    void registerCallback(PlayerCallback callback);
    void unregisterCallback(PlayerCallback callback);

}
