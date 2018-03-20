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
 * 附近-小视频-数据仓库
 */

public class RecentSmallVideoRepority {

    private Context mContext;

    public RecentSmallVideoRepority(Context mContext) {
        this.mContext = mContext;
    }

    public List<LiveRoom> getFollowVideoRepority() {
        ArrayList<Integer> avatarsImages = new ArrayList<>();
        ArrayList<String> liveDis = new ArrayList<>();

        String[] avatars = mContext.getResources().getStringArray(R.array.avatars); //头像，封面
        String[] arrayDis = mContext.getResources().getStringArray(R.array.recent_liv_dis);
        String[] liveTitles = mContext.getResources().getStringArray(R.array.live_Titles);//直播标题

        for (String str : avatars) {
            int resId = ResourceUtils.getResId(str, R.drawable.class);
            avatarsImages.add(resId);
        }

        liveDis.addAll(Arrays.asList(arrayDis));

        List<LiveRoom> liveRoomList = new ArrayList<>();
        for (String dis : liveDis) {
            LiveRoom liveRoom = new LiveRoom();
            int arvatarNum = new Random().nextInt(avatars.length);
            liveRoom.setCover(avatarsImages.get(arvatarNum));//封面
            liveRoom.setAvatar(avatarsImages.get(arvatarNum));//头像与封面相同


            int liveDislNum = new Random().nextInt(liveDis.size());
            liveRoom.setDis(liveDis.get(liveDislNum));
            liveRoomList.add(liveRoom);

            int liveTitleNum = new Random().nextInt(liveTitles.length);
            liveRoom.setLiveTitle(liveTitles[liveTitleNum]);//直播标题

        }

        return liveRoomList;
    }
}
