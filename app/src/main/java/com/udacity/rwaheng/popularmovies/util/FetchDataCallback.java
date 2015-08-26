package com.udacity.rwaheng.popularmovies.util;



/**
 * Created by rwaheng on 8/21/2015.
 */
public interface FetchDataCallback {

    void onSuccess(Object results);
   // void onFailure(Exception exception);
    String getApiName();

}
