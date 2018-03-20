package com.pgzxc.live.api.resp;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by admin on 2018/1/22.
 */
public abstract class SimpleSubscriber<T> implements Subscriber<T> {


    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}

