package com.pgzxc.live.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.pgzxc.live.R;
import com.pgzxc.live.widget.SimpleMultiStateView;
import com.umeng.analytics.MobclickAgent;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Fragment基类
 */

public abstract class BaseFragment<T extends ViewDataBinding> extends SupportFragment implements BaseView {
    protected T mDataBinding;//dataBinding实例

    private boolean hasInitData = false;//是否已加载过一次数据
    Unbinder unbinder;

    @BindView(R.id.SimpleMultiStateView)
    SimpleMultiStateView mSimpleMultiStateView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        if (mDataBinding == null) {
            mDataBinding = DataBindingUtil.inflate(inflater, getFragmentLayoutId(), null, false);
            unbinder = ButterKnife.bind(getActivity());
            initWidget();//初始化控件
            //initData();
            initStateView();
            //lazyLoad();//加载数据
            setListener();
            MobclickAgent.setScenarioType(getActivity(), MobclickAgent.EScenarioType.E_UM_NORMAL);
        }
        return mDataBinding.getRoot();
    }

    protected void setListener() {

    }

    /**
     * 初始化控件的空实现
     */
    protected void initWidget() {

    }

    @Override
    public void onLazyInitView(Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initData();
    }

    protected void initData() {

    }

    /**
     * 获取fragment布局文件Id，没有时返回0
     *
     * @return fragment布局文件Id
     */
    public abstract int getFragmentLayoutId();

    /**
     * 初始化数据的空实现，只加载一次数据的时候重写
     */
    protected void initDataOnlyOnce() {

    }

    /**
     * 初始化数据的空实现，fragment切换加载数据的时候重写
     */
    protected void initDataOnUserVisible() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();//加载数据
    }

    protected void lazyLoad() {
        if (mDataBinding != null && getUserVisibleHint()) {//dataBingding不为空且用户可见
            initDataOnUserVisible();//用户可见时加载数据
            if (!hasInitData) {
                hasInitData = true;
                initDataOnlyOnce();//只加载一次数据
            }
        }
    }

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
        initDataOnUserVisible();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getSimpleName());
        MobclickAgent.onResume(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
        MobclickAgent.onPause(getActivity());
    }
}
