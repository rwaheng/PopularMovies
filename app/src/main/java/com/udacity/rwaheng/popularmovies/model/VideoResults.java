package com.udacity.rwaheng.popularmovies.model;

/**
 * Created by rwaheng on 8/17/2015.
 */


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
        import java.util.List;


public class VideoResults {
    private Integer id;
    @SerializedName("results")
    private List<VideoBean> videos = new ArrayList<VideoBean>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<VideoBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoBean> videos) {
        this.videos = videos;
    }
}
