package com.udacity.rwaheng.popularmovies.util;

import android.os.AsyncTask;
import android.util.Log;

import com.udacity.rwaheng.popularmovies.api.MovieDbApiClient;
import com.udacity.rwaheng.popularmovies.api.MovieDbApiServices;
import com.udacity.rwaheng.popularmovies.model.MovieBean;
import com.udacity.rwaheng.popularmovies.model.movie.MovieAllBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Created by rwaheng on 8/21/2015.
 */

public abstract  class FetchDataAsync  extends AsyncTask implements FetchDataCallback {

    public static final String LOG_TAG=FetchDataAsync.class.getSimpleName();
    public static final String GET_VIDEOS="getvideos";
    public static final String GET_REVIEWS="getreviews";
    private MovieDbApiServices movieDbApiServices;


    public FetchDataAsync() {
    }

    @Override
    protected Object doInBackground(Object[] params) {
        String api=getApiName();
        if (movieDbApiServices == null)
            movieDbApiServices = MovieDbApiClient.getMovieService();

        if(api.equals(FetchDataAsync.GET_REVIEWS)){
            return movieDbApiServices.getReviews((String) params[0]);
        }else if(api.equals(FetchDataAsync.GET_VIDEOS)){
            return movieDbApiServices.getVideos((String) params[0]);
        }
        return null;
    }



    @Override
    protected void onPostExecute(Object results) {
        //mVideos=((VideoResults)results).getVideos();
        onSuccess((Object) results);
    }


}