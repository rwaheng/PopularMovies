package com.udacity.rwaheng.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rwaheng on 8/18/2015.
 */
public class PreviewBean implements Parcelable{
        private String id;
        private String author;
        private String content;
        private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(author);
        dest.writeString(content);
        dest.writeString(url);

    }

    private PreviewBean(Parcel in) {
       id=in.readString();
        author=in.readString();
        content=in.readString();
        url=in.readString();
    }

    public static final Parcelable.Creator<PreviewBean> CREATOR = new Parcelable.Creator<PreviewBean>() {
        public PreviewBean createFromParcel(Parcel in) {
            return new PreviewBean(in);
        }

        public PreviewBean[] newArray(int size) {
            return new PreviewBean[size];
        }
    };
}
