package com.pgzxc.live.ui.fragment.recent;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pgzxc.live.R;
import com.pgzxc.live.api.reposity.RecentSmallVideoRepority;
import com.pgzxc.live.data.constants.SpanCountType;
import com.pgzxc.live.databinding.FragmentRecentSamllVideoBinding;
import com.pgzxc.live.ui.adapter.RecentSmallVideoAdapter;
import com.pgzxc.live.ui.base.BaseFragment;

import org.kymjs.kjframe.ui.ViewInject;


/**
 * 附近-小视频
 */

public class RecentSmallVideoFragment extends BaseFragment<FragmentRecentSamllVideoBinding> {
    public static RecentSmallVideoFragment newInstance() {

        Bundle args = new Bundle();
        RecentSmallVideoFragment fragment = new RecentSmallVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_recent_samll_video;
    }

    @Override
    protected void initData() {
        super.initData();
        mDataBinding.recycleViewSmallVideo.setLayoutManager(new GridLayoutManager(getActivity(), SpanCountType.TWO));
        RecentSmallVideoAdapter smallVideoAdapter = new RecentSmallVideoAdapter(R.layout.adapter_recent_samll_video,
                new RecentSmallVideoRepority(getActivity()).getFollowVideoRepority());
        mDataBinding.recycleViewSmallVideo.setAdapter(smallVideoAdapter);
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
        });
    }
}
