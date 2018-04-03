package com.example.android.bakingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dell on 19/02/2018.
 */

public class Step implements Parcelable {

    int id;
    String shortDesc;
    String desc;
    String videoUrl;
    String thumbUrl;


    public Step(int id, String shortDesc, String desc, String videoUrl, String thumbUrl) {
        this.id = id;
        this.shortDesc = shortDesc;
        this.desc = desc;
        this.videoUrl = videoUrl;
        this.thumbUrl=thumbUrl;
    }


    protected Step(Parcel in) {
        id = in.readInt();
        shortDesc = in.readString();
        desc = in.readString();
        videoUrl = in.readString();
        thumbUrl = in.readString();
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(shortDesc);
        parcel.writeString(desc);
        parcel.writeString(videoUrl);
        parcel.writeString(thumbUrl);
    }
}
