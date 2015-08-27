package com.udacity.rwaheng.popularmovies.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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
    public boolean addFavMovie(String value) {
        String movie= mPref.getString(FAV_MOVIE, "");

        boolean added_flag = false;

        if (null == movie || movie.equals("")) {
            movie = value;
            added_flag=true;
        } else {
            StringTokenizer st = new StringTokenizer(movie,"|");
            String tmp = null;
            while (st.hasMoreTokens()) {
                tmp = st.nextToken();
                if (value.equals(tmp))
                    added_flag=true;
            }
            if(!added_flag){
                movie=movie+"|"+value;
            }

        }

        mPref.edit()
                .putString(FAV_MOVIE, movie)
                .apply();

        return added_flag;
    }

    public String getFavMovie() {
        return mPref.getString(FAV_MOVIE, "");
    }

    public boolean removeFavMovie(String value) {
        String movie= mPref.getString(FAV_MOVIE, "");

        boolean remove_flag = false;
        StringTokenizer st = new StringTokenizer(movie, "|");
        StringBuffer sb = new StringBuffer();
        String tmp = null;
        int token_counter=0;
        while (st.hasMoreTokens()) {
            tmp = st.nextToken();

            if (!value.equals(tmp))
                sb.append(tmp+"|");
            else
                remove_flag = true;

            token_counter++;

            Log.v("token",token_counter+""+tmp);
        }

        if(null!=sb && sb.length()>2)
        movie=sb.substring(0, sb.length()-1).toString();
        else if(token_counter<=1){
            movie="";
        }

        mPref.edit()
                .putString(FAV_MOVIE, movie)
                .apply();

        return remove_flag;


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