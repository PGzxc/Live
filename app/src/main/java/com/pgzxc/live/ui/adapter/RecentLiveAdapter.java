package com.pgzxc.live.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pgzxc.live.R;
import com.pgzxc.live.data.bean.LiveRoom;

import java.util.List;

/**
 * 附近-直播
 */

public class RecentLiveAdapter extends BaseQuickAdapter<LiveRoom, BaseViewHolder> {


    public RecentLiveAdapter(int layoutResId, @Nullable List<LiveRoom> data) {
        super(layoutResId, data);
    }

    public RecentLiveAdapter(@Nullable List<LiveRoom> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveRoom item) {
        ((ImageView) helper.getView(R.id.image_recent_live)).setImageResource(item.getCover());
        ((ImageView) helper.getView(R.id.image_recent_rank_lev)).setImageResource(item.getRankLev());
        helper.setText(R.id.tv_recent_dis, item.getDis());
    }
}
