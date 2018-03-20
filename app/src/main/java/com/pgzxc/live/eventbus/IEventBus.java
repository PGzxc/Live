package com.pgzxc.live.eventbus;

/**
 * EventBus接收事件接口
 * @param <T> 传递数据类型
 */
public interface IEventBus<T> {

    void onEvent(AnyEvent<T> event);//接收事件方法

}
