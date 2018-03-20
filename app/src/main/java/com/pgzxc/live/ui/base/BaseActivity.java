package com.pgzxc.live.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pgzxc.live.R;
import com.pgzxc.live.widget.SimpleMultiStateView;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity基类
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends SupportActivity implements BaseView {
    protected T mDataBinding;//ViewDataBinding引用
    protected BaseActivity activity;
    Unbinder unbinder;
    @Nullable
    @BindView(R.id.SimpleMultiStateView)
    SimpleMultiStateView mSimpleMultiStateView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        int layoutId = getActivityLayoutId();
        if (layoutId != 0) {//如果设置了布局Id
            mDataBinding = DataBindingUtil.setContentView(this, layoutId);//解析布局
        }
        unbinder = ButterKnife.bind(this, activity);
        initStateView();
        initWidget();
        initData();
        setListener();
        MobclickAgent.setScenarioType(activity, MobclickAgent.EScenarioType.E_UM_NORMAL);
    }


    /**
     * 初始化控件属性
     */
    public void initWidget() {
    }

    /**
     * 初始化数据
     */
    public void initData() {

    }

    /**
     * 设置监听
     */
    public void setListener() {

    }

    protected abstract int getActivityLayoutId();

    private void initStateView() {
        if (mSimpleMultiStateView == null) return;
        mSimpleMultiStateView.setEmptyResource(R.layout.view_empty)
                .setRetryResource(R.layout.view_retry)
                .setLoadingResource(R.layout.view_loading)
                .setNoNetResource(R.layout.view_nonet)
                .build()
                .setonReLoadlistener(this::onRetry);
    }

    @Override
    public void showLoading() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showLoadingView();
        }
    }

    @Override
    public void showSuccess() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showContent();
        }
    }

    @Override
    public void showFaild() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showErrorView();
        }
    }

    @Override
    public void showNoNet() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showNoNetView();
        }
    }

    @Override
    public void onRetry() {
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getSimpleName());
        MobclickAgent.onResume(activity);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
        MobclickAgent.onPause(activity);
    }
}
