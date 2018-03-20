package com.pgzxc.live.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pgzxc.live.R;
import com.pgzxc.live.data.bean.LiveRoom;
import com.pgzxc.live.data.constants.AdapterType;
import org.kymjs.kjframe.widget.RoundImageView;
import java.util.List;

/**
 * 关注
 */

public class FollowAdapter extends BaseMultiItemQuickAdapter<LiveRoom, BaseViewHolder> {

    private Context mContext;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public FollowAdapter(Context context, List<LiveRoom> data) {
        super(data);
        addItemType(AdapterType.TYPE_SINGLE, R.layout.adapter_follow_one);
        addItemType(AdapterType.TYPE_MULTIPLE, R.layout.adapter_follow_common);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, LiveRoom item) {

        switch (baseViewHolder.getItemViewType()) {
            case AdapterType.TYPE_SINGLE:
                ((RoundImageView) baseViewHolder.getView(R.id.image_round)).setImageResource(item.getAvatar());
                ((ImageView) baseViewHolder.getView(R.id.image_cover)).setImageResource(item.getCover());
                baseViewHolder.setText(R.id.tv_userName, item.getUserName());
                baseViewHolder.setText(R.id.tv_city, item.getCity());
                baseViewHolder.setText(R.id.tv_room_num, String.valueOf(item.getAudienceNum()));
                baseViewHolder.setText(R.id.tv_liveTitle, item.getLiveTitle());
                break;
            case AdapterType.TYPE_MULTIPLE:
                ((RoundImageView) baseViewHolder.getView(R.id.image_round)).setImageResource(item.getAvatar());
                ((ImageView) baseViewHolder.getView(R.id.image_cover)).setImageResource(item.getCover());
                ((ImageView) baseViewHolder.getView(R.id.image_rank_lev)).setImageResource(item.getRankLev());
                baseViewHolder.setText(R.id.tv_liveTitle, item.getLiveTitle());
                baseViewHolder.setText(R.id.tv_room_num, String.valueOf(item.getAudienceNum()));
                baseViewHolder.setText(R.id.tv_userName, item.getUserName());

                break;
        }
    }
}
