package com.pgzxc.live.ui.fragment.player;

import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMChatRoomChangeListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.exceptions.HyphenateException;
import com.orhanobut.logger.Logger;
import com.pgzxc.live.R;
import com.pgzxc.live.api.reposity.FollowRepority;
import com.pgzxc.live.api.reposity.PlayLiveRepority;
import com.pgzxc.live.api.req.IntentKey;
import com.pgzxc.live.api.req.StatusConfig;
import com.pgzxc.live.data.bean.AudienceBean;
import com.pgzxc.live.data.bean.Gif;
import com.pgzxc.live.data.bean.LiveRoom;
import com.pgzxc.live.databinding.FragmentChatBinding;
import com.pgzxc.live.eventbus.AnyEvent;
import com.pgzxc.live.eventbus.AnyEventType;
import com.pgzxc.live.listener.MessageViewListener;
import com.pgzxc.live.ui.adapter.player.AudienceAdapter;
import com.pgzxc.live.ui.base.BaseFragment;
import com.pgzxc.live.utils.KeyboardUtils;
import com.pgzxc.live.utils.ShareUtils;
import com.pgzxc.live.widget.gif.FragmentGiftDialog;
import com.pgzxc.live.widget.nicedialog.AudienceDialog;
import com.pgzxc.live.widget.nicedialog.BaseNiceDialog;
import com.pgzxc.live.widget.nicedialog.ViewConvertListener;
import com.pgzxc.live.widget.nicedialog.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.kymjs.kjframe.ui.ViewInject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 聊天
 */

