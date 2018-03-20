package com.pgzxc.live.ui.fragment.player;

import android.os.Bundle;

import com.pgzxc.live.R;
import com.pgzxc.live.databinding.FragmentNochatBinding;
import com.pgzxc.live.eventbus.AnyEvent;
import com.pgzxc.live.eventbus.AnyEventType;
import com.pgzxc.live.ui.base.BaseFragment;
import org.greenrobot.eventbus.EventBus;

/**
 * author：Administrator on 2016/12/26 09:35
 * description:文件说明
 * version:版本
 */
public class NoFragment extends BaseFragment<FragmentNochatBinding> {

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_nochat;
    }

    @Override
    protected void setListener() {
        super.setListener();
        mDataBinding.imgClose.setOnClickListener(view -> {
            EventBus.getDefault().postSticky(new AnyEvent<>(AnyEventType.NO_FRAGMENT_LIVE_CLOSE, null));
        });
    }
}
