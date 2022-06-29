package com.pgzxc.live.ui.fragment;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.florent37.viewanimator.ViewAnimator;
import com.pgzxc.live.R;
import com.pgzxc.live.api.reposity.FollowRepority;
import com.pgzxc.live.data.bean.LiveRoom;
import com.pgzxc.live.data.constants.AdapterType;
import com.pgzxc.live.data.constants.SpanCountType;
import com.pgzxc.live.databinding.FragmentFollowBinding;
import com.pgzxc.live.ui.activity.player.PlayerLiveActivity;
import com.pgzxc.live.ui.adapter.FollowAdapter;
import com.pgzxc.live.ui.base.BaseFragment;
import com.pgzxc.live.utils.ActivityUtils;

import org.kymjs.kjframe.ui.ViewInject;

import java.util.List;
import java.util.Random;


/**
 * 关注
 */

public class FollowFragment extends BaseFragment<FragmentFollowBinding> {


    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_follow;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mDataBinding.titleBar.setLeftImageView(R.drawable.ic_title_add, view -> {
            ViewInject.toast(getActivity(), "添加");
        });
        mDataBinding.titleBar.setTitleTextView("关注");
        mDataBinding.titleBar.setRightImageView(R.drawable.ic_title_msg, view -> {
            ViewInject.toast(getActivity(), "消息");
        });
    }


    @Override
    protected void initData() {
        super.initData();

        List<LiveRoom> followRepority = new FollowRepority(getActivity()).getFollowRepority();
        if (followRepority.size() % 2 == 0) {
            for (LiveRoom liveRoom : followRepority) {
                liveRoom.setItemType(AdapterType.TYPE_MULTIPLE);
            }
            mDataBinding.recycleFollow.setLayoutManager(new GridLayoutManager(getActivity(), SpanCountType.TWO));
        } else {
            for (LiveRoom liveRoom : followRepority) {
                liveRoom.setItemType(AdapterType.TYPE_SINGLE);
            }
            mDataBinding.recycleFollow.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        FollowAdapter followAdapter = new FollowAdapter(getActivity(), followRepority);
        mDataBinding.recycleFollow.setAdapter(followAdapter);
        followAdapter.setOnItemClickListener((adapter, view, position) -> {
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
