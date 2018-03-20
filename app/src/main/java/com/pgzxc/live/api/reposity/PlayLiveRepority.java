package com.pgzxc.live.api.reposity;

import android.content.Context;

import com.pgzxc.live.R;
import com.pgzxc.live.data.bean.AudienceBean;
import com.pgzxc.live.data.bean.LiveRoom;
import com.pgzxc.live.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 播放
 */

public class PlayLiveRepority {

    private Context mContext;

    public PlayLiveRepority(Context mContext) {
        this.mContext = mContext;
    }

    public List<String> getLiveStreams() {
        List<String> info = new ArrayList<>();
        String[] arrayStream = mContext.getResources().getStringArray(R.array.live_streams);
        info.addAll(Arrays.asList(arrayStream));
        return info;
    }

    /**
     * 直播观众
     *
     * @return
     */
    public List<AudienceBean> getLiveAudience() {
        List<AudienceBean> liveRoomList = new ArrayList<>();
        ArrayList<Integer> avatarsImages = new ArrayList<>();
        String[] avatars = mContext.getResources().getStringArray(R.array.avatars); //头像，封面
        String[] userNames = mContext.getResources().getStringArray(R.array.userNames); //用户名
        for (String str : avatars) {
            int resId = ResourceUtils.getResId(str, R.drawable.class);
            avatarsImages.add(resId);
        }
        for (String avatar : avatars) {
            AudienceBean audienceBean = new AudienceBean();
            int arvatarNum = new Random().nextInt(avatars.length);
            audienceBean.setCover(avatarsImages.get(arvatarNum));//头像
            audienceBean.setCover(avatarsImages.get(arvatarNum));//封面

            int userNameNum = new Random().nextInt(userNames.length);
            audienceBean.setName(userNames[userNameNum]);//用户名

            liveRoomList.add(audienceBean);
        }
        return liveRoomList;
    }

    /**
     * 获取随机头像
     * @return
     */
    public int getAvatar() {
        ArrayList<Integer> avatarsImages = new ArrayList<>();
        String[] avatars = mContext.getResources().getStringArray(R.array.avatars); //头像，封面
        for (String str : avatars) {
            int resId = ResourceUtils.getResId(str, R.drawable.class);
            avatarsImages.add(resId);
        }
        int arvatarNum = new Random().nextInt(avatars.length);
        return avatarsImages.get(arvatarNum);//头像
    }

}
