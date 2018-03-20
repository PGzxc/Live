package com.pgzxc.live.data.bean;

import android.support.annotation.NonNull;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import java.io.Serializable;


/**
 * 直播间
 */

public class LiveRoom implements Serializable, MultiItemEntity {
    /**
     * 头像
     */
    @NonNull
    private int avatar;
    /**
     * 名称
     */
    private String userName;
    /**
     * 城市
     */
    private String city;
    /**
     * 封面
     */
    private int cover;
    /**
     * 观看人数
     */
    private int audienceNum;
    /**
     * 直播标题
     */
    private String liveTitle;
    /**
     * 等级图片
     */
    private int rankLev;
    /**
     * 主播与用户的距离
     */
    private String dis;
    private int itemType;
    /**
     * 聊天室id
     */
    private String chatroomId;
    /**
     * 主播id
     */
    private String anchorId;
    /**
     * 直播id
     */
    private String id;

    @Override
    public int getItemType() {
        return itemType;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public int getAudienceNum() {
        return audienceNum;
    }

    public void setAudienceNum(int audienceNum) {
        this.audienceNum = audienceNum;
    }

    public String getLiveTitle() {
        return liveTitle;
    }

    public void setLiveTitle(String liveTitle) {
        this.liveTitle = liveTitle;
    }

    public int getRankLev() {
        return rankLev;
    }

    public void setRankLev(int rankLev) {
        this.rankLev = rankLev;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(String chatroomId) {
        this.chatroomId = chatroomId;
    }

    public String getAnchorId() {
        return anchorId;
    }

    public void setAnchorId(String anchorId) {
        this.anchorId = anchorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
