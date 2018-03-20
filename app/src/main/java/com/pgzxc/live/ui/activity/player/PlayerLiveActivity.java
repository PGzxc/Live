package com.pgzxc.live.ui.activity.player;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.pgzxc.live.R;
import com.pgzxc.live.api.reposity.PlayLiveRepority;
import com.pgzxc.live.api.req.IntentKey;
import com.pgzxc.live.data.bean.LiveRoom;
import com.pgzxc.live.databinding.ActivityPlayerLiveBinding;
import com.pgzxc.live.eventbus.AnyEvent;
import com.pgzxc.live.eventbus.AnyEventType;
import com.pgzxc.live.listener.PlayEvent;
import com.pgzxc.live.player.pldroid.PlayerHelper;
import com.pgzxc.live.ui.adapter.VerticalPagerAdapter;
import com.pgzxc.live.ui.adapter.player.RoomPanlAdapter;
import com.pgzxc.live.ui.base.BaseActivity;
import com.pgzxc.live.ui.fragment.player.ChatFragment;
import com.pgzxc.live.ui.fragment.player.NoFragment;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.kymjs.kjframe.ui.ViewInject;


/**
 * 直播页面
 */

public class PlayerLiveActivity extends BaseActivity<ActivityPlayerLiveBinding> implements PlayEvent.PlayCallBack {
    private static final String TAG = PlayerLiveActivity.class.getSimpleName();
    private PLVideoTextureView mVideoView;
    private RelativeLayout mRoomContainer;
    private int mCurrentItem;
    private int mRoomId = -1;
    private PlayerHelper playerHelper;
    private ViewPager viewPagerLive;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_player_live;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mRoomContainer = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.view_room_container, null);
        viewPagerLive = mRoomContainer.findViewById(R.id.view_pager_live);
        mVideoView = mRoomContainer.findViewById(R.id.texture_view);
        playerHelper = new PlayerHelper(PlayerLiveActivity.this, mVideoView, this);

    }


    @Override
    public void initData() {
        super.initData();
        RoomPanlAdapter roomPanlAdapter = new RoomPanlAdapter(getSupportFragmentManager());
        roomPanlAdapter.addFragment(new NoFragment());
        ChatFragment chatFragment = new ChatFragment();
        Bundle bundle = new Bundle();
        LiveRoom liveRoom = (LiveRoom) getIntent().getSerializableExtra(IntentKey.LiveRoom);
        bundle.putSerializable(IntentKey.LiveRoom, liveRoom);
        chatFragment.setArguments(bundle);
        roomPanlAdapter.addFragment(chatFragment);
        viewPagerLive.setAdapter(roomPanlAdapter);
        viewPagerLive.setCurrentItem(1);

        mDataBinding.viewPager.setAdapter(new VerticalPagerAdapter(new PlayLiveRepority(PlayerLiveActivity.this).getLiveStreams()));
    }

    @Override
    public void setListener() {
        super.setListener();
        mDataBinding.viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e(TAG, "mCurrentId == " + position + ", positionOffset == " + positionOffset +
                        ", positionOffsetPixels == " + positionOffsetPixels);
                mCurrentItem = position;
            }
        });

        mDataBinding.viewPager.setPageTransformer(false, (page, position) -> {
            ViewGroup viewGroup = (ViewGroup) page;
            Log.e(TAG, "page.id == " + page.getId() + ", position == " + position);

            if ((position < 0 && viewGroup.getId() != mCurrentItem)) {
                View roomContainer = viewGroup.findViewById(R.id.room_container);
                if (roomContainer != null && roomContainer.getParent() != null && roomContainer.getParent() instanceof ViewGroup) {
                    ((ViewGroup) (roomContainer.getParent())).removeView(roomContainer);
                }
            }
            // 满足此种条件，表明需要加载直播视频，以及聊天室了
            if (viewGroup.getId() == mCurrentItem && position == 0 && mCurrentItem != mRoomId) {
                if (mRoomContainer.getParent() != null && mRoomContainer.getParent() instanceof ViewGroup) {
                    ((ViewGroup) (mRoomContainer.getParent())).removeView(mRoomContainer);
                }
                loadVideoAndChatRoom(viewGroup, mCurrentItem);
            }
        });

    }

    /**
     * @param viewGroup
     * @param currentItem
     */
    private void loadVideoAndChatRoom(ViewGroup viewGroup, int currentItem) {
        //聊天室的fragment只加载一次，以后复用
//        if (!mInit) {
//            getSupportFragmentManager().beginTransaction().add(mFragmentContainer.getId(), mRoomFragment).commitAllowingStateLoss();
//            mInit = true;
//        }


        viewGroup.addView(mRoomContainer);
        loadVideo(currentItem);
        mRoomId = currentItem;
    }

    private void loadVideo(int position) {
        playerHelper.playUrl(new PlayLiveRepority(PlayerLiveActivity.this).getLiveStreams().get(position));
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mVideoView.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mVideoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onEvent(int what, String message) {
        switch (what) {
            case PlayEvent.PlayCallBack.EVENT_PLAY_START:
                ViewInject.toast(this, "开始");
                EventBus.getDefault().postSticky(new AnyEvent<Integer>(AnyEventType.PLAY_START, null));
                break;
            case PlayEvent.PlayCallBack.EVENT_PLAY_COMPLETION:
                ViewInject.toast(this, "完成");
                break;
        }
    }

    /**
     * 关闭直播间
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void PlivLiveClose(AnyEvent event) {
        switch (event.getEvent()) {
            case AnyEventType.CHAT_FRAGMENT_LIVE_CLOSE:
            case AnyEventType.NO_FRAGMENT_LIVE_CLOSE:
                onBackPressedSupport();
                break;
        }
    }
}
