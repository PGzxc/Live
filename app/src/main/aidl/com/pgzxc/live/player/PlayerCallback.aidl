package com.pgzxc.live.player;

interface PlayerCallback {
    void onUiUpdated();
    void onPlayStatusChanged(int status);
}