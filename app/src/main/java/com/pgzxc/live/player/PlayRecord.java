package com.pgzxc.live.player;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 播放记录
 */
public class PlayRecord implements Parcelable, Cloneable {

    public static final int TYPE_NONE = 0;
    public static final int TYPE_LIVE = 1;
    public static final int TYPE_AOD = 2;
    private int type;//播放类型
    private String id;
    private String liveUrl;
    private String liveName;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }

    public String getLiveName() {
        return liveName;
    }

    public void setLiveName(String liveName) {
        this.liveName = liveName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeString(this.id);
        dest.writeString(this.liveUrl);
        dest.writeString(this.liveName);
    }

    public PlayRecord() {
    }

    protected PlayRecord(Parcel in) {
        this.type = in.readInt();
        this.id = in.readString();
        this.liveUrl = in.readString();
        this.liveName = in.readString();
    }

    public static final Creator<PlayRecord> CREATOR = new Creator<PlayRecord>() {
        @Override
        public PlayRecord createFromParcel(Parcel source) {
            return new PlayRecord(source);
        }

        @Override
        public PlayRecord[] newArray(int size) {
            return new PlayRecord[size];
        }
    };
}
