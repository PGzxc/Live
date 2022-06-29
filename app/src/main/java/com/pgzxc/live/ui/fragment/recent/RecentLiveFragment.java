package com.pgzxc.live.ui.fragment.recent;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.florent37.viewanimator.ViewAnimator;
import com.pgzxc.live.R;
import com.pgzxc.live.api.reposity.RecentLiveRepority;
import com.pgzxc.live.data.bean.LiveRoom;
import com.pgzxc.live.data.constants.SpanCountType;
import com.pgzxc.live.databinding.FragmentRecentLiveBinding;
import com.pgzxc.live.ui.activity.player.PlayerLiveActivity;
import com.pgzxc.live.ui.adapter.RecentLiveAdapter;
import com.pgzxc.live.ui.base.BaseFragment;
import com.pgzxc.live.utils.ActivityUtils;
import com.sunfusheng.marqueeview.MarqueeView;

import org.kymjs.kjframe.ui.ViewInject;

import java.util.Random;


/**
 * 附近-直播
 */

public class RecentLiveFragment extends BaseFragment<FragmentRecentLiveBinding> {

    MarqueeView marqueeView;

    /**
     * 创建附近-直播
     *
     * @return
     */
    public static RecentLiveFragment newInstance() {
        Bundle args = new Bundle();
        RecentLiveFragment fragment = new RecentLiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_recent_live;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

    }

    @Override
    protected void initData() {
        super.initData();
        mDataBinding.recycleRecentLiv.setLayoutManager(new GridLayoutManager(getActivity(), SpanCountType.THREE));
        RecentLiveAdapter recentLiveAdapter = new RecentLiveAdapter(R.layout.adapter_recent_live,
                new RecentLiveRepority(getActivity()).getFollowRepority());
        recentLiveAdapter.addHeaderView(getHeaderView(view -> ViewInject.toast(getActivity(), "头部")));
        mDataBinding.recycleRecentLiv.setAdapter(recentLiveAdapter);
        marqueeView.startWithList(new RecentLiveRepority(getActivity()).getMarqueenData());
        recentLiveAdapter.setOnItemClickListener((adapter, view, position) -> {
            ActivityUtils.startActivity(getActivity(), PlayerLiveActivity.class, (LiveRoom) adapter.getItem(position));
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

    @Override
    public void onResume() {
        super.onResume();
        if (marqueeView != null) {
            marqueeView.startFlipping();
        }
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

    @Override
    public void onStop() {
        super.onStop();
        marqueeView.stopFlipping();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (marqueeView != null) {
            marqueeView = null;
        }
    }

    private View getHeaderView(View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.head_view_recent_live, (ViewGroup) mDataBinding.recycleRecentLiv.getParent(), false);
        marqueeView = view.findViewById(R.id.marqueeView);
        view.setOnClickListener(listener);
        return view;
    }
}
