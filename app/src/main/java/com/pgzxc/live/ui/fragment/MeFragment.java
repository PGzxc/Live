package com.pgzxc.live.ui.fragment;


import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.pgzxc.live.R;
import com.pgzxc.live.databinding.FragmentMeBinding;
import com.pgzxc.live.ui.activity.LoginActivity;
import com.pgzxc.live.ui.base.BaseFragment;
import com.pgzxc.live.utils.ActivityUtils;
import org.kymjs.kjframe.ui.ViewInject;
import java.util.Random;

/**
 * 我
 */

public class MeFragment extends BaseFragment<FragmentMeBinding> {
    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mDataBinding.titleBar.setTitleTextView("我");
        mDataBinding.titleBar.setRightImageView(R.drawable.ic_sign_out, view -> {
            EMClient.getInstance().logout(false, new EMCallBack() {
                @Override
                public void onSuccess() {
                    ActivityUtils.skipActivity(getActivity(), LoginActivity.class);
                }

                @Override
                public void onError(int i, String s) {

                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mDataBinding.headerInfo.setTvUerName(EMClient.getInstance().getCurrentUser());
        mDataBinding.followInfo.setFansNum(new Random().nextInt(10000) + 123);
        mDataBinding.followInfo.setFollowNum(new Random().nextInt(10000) + 123);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mDataBinding.followInfo.getRlFollow().setOnClickListener(view -> {
            ViewInject.toast(getActivity(), "关注");
        });
        mDataBinding.followInfo.getRlFans().setOnClickListener(v -> ViewInject.toast(getActivity(), "粉丝"));
    }
}