public class ChatFragment extends BaseFragment<FragmentChatBinding> {
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private LiveRoom liveRoom;
    List<AudienceBean> memberList = new ArrayList<>();
    protected EMChatRoom chatroom;
    protected boolean isMessageListInited;


    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void setListener() {
        super.setListener();
        mDataBinding.rootLayout.setOnClickListener(view -> {
            mDataBinding.heartLayout.addHeart(randomColor());
        });
        EMClient.getInstance().chatroomManager().addChatRoomChangeListener(chatRoomChangeListener);
        mDataBinding.liveBottomBar.bottomSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputView();
            }
        });
        mDataBinding.liveBottomBar.bottomGift.setOnClickListener(view -> {
            ArrayList<Gif> gifts = new ArrayList<>();
            FragmentGiftDialog giftDialog = FragmentGiftDialog.newInstance();
            giftDialog.setOnGridViewClickListener(gift -> {
                gift.userName = EMClient.getInstance().getCurrentUser();
                gift.avatar = liveRoom.getAvatar();
                gift.giftName = "送你一个小礼物" + gift.getGiftName();
                if (!gifts.contains(gift)) {
                    gifts.add(gift);
                    mDataBinding.gifItem.setGif(gift);
                }
                mDataBinding.gifItem.addNum(1);
            }).show(getChildFragmentManager(), "dialog");
        });

        mDataBinding.liveBottomBar.bottomShare.setOnClickListener(view -> ShareUtils.shareText(getActivity()));
        mDataBinding.liveBottomBar.bottomClose.setOnClickListener(view -> {
            EventBus.getDefault().postSticky(new AnyEvent<>(AnyEventType.CHAT_FRAGMENT_LIVE_CLOSE, null));
        });
    }

    @Override
    protected void initData() {
        super.initData();
        if (getArguments() != null) {
            //头部
            liveRoom = (LiveRoom) getArguments().getSerializable(IntentKey.LiveRoom);
            Glide.with(getActivity()).load(liveRoom.getAvatar()).error(R.drawable.default_head).placeholder(R.color.white).into(mDataBinding.layoutHead.liveAnchor);
            mDataBinding.layoutHead.tvUsername.setText(liveRoom.getUserName());
            mDataBinding.layoutHead.audienceNum.setText(String.valueOf(liveRoom.getAudienceNum()));
        }

        //
        Disposable subscribe = Observable.interval(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> mDataBinding.heartLayout.addHeart(randomColor()));
        mCompositeDisposable.add(subscribe);

    }

    /**
     * 产生随机心形颜色
     *
     * @return
     */
    private int randomColor() {
        return Color.rgb(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
    }

    /**
     * 聊天室监听
     */
    EMChatRoomChangeListener chatRoomChangeListener = new EMChatRoomChangeListener() {
        /**
         * 聊天室被解散
         * @param roomId
         * @param roomName
         */
        @Override
        public void onChatRoomDestroyed(String roomId, String roomName) {
            if (roomId.equals(liveRoom.getChatroomId())) {
                Logger.d(" room : " + roomId + " with room name : " + roomName + " was destroyed");
            }
        }

        /**
         * 聊天室加入新成员事件
         * @param roomId
         *  聊天室id
         * @param participant
         *  新成员username
         */
        @Override
        public void onMemberJoined(String roomId, String participant) {
            EMMessage message = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
            message.setFrom(participant);
            EMTextMessageBody textMessageBody = new EMTextMessageBody("来了");
            message.addBody(textMessageBody);
            message.setChatType(EMMessage.ChatType.ChatRoom);
            EMClient.getInstance().chatManager().saveMessage(message);
            mDataBinding.messageView.refreshSelectLast();
            onRoomMemberAdded(participant);
            Logger.d("-----新成员进入聊天室-----" + participant);

        }

        /**
         * 聊天室成员主动退出事件
         * @param roomId
         * 聊天室id
         * @param roomName
         * 聊天室名字
         * @param participant
         *  退出的成员的username
         */
        @Override
        public void onMemberExited(String roomId, String roomName, String participant) {
            onRoomMemberExited(participant);
            Logger.d("-----成员退出聊天室-----" + participant);

        }

        /**
         * 聊天室人员被移除
         * @param roomId
         * 聊天室id
         *@param roomName
         * 聊天室名字
         * @param participant
         * 被移除人员的username
         */
        @Override
        public void onRemovedFromChatRoom(int i, String roomId, String roomName, String participant) {
            if (roomId.equals(liveRoom.getChatroomId())) {
                String curUser = EMClient.getInstance().getCurrentUser();
                if (curUser.equals(participant)) {
                    EMClient.getInstance().chatroomManager().leaveChatRoom(roomId);
                    ViewInject.toast(getActivity(), "你已被移除出此房间");
                    getActivity().onBackPressed();
                } else {
                    onRoomMemberExited(participant);
                }
            }
            Logger.d("-----成员被移除出聊天室-----" + participant);
        }


        @Override
        public void onMuteListAdded(String s, List<String> list, long l) {

        }

        @Override
        public void onMuteListRemoved(String s, List<String> list) {

        }

        @Override
        public void onWhiteListAdded(String chatRoomId, List<String> whitelist) {

        }

        @Override
        public void onWhiteListRemoved(String chatRoomId, List<String> whitelist) {

        }

        @Override
        public void onAllMemberMuteStateChanged(String chatRoomId, boolean isMuted) {

        }

        @Override
        public void onAdminAdded(String s, String s1) {

        }

        @Override
        public void onAdminRemoved(String s, String s1) {

        }

        @Override
        public void onOwnerChanged(String s, String s1, String s2) {

        }

        @Override
        public void onAnnouncementChanged(String s, String s1) {

        }
    };
    /**
     * 消息接收监听
     */
    EMMessageListener messageListener = new EMMessageListener() {
        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            for (EMMessage message : messages) {
                String username = null;
                // 群组消息
                if (message.getChatType() == EMMessage.ChatType.GroupChat
                        || message.getChatType() == EMMessage.ChatType.ChatRoom) {
                    username = message.getTo();
                } else {
                    // 单聊消息
                    username = message.getFrom();
                }
                // 如果是当前会话的消息，刷新聊天页面
                if (username.equals(liveRoom.getChatroomId())) {
                    if (message.getBooleanAttribute(StatusConfig.EXTRA_IS_BARRAGE_MSG, false)) {
                        mDataBinding.barrageLayout.addBarrage(((EMTextMessageBody) message.getBody()).getMessage(),
                                message.getFrom(), liveRoom.getAvatar());
                    }
                    mDataBinding.messageView.refreshSelectLast();
                }
            }
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageRead(List<EMMessage> list) {

        }

        @Override
        public void onMessageDelivered(List<EMMessage> list) {

        }

        @Override
        public void onMessageRecalled(List<EMMessage> list) {

        }

        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {
            if (isMessageListInited) {
                mDataBinding.messageView.refresh();
            }
        }
    };

    /**
     * 新成员进入聊天室
     */
    private void onRoomMemberAdded(String name) {
        if (!memberList.contains(name)) {
            AudienceBean anchorBean = new AudienceBean();
            anchorBean.setName(name);
            anchorBean.setCover(new PlayLiveRepority(getActivity()).getAvatar());
            memberList.add(anchorBean);
        }
        getActivity().runOnUiThread(() -> {
            mDataBinding.layoutHead.audienceNum.setText(String.valueOf(memberList.size()));
            mDataBinding.layoutHead.horizontalRecycleView.getAdapter().notifyDataSetChanged();
        });
    }

    /**
     * 成员离开聊天室
     */
    private void onRoomMemberExited(String name) {
        removeMember(name);
        getActivity().runOnUiThread(() -> {
            mDataBinding.layoutHead.audienceNum.setText(String.valueOf(memberList.size()));
            mDataBinding.layoutHead.horizontalRecycleView.getAdapter().notifyDataSetChanged();
        });
    }

    /**
     * 移除聊天室成员
     */
    private synchronized void removeMember(String name) {
        if (name == null || "".equals(name)) {
            return;
        }
        for (int i = 0; i < memberList.size(); i++) {
            if (name.equals(memberList.get(i).getName())) {
                memberList.remove(i);
                break;
            }
        }
    }

    public void joinChatRoom() {
        EMClient.getInstance().chatroomManager().joinChatRoom(modifyChatRoomID(liveRoom.getChatroomId()), new EMValueCallBack<EMChatRoom>() {
            @Override
            public void onSuccess(EMChatRoom emChatRoom) {
                chatroom = emChatRoom;
                EMClient.getInstance().chatroomManager().addChatRoomChangeListener(chatRoomChangeListener);
                onMessageListInit();
                Logger.d("-----加入聊天室成功-----");
            }

            @Override
            public void onError(int i, String s) {
                ViewInject.toast(getActivity(), "加入聊天室失败");
                Logger.d("-----加入聊天室失败-----" + s);
            }
        });

    }

    private String modifyChatRoomID(String roomID) {
        if (TextUtils.isEmpty(roomID)) {
            return new FollowRepority(getActivity()).getChatRoomId(getActivity());
        } else {
            return roomID;
        }
    }

    /**
     * 初始化消息
     */
    protected void onMessageListInit() {
        getActivity().runOnUiThread(() -> {
            mDataBinding.messageView.init(liveRoom.getChatroomId());
            mDataBinding.messageView.setMessageViewListener(new MessageViewListener() {
                @Override
                public void onMessageSend(String content) {
                    EMMessage message = EMMessage.createTxtSendMessage(content, liveRoom.getChatroomId());
                    if (mDataBinding.messageView.isDanmuShow()) {
                        message.setAttribute(StatusConfig.EXTRA_IS_BARRAGE_MSG, true);
                        mDataBinding.barrageLayout.addBarrage(content, EMClient.getInstance().getCurrentUser(), liveRoom.getAvatar());
                    }
                    message.setChatType(EMMessage.ChatType.ChatRoom);
                    EMClient.getInstance().chatManager().sendMessage(message);
                    message.setMessageStatusCallback(new EMCallBack() {
                        @Override
                        public void onSuccess() {
                            //刷新消息列表
                            mDataBinding.messageView.refreshSelectLast();
                            Logger.d("-----消息发送成功-----");
                        }

                        @Override
                        public void onError(int i, String s) {
                            ViewInject.toast(getActivity(), "消息发送失败！");
                            Logger.d("-----消息发送失败！-----" + s);
                        }

                        @Override
                        public void onProgress(int i, String s) {

                        }
                    });
                }

                @Override
                public void onHiderBottomBar() {
                    KeyboardUtils.hideSoftInput(mDataBinding.messageView.getInputView());
                    mDataBinding.liveBottomBar.layoutBottomMenu.setVisibility(View.VISIBLE);
                }

                @Override
                public void onItemClickListener(int id, String sid) {
                    if (sid != null && !sid.equals(EMClient.getInstance().getCurrentUser())) {
                        mDataBinding.messageView.setShowInputView(true);
                        mDataBinding.liveBottomBar.layoutBottomMenu.setVisibility(View.INVISIBLE);
                        mDataBinding.messageView.setReplyer(sid);
                    }
                    Logger.d("-----点击聊天消息的条目-----" + sid);
                }

                @Override
                public void onLoadMore() {

                }
            });
            mDataBinding.messageView.setVisibility(View.VISIBLE);
            mDataBinding.liveBottomBar.layoutBottomMenu.setVisibility(View.VISIBLE);
            isMessageListInited = true;
            updateUnreadMsgView();
            showMemberList();
        });
    }

    /**
     * 更新未读显示提示
     */
    protected void updateUnreadMsgView() {
        if (isMessageListInited) {
            for (EMConversation conversation : EMClient.getInstance()
                    .chatManager()
                    .getAllConversations()
                    .values()) {
                if (conversation.getType() == EMConversation.EMConversationType.Chat
                        && conversation.getUnreadMsgCount() > 0) {
                    return;
                }
            }
        }
    }

    /**
     * 显示成员列表
     */
    private void showMemberList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mDataBinding.layoutHead.horizontalRecycleView.setLayoutManager(layoutManager);
        AudienceAdapter anchorAdapter = new AudienceAdapter(R.layout.adapter_live_audience, memberList);
        mDataBinding.layoutHead.horizontalRecycleView.setAdapter(anchorAdapter);
        anchorAdapter.setOnItemClickListener((adapter, view, position) -> {
            AudienceDialog dialog = AudienceDialog.newInstance((AudienceBean) adapter.getItem(position));
            dialog.setWidth(300);
            dialog.setAnimStyle(R.style.EnterExitAnimation);
            dialog.show(getChildFragmentManager());

            dialog.setConvertListener(new ViewConvertListener() {
                @Override
                protected void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                    holder.setOnClickListener(R.id.image_audience_close, v -> dialog.dismiss());
                }
            });
        });

        /**获取聊天室成员列表*/
        new Thread(() -> {
            try {
                chatroom = EMClient.getInstance().chatroomManager().fetchChatRoomFromServer(liveRoom.getChatroomId(), true);
                List<String> temp = chatroom.getMemberList();
                memberList.addAll(new PlayLiveRepority(getActivity()).getLiveAudience());
                if (temp != null && temp.size() > 0) {
                    for (int i = 0; i < temp.size(); i++) {
                        AudienceBean anchorBean = new AudienceBean();
                        anchorBean.setName(temp.get(i));
                        anchorBean.setCover(new PlayLiveRepority(getActivity()).getAvatar());
                        memberList.add(anchorBean);
                    }
                }
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
            getActivity().runOnUiThread(() -> {
                mDataBinding.layoutHead.audienceNum.setText(String.valueOf(memberList.size()));
                mDataBinding.layoutHead.horizontalRecycleView.getAdapter().notifyDataSetChanged();
            });
        }).start();

    }

    /**
     * 显示输入框
     */
    private void showInputView() {
        mDataBinding.liveBottomBar.layoutBottomMenu.setVisibility(View.INVISIBLE);
        mDataBinding.messageView.setShowInputView(true);
        mDataBinding.messageView.getInputView().requestFocus();
        mDataBinding.messageView.getInputView().requestFocusFromTouch();
        Handler handler = new Handler();
        handler.postDelayed(() -> KeyboardUtils.showSoftInput(mDataBinding.messageView.getInputView()), 200);
    }

    @Override
    public void onResume() {
        super.onResume();
        // register the event listener when enter the foreground
        EMClient.getInstance().chatManager().addMessageListener(messageListener);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventMainThread(AnyEvent event) {
        switch (event.getEvent()) {
            case AnyEventType.PLAY_START:
                joinChatRoom();
                break;

        }
    }

}
