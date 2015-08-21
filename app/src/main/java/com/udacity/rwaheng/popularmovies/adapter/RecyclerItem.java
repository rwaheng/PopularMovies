package com.udacity.rwaheng.popularmovies.adapter;


import com.udacity.rwaheng.popularmovies.model.MovieBean;

/**
 * Created by rwaheng on 8/1/2015.
 */
public class RecyclerItem{

    final static private String IMAGE_BASE_URLl = "http://image.tmdb.org/t/p/";
    final static private String IMAGE_VVSMALL_SIZE = "w92";
    final static private String IMAGE_VSMALL_SIZE = "w154";
    final static private String IMAGE_SMALL_SIZE = "w185";
    final static private String IMAGE_MEDIUM_SIZE = "w342";
    final static private String IMAGE_LARGE_SIZE = "w500";
    final static private String IMAGE_VLARGE_SIZE = "w780";
    final static private String NO_PICTURE = "http://i.media-imdb.com/images/SFa6c7a966d6dcebed648b97af73c87f0d/nopicture/67x98/film.png";

    private MovieBean movie;
    private static String defaultSize=IMAGE_MEDIUM_SIZE;

    public RecyclerItem() {
        movie=new MovieBean();

    }
    public RecyclerItem(MovieBean movie) {
        this.movie=movie;
    }

    public MovieBean getMovie() {
        return movie;
    }

    public void setMovie(MovieBean movie) {
        this.movie = movie;
    }

    public static String makeTmdbURL(String path){
        String url;
        if(null!=path)
            url= IMAGE_BASE_URLl+RecyclerItem.getDefaultSize()+path;
        else
           url= NO_PICTURE;

        return  url;
    }

    public static String getDefaultSize() {
        return defaultSize;
    }

    public void setDefaultSize(String defaultSize) {
        this.defaultSize = defaultSize;
    }
}
