package com.pgzxc.live.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pgzxc.live.R;
import com.pgzxc.live.data.bean.LiveRoom;
import org.kymjs.kjframe.widget.RoundImageView;
import java.util.List;

/**
 * 附近-小视频
 */

public class RecentSmallVideoAdapter extends BaseQuickAdapter<LiveRoom, BaseViewHolder> {


    public RecentSmallVideoAdapter(int layoutResId, @Nullable List<LiveRoom> data) {
        super(layoutResId, data);
    }

    public RecentSmallVideoAdapter(@Nullable List<LiveRoom> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveRoom item) {
        ((ImageView) helper.getView(R.id.image_recent_live_cover)).setImageResource(item.getCover());
        ((RoundImageView) helper.getView(R.id.image_round_recent_video)).setImageResource(item.getAvatar());
        helper.setText(R.id.tv_recent_dis, item.getDis());
        helper.setText(R.id.tv_recent_video_title, item.getLiveTitle());
    }
}
