package com.pgzxc.live.ui.base;

/**
 * Created by admin on 2018/1/22.
 */

public interface BaseView {
    //显示进度中
    void showLoading();

    //显示请求成功
    void showSuccess();

    //失败重试
    void showFaild();

    //显示当前网络不可用
    void showNoNet();

    //重试
    void onRetry();
}
