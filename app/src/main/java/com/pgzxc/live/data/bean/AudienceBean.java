package com.pgzxc.live.data.bean;

import java.io.Serializable;

/**
 * 观众
 */
public class AudienceBean implements Serializable {

    String anchorId; //主播id
    int cover;//用户头像地址url
    String name;  //昵称

    public String getAnchorId() {
        return anchorId;
    }

    public void setAnchorId(String anchorId) {
        this.anchorId = anchorId;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
