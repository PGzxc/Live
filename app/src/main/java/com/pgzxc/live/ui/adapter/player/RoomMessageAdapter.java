package com.pgzxc.live.ui.adapter.player;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.pgzxc.live.R;
import java.util.List;
import java.util.Random;

/**
 * 直播间消息的适配器
 */

public class RoomMessageAdapter extends BaseQuickAdapter<EMMessage, BaseViewHolder> {
    private Context mContext;

    public RoomMessageAdapter(Context context, int layoutResId,  List<EMMessage> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EMMessage item) {
        helper.setText(R.id.tv_msg_level, String.valueOf(new Random().nextInt(100)));
        helper.setText(R.id.tv_msg_name, item.getFrom() + ":");
        helper.setText(R.id.tv_msg_content,((EMTextMessageBody)item.getBody()).getMessage());
    }

}
