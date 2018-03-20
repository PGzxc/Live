package com.pgzxc.live.eventbus;

/**
 * EventBus 事件定义
 * @param <T> 传递数据类型
 */
public class AnyEvent<T> {

    private int event;//事件类型
    private T data;//传递数据

    public AnyEvent(int event, T data){
        this.event = event;
        this.data = data;
    }

    public int getEvent() {
        return event;
    }

    public T getData() {
        return data;
    }

    public void setEvent(int event) {

        this.event = event;
    }

    public void setData(T data) {
        this.data = data;
    }
}
