package com.pgzxc.live.api.reposity;

import android.content.Context;

import com.pgzxc.live.R;
import com.pgzxc.live.data.bean.LiveRoom;
import com.pgzxc.live.utils.ResourceUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 附近-直播-数据仓库
 */

public class RecentLiveRepority {

    private Context mContext;

    public RecentLiveRepority(Context mContext) {
        this.mContext = mContext;
    }

    public List<String> getMarqueenData() {
        List<String> info = new ArrayList<>();
        String[] arrayMarqueen = mContext.getResources().getStringArray(R.array.recent_marquee);
        info.addAll(Arrays.asList(arrayMarqueen));
        return info;
    }

    public List<LiveRoom> getFollowRepority() {
        ArrayList<Integer> avatarsImages = new ArrayList<>();
        ArrayList<Integer> rankLevImages = new ArrayList<>();
        ArrayList<String> liveDis = new ArrayList<>();

        String[] avatars = mContext.getResources().getStringArray(R.array.avatars); //头像，封面
        String[] rankLevels = mContext.getResources().getStringArray(R.array.rank_levels);//等级 1-128
        String[] arrayDis = mContext.getResources().getStringArray(R.array.recent_liv_dis);
        String[] chartRoomIDs = mContext.getResources().getStringArray(R.array.chartRoomID);//聊天室ID


        for (String str : avatars) {
            int resId = ResourceUtils.getResId(str, R.drawable.class);
            avatarsImages.add(resId);
        }
        for (String str : rankLevels) {
            int resId = ResourceUtils.getResId(str, R.drawable.class);
            rankLevImages.add(resId);
        }

        liveDis.addAll(Arrays.asList(arrayDis));

        List<LiveRoom> liveRoomList = new ArrayList<>();
        for (String dis : liveDis) {
            LiveRoom liveRoom = new LiveRoom();
            int arvatarNum = new Random().nextInt(avatars.length);
            liveRoom.setCover(avatarsImages.get(arvatarNum));//封面

            int rankLevelNum = new Random().nextInt(rankLevels.length);
            liveRoom.setRankLev(rankLevImages.get(rankLevelNum));//当前等级

            int liveDislNum = new Random().nextInt(liveDis.size());
            liveRoom.setDis(liveDis.get(liveDislNum));

            int chartRoomNum = new Random().nextInt(chartRoomIDs.length);
            liveRoom.setChatroomId(chartRoomIDs[chartRoomNum]);  //聊天室

            liveRoomList.add(liveRoom);

        }

        return liveRoomList;
    }
}
