package com.udacity.rwaheng.popularmovies.util;

/**
 * Created by rwaheng on 8/21/2015.
 */
public class YouTubeHelper {
    final static String YOUTUBE_IMG="http://img.youtube.com/vi/";
    final static String YOUTUBE_VIDEO="http://www.youtube.com/watch?v=";
    public static String makeImgURL(String key){
        return YOUTUBE_IMG+key+"/"+0+".jpg";
    }
    public static String makeImgURL(String key,int index){
        return YOUTUBE_IMG+key+"/"+index+".jpg";
    }

    public static String makeVideoURL(String key){
        return YOUTUBE_VIDEO+key;
    }
}
