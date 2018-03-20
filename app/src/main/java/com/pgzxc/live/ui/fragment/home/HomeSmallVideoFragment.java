package com.pgzxc.live.ui.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import com.github.florent37.viewanimator.ViewAnimator;
import com.pgzxc.live.R;
import com.pgzxc.live.api.reposity.HomeSmallVideoRepority;
import com.pgzxc.live.data.constants.SpanCountType;
import com.pgzxc.live.databinding.FragmentHomeSmallVideoBinding;
import com.pgzxc.live.ui.adapter.HomeSmallVideoAdapter;
import com.pgzxc.live.ui.base.BaseFragment;
import org.kymjs.kjframe.ui.ViewInject;
import java.util.Random;

/**
 * 首页-小视频
 */

public class HomeSmallVideoFragment extends BaseFragment<FragmentHomeSmallVideoBinding> {
    public static HomeSmallVideoFragment newInstance() {

        Bundle args = new Bundle();
        HomeSmallVideoFragment fragment = new HomeSmallVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_home_small_video;
    }

    @Override
    protected void initData() {
        super.initData();
        mDataBinding.recycleViewHomeSmallVideo.setLayoutManager(new GridLayoutManager(getActivity(), SpanCountType.TWO));
        HomeSmallVideoAdapter smallVideoAdapter = new HomeSmallVideoAdapter(getActivity(), R.layout.adapter_home_samll_video,
                new HomeSmallVideoRepority(getActivity()).getSmallVideoRepority());
        mDataBinding.recycleViewHomeSmallVideo.setAdapter(smallVideoAdapter);
        smallVideoAdapter.setOnItemClickListener((adapter, view, position) -> {
            ViewInject.toast(getActivity(), "第" + position);
        });
    }

    @Override
    protected void setListener() {
        super.setListener();

        mDataBinding.refreshLayout.setOnRefreshListener(refreshlayout -> {
            refreshlayout.finishRefresh();
            initData();
            showToast(new Random().nextInt(10) + 1);
        });
    }

    private void showToast(int num) {
        mDataBinding.tvToast.setText(String.format(getResources().getString(R.string.live_toast), num + ""));
        mDataBinding.rlTopToast.setVisibility(View.VISIBLE);
        ViewAnimator.animate(mDataBinding.rlTopToast)
                .newsPaper()
                .duration(1000)
                .start()
                .onStop(() -> ViewAnimator.animate(mDataBinding.rlTopToast)
                        .bounceOut()
                        .duration(1000)
                        .start());
    }

}
