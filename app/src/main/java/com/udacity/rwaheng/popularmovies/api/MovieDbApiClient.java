package com.udacity.rwaheng.popularmovies.api;


import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class MovieDbApiClient {
    private static MovieDbApiServices movieclient;
    private static String api_key="8b7ea7261c27a2eb09a9c14725e9e588";//removed for submission
//8b7ea7261c27a2eb09a9c14725e9e588
    public static MovieDbApiServices getMovieService() {
        if (movieclient == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                  //.setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("http://api.themoviedb.org/3")
                    .setRequestInterceptor(new RequestInterceptor() {
                        @Override
                        public void intercept(RequestFacade request) {
                            request.addQueryParam("api_key", api_key);
                        }
                    })
                    .build();

            movieclient = restAdapter.create(MovieDbApiServices.class);

            return movieclient;

        }

        return movieclient;

    }

}
