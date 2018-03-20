package com.pgzxc.live.listener;

/**
 * ========================================
 * 描 述：房间中监听事件
 * ========================================
 */
public interface MessageViewListener {

    /**
     * 消息发送监听
     */
    void onMessageSend(String content);

    /**
     * 隐藏输入面板
     */
    void onHiderBottomBar();

    /**
     * 消息点击监听
     *
     * @param id  点击回传内容随便定义满足int类型
     * @param sid 点击回传内容随便定义满足String类型
     */
    void onItemClickListener(int id, String sid);

    /**
     * 消息加载更多
     */
    void onLoadMore();
}