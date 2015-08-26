package com.udacity.rwaheng.popularmovies.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.StringTokenizer;

/**
 * Created by rwaheng on 8/6/2015.
 */

public class PreferencesManager {

    public static final String PREF_NAME= "movie_preference";
    public static final String BY_POPULARITY="popularity.desc";
    public static final String BY_RATING="vote_average.desc";

    public static final String FAV_MOVIE="fav_movie";




    public static final String KEY_SORT_BY="sort_by";
    public static final String PAGE_COUNT="page_count";



    private static PreferencesManager sInstance;
    private final SharedPreferences mPref;

    private PreferencesManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized PreferencesManager initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
        return sInstance;
    }


    public void setValueSortBy(String value) {
        mPref.edit()
                .putString(KEY_SORT_BY, value)
                .apply();
    }
    public void addFavMovie(String value) {
        String tmp= mPref.getString(FAV_MOVIE, "");

        if(!tmp.contains(value)){
            tmp=tmp+"|"+value;
        }

        mPref.edit()
                .putString(FAV_MOVIE, tmp)
                .apply();
    }

    public String getFavMovie() {
        return mPref.getString(FAV_MOVIE, "");
    }

    public void removeFavMovie(String value) {
        String tmp= mPref.getString(FAV_MOVIE, "");
        StringBuffer sb= new StringBuffer();

        if(tmp.contains(value)){
            StringTokenizer st= new StringTokenizer(tmp,"|");
            while (st.hasMoreElements()) {
                String str= (String) st.nextElement();
                if(!st.equals(value))
                sb.append(str+"|");
            }
        }
        mPref.edit()
                .putString(FAV_MOVIE, sb.toString())
                .apply();

    }


    public void setValuePageCount(int value) {
        mPref.edit()
                .putInt(PAGE_COUNT, value)
                .apply();
    }


    public String getValueSortBy() {
        return mPref.getString(KEY_SORT_BY, BY_POPULARITY);
    }

    public int getValuePageCount() {
        return mPref.getInt(PAGE_COUNT, 1);
    }



    public void remove(String key) {
        mPref.edit()
                .remove(key)
                .apply();
    }

    public boolean clear() {
        return mPref.edit()
                .clear()
                .commit();
    }
}