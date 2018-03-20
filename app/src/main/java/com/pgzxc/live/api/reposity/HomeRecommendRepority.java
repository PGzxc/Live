package com.pgzxc.live.api.reposity;

import android.content.Context;

import com.pgzxc.live.R;
import com.pgzxc.live.data.bean.LiveRoom;
import com.pgzxc.live.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 首页-推荐-模拟数据
 */

public class HomeRecommendRepority {
    private Context mContext;

    public HomeRecommendRepority(Context mContext) {
        this.mContext = mContext;
    }

    public List<Integer> getLocalImages() {
        List<Integer> localImages = new ArrayList<>();
        String[] arrayViewpager = mContext.getResources().getStringArray(R.array.home_viewpager); //轮播图
        for (String str : arrayViewpager) {
            int resId = ResourceUtils.getResId(str, R.drawable.class);
            localImages.add(resId);
        }
        return localImages;
    }

    public List<LiveRoom> getHomeRecommendRepority() {
        ArrayList<Integer> avatarsImages = new ArrayList<>();
        ArrayList<Integer> rankLevImages = new ArrayList<>();


        String[] avatars = mContext.getResources().getStringArray(R.array.avatars); //头像，封面
        String[] rankLevels = mContext.getResources().getStringArray(R.array.rank_levels);//等级 1-128
        String[] userNames = mContext.getResources().getStringArray(R.array.userNames); //用户名
        String[] provicesNames = mContext.getResources().getStringArray(R.array.provices_name);// 城市
        String[] liveTitles = mContext.getResources().getStringArray(R.array.live_Titles);//直播标题
        String[] chartRoomIDs = mContext.getResources().getStringArray(R.array.chartRoomID);//聊天室ID

        for (String str : avatars) {
            int resId = ResourceUtils.getResId(str, R.drawable.class);
            avatarsImages.add(resId);
        }
        for (String str : rankLevels) {
            int resId = ResourceUtils.getResId(str, R.drawable.class);
            rankLevImages.add(resId);
        }

        List<LiveRoom> liveRoomList = new ArrayList<>();

        for (String dis : rankLevels) {
            LiveRoom liveRoom = new LiveRoom();
            int arvatarNum = new Random().nextInt(avatars.length);
            liveRoom.setAvatar(avatarsImages.get(arvatarNum)); //头像

            liveRoom.setCover(avatarsImages.get(arvatarNum));//封面

            int userNameNum = new Random().nextInt(userNames.length);
            liveRoom.setUserName(userNames[userNameNum]);//用户名

            int proviceNameNum = new Random().nextInt(provicesNames.length);
            liveRoom.setCity(provicesNames[proviceNameNum]);//城市

            liveRoom.setAudienceNum(new Random().nextInt(2000) + 10);//直播间人数

            int rankLevelNum = new Random().nextInt(rankLevels.length);
            liveRoom.setRankLev(rankLevImages.get(rankLevelNum));//当前等级

            int liveTitleNum = new Random().nextInt(liveTitles.length);
            liveRoom.setLiveTitle(liveTitles[liveTitleNum]);//直播标题

            int chartRoomNum = new Random().nextInt(chartRoomIDs.length);
            liveRoom.setChatroomId(chartRoomIDs[chartRoomNum]);

            liveRoomList.add(liveRoom);

        }

        return liveRoomList;
    }
}
