package com.pgzxc.live.ui.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.pgzxc.live.R;
import com.pgzxc.live.api.reposity.HomeRecommendRepority;
import com.pgzxc.live.data.bean.LiveRoom;
import com.pgzxc.live.data.constants.SpanCountType;
import com.pgzxc.live.databinding.FragmentHomeRecommendBinding;
import com.pgzxc.live.ui.activity.player.PlayerLiveActivity;
import com.pgzxc.live.ui.adapter.HomeRecommendAdapter;
import com.pgzxc.live.ui.base.BaseFragment;
import com.pgzxc.live.ui.holder.LocalImageHolderView;
import com.pgzxc.live.utils.ActivityUtils;
import org.kymjs.kjframe.ui.ViewInject;
import java.util.Random;


/**
 * 首页推荐
 */

public class HomeRecommendFragment extends BaseFragment<FragmentHomeRecommendBinding> {
    public static HomeRecommendFragment newInstance() {

        Bundle args = new Bundle();
        HomeRecommendFragment fragment = new HomeRecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_home_recommend;
    }

    @Override
    protected void initData() {
        super.initData();
        mDataBinding.convenientBanner.setPages(() -> new LocalImageHolderView(), new HomeRecommendRepority(getActivity()).getLocalImages())
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused});

        mDataBinding.recycleViewRecommend.setLayoutManager(new GridLayoutManager(getActivity(), SpanCountType.TWO));
        HomeRecommendAdapter homeRecommendAdapter = new HomeRecommendAdapter(getActivity(), R.layout.adapter_home_recommend, new HomeRecommendRepority(getActivity()).getHomeRecommendRepority());
        mDataBinding.recycleViewRecommend.setAdapter(homeRecommendAdapter);
        mDataBinding.recycleViewRecommend.setNestedScrollingEnabled(false);
        homeRecommendAdapter.setOnItemClickListener((adapter, view, position) -> ActivityUtils.startActivity(getActivity(), PlayerLiveActivity.class, (LiveRoom) adapter.getItem(position)));
    }

    @Override
    protected void setListener() {
        super.setListener();
        mDataBinding.refreshLayout.setOnRefreshListener(refreshlayout -> {
            refreshlayout.finishRefresh();
            initData();
            showToast(new Random().nextInt(10)+1);
        });
        mDataBinding.convenientBanner.setOnItemClickListener(position -> ViewInject.toast(getActivity(), "第" + position + "个位置"));

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
    public void onResume() {
        super.onResume();
        mDataBinding.convenientBanner.startTurning(3000);
    }

    @Override
    public void onPause() {
        super.onPause();
        mDataBinding.convenientBanner.stopTurning();
    }
}
