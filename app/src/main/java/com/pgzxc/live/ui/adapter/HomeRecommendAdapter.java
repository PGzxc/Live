package com.pgzxc.live.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pgzxc.live.R;
import com.pgzxc.live.data.bean.LiveRoom;
import com.pgzxc.live.ui.activity.player.PlayerLiveActivity;
import com.pgzxc.live.utils.ActivityUtils;

import org.kymjs.kjframe.ui.ViewInject;

import java.util.List;

/**
 * 首页-推荐
 */

public class HomeRecommendAdapter extends BaseQuickAdapter<LiveRoom, BaseViewHolder>  {

    private Activity mContext;

    public HomeRecommendAdapter(Activity context, int layoutResId, @Nullable List<LiveRoom> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    public HomeRecommendAdapter(@Nullable List<LiveRoom> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveRoom item) {
        ((ImageView) helper.getView(R.id.image_home_recommend)).setImageResource(item.getCover());
        helper.setText(R.id.tv_home_city, item.getCity());
        helper.setText(R.id.tv_home_userName, item.getUserName());
        helper.setText(R.id.tv_home_onlineNum, item.getAudienceNum() + "人在线看");
        //setOnItemClickListener(this);
    }

//    @Override
//    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        ActivityUtils.startActivity(mContext, PlayerLiveActivity.class, (LiveRoom) adapter.getItem(position));
//    }
}
