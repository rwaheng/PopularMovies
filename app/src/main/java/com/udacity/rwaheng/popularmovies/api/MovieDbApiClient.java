package com.udacity.rwaheng.popularmovies.api;


import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class MovieDbApiClient {
    private static MovieDbApiServices movieclient;
    private static String api_key="<<Add key>>";//removed for submission

    public static MovieDbApiServices getMovieService() {
        if (movieclient == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                   .setLogLevel(RestAdapter.LogLevel.FULL)
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
