package com.pgzxc.live.data.bean;

/**
 * 礼物
 */

public class Gif {
    public String userName; //送礼人
    public String giftName; //礼物名
    public int giftType; //礼物图像
    public int gifNum; //送礼数
    public int avatar;//头像
    public String gitCost;//礼物费用
    private boolean isSelected;//是否选中

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public int getGiftType() {
        return giftType;
    }

    public void setGiftType(int giftType) {
        this.giftType = giftType;
    }

    public int getGifNum() {
        return gifNum;
    }

    public void setGifNum(int gifNum) {
        this.gifNum = gifNum;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getGitCost() {
        return gitCost;
    }

    public void setGitCost(String gitCost) {
        this.gitCost = gitCost;
    }
}
