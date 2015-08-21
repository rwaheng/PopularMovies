package com.udacity.rwaheng.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rwaheng on 8/17/2015.
 */
public class VideoBean implements Parcelable{


    private String id;
    @SerializedName("iso_639_1")
    private String iso6391;
    private String key;
    private String name;
    private String site;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(iso6391);
        dest.writeString(key);
        dest.writeString(name);
        dest.writeString(site);
        dest.writeString(type);

    }

    private VideoBean(Parcel in) {
        id=in.readString();
        iso6391=in.readString();
        key=in.readString();
        name=in.readString();
        site=in.readString();
        type=in.readString();
    }

    public static final Parcelable.Creator<VideoBean> CREATOR = new Parcelable.Creator<VideoBean>() {
        public VideoBean createFromParcel(Parcel in) {
            return new VideoBean(in);
        }

        public VideoBean[] newArray(int size) {
            return new VideoBean[size];
        }
    };
}
