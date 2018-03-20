package com.pgzxc.live.api.reposity;

import android.app.Activity;
import android.content.Context;

import com.pgzxc.live.R;
import com.pgzxc.live.data.bean.LiveRoom;
import com.pgzxc.live.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 我的关注数据
 */

public class FollowRepority {

    private Activity mContext;

    public FollowRepority(Activity mContext) {
        this.mContext = mContext;
    }

    public List<LiveRoom> getFollowRepority() {
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
        for (String avatar : avatars) {
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
        int start;
        int end;
        int liveList = new Random().nextInt(liveRoomList.size() - 1);
        start = liveList;
        if (liveList >= liveRoomList.size()) {
            start = liveList - 3;
            end = liveList;
        } else {
            end = liveList + new Random().nextInt(liveRoomList.size() - liveList) + 1;
        }
        return liveRoomList.subList(start, end);
    }

    /**
     * 获得一个随机直播间
     * @param mContext
     * @return
     */
    public  String getChatRoomId(Context mContext) {
        String[] chartRoomIDs = mContext.getResources().getStringArray(R.array.chartRoomID);//聊天室ID
        int chartRoomNum = new Random().nextInt(chartRoomIDs.length);
        return chartRoomIDs[chartRoomNum];
    }
}
