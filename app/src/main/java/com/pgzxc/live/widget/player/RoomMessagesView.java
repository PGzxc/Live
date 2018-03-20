package com.pgzxc.live.widget.player;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.pgzxc.live.R;
import com.pgzxc.live.listener.MessageViewListener;
import com.pgzxc.live.ui.adapter.player.RoomMessageAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ========================================
 * 描 述：房间聊天布局
 * ========================================
 */
public class RoomMessagesView extends RelativeLayout {

    /**
     * 环信聊天对象
     */
    private EMConversation conversation;
    /**
     * 消息适配器
     */
    RoomMessageAdapter adapter;
    /**
     * 消息列表
     */
    @BindView(R.id.recycleViewMsg)
    RecyclerView recyclerView;
    /**
     * 输入面板
     */
    @BindView(R.id.linear_send)
    View sendContainer;
    /**
     * 编辑框
     */
    @BindView(R.id.edit_text_content)
    MentionEditText editText;
    /**
     * 发送
     */
    @BindView(R.id.btn_send)
    Button sendBtn;
    /**
     * 关闭
     */
    @BindView(R.id.image_close)
    ImageView closeView;
    /**
     * 弹幕
     */
    @BindView(R.id.image_barrage)
    ImageView danmuImage;
    /**
     * 房间监听
     */
    MessageViewListener messageViewListener;
    /**
     * 消息列表
     */
    List<EMMessage> list = new ArrayList<>();
    /**
     * 是否是弹幕
     */
    private boolean isDanmuShow = false;
    /**
     * @的某人的集合
     */
    private List<String> atList = new ArrayList<>();

    public RoomMessagesView(Context context) {
        super(context);
        init(context, null);
    }

    public RoomMessagesView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoomMessagesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_room_messages, this);
        ButterKnife.bind(view);
    }

    /**
     * 获取输入框
     */
    public MentionEditText getInputView() {
        return editText;
    }

    /**
     * 获取艾特的集合
     */
    public List<String> getAtList() {
        return atList;
    }

    public void init(String chatroomId) {
        conversation = EMClient.getInstance().chatManager().getConversation(chatroomId, EMConversation.EMConversationType.ChatRoom, true);
        List<EMMessage> temp = conversation.getAllMessages();
        if (temp != null && temp.size() > 0) {
            list.clear();
            list.addAll(temp);
        }
        adapter = new RoomMessageAdapter(getContext(), R.layout.adapter_room_msg, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            if (messageViewListener != null) {
                EMMessage message = list.get(position);
                messageViewListener.onItemClickListener(0, message.getFrom());
            }
        });
        sendBtn.setOnClickListener(view -> {
            if (messageViewListener != null) {
                if (TextUtils.isEmpty(editText.getText())) {
                    Toast.makeText(getContext(), "文字内容不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                /**这里是@的集合*/
                List<String> temp1 = editText.getMentionList(true);
                if (temp1 != null && temp1.size() > 0) {
                    atList.clear();
                    atList.addAll(temp1);
                }
                messageViewListener.onMessageSend(editText.getText().toString());
                editText.setText("");
            }
        });
        closeView.setOnClickListener(view -> {
            setShowInputView(false);
            if (messageViewListener != null) {
                messageViewListener.onHiderBottomBar();
            }
        });

        danmuImage.setOnClickListener(view -> {
            if (danmuImage.isSelected()) {
                danmuImage.setSelected(false);
                isDanmuShow = false;
            } else {
                danmuImage.setSelected(true);
                isDanmuShow = true;
            }
        });
        /**输入框文本输入监听*/
        editText.setMentionTextColor(Color.RED); //optional, set highlight color of mention string
        editText.setPattern("@[\\u4e00-\\u9fa5\\w\\-]+"); //optional, set regularExpression
        /**自动补全*/
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("@".equals(editText.getText().toString())) {
                    editText.setText("@全体成员 ");
                    editText.setSelection(editText.getText().length());
                }
            }
        });

    }

    /**
     * 艾特功能中设置艾特的人
     */
    public void setReplyer(String txt) {
        editText.setText("@" + txt + " ");
        editText.setSelection(editText.getText().length());
    }

    public void setShowInputView(boolean showInputView) {
        if (showInputView) {
            sendContainer.setVisibility(View.VISIBLE);
        } else {
            sendContainer.setVisibility(View.INVISIBLE);
        }
    }

    public void setMessageViewListener(MessageViewListener messageViewListener) {
        this.messageViewListener = messageViewListener;
    }

    /**
     * 刷新
     */
    public void refresh() {
        List<EMMessage> temp = conversation.getAllMessages();
        if (temp != null && temp.size() > 0) {
            list.clear();
            list.addAll(temp);
        }
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 刷新并定位到最后一条
     */
    public void refreshSelectLast() {
        if (adapter != null) {
            refresh();
            recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
        }
    }

    /**
     * 是否开始弹幕（飘屏）
     */
    public boolean isDanmuShow() {
        return isDanmuShow;
    }
}
