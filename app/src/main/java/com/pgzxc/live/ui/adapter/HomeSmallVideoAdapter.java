package com.pgzxc.live.ui.adapter;

import android.app.Activity;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pgzxc.live.R;
import com.pgzxc.live.data.bean.LiveRoom;
import org.kymjs.kjframe.widget.RoundImageView;
import java.util.List;

/**
 * 首页-小视频
 */

public class HomeSmallVideoAdapter extends BaseQuickAdapter<LiveRoom, BaseViewHolder>{

    private Activity activity;

    public HomeSmallVideoAdapter(Activity activity, int layoutResId,  List<LiveRoom> data) {
        super(layoutResId, data);
        this.activity = activity;
    }

    public HomeSmallVideoAdapter(List<LiveRoom> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveRoom item) {
        ((ImageView) helper.getView(R.id.image_home_live_cover)).setImageResource(item.getCover());
        ((RoundImageView) helper.getView(R.id.image_round_home_video)).setImageResource(item.getAvatar());
        helper.setText(R.id.tv_home_dis, item.getDis());
        helper.setText(R.id.tv_home_video_title, item.getLiveTitle());
    }

}
