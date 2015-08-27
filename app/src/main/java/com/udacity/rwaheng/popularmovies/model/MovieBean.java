
package com.udacity.rwaheng.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.udacity.rwaheng.popularmovies.model.movie.MovieAllBean;
import com.udacity.rwaheng.popularmovies.util.ArrayManipulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MovieBean implements Parcelable {

    private Boolean adult;
    private String backdrop_path;
    private List<Integer> genre_ids = new ArrayList<Integer>();
    private Integer id;
    private String original_language;
    private String original_title;
    private String overview;
    private String release_date;
    private String poster_path;
    private Double popularity;
    private String title;
    private Boolean video;
    private Double vote_average;
    private Integer vote_count;


    public MovieBean(MovieBean movie) {
       // this.
    }

    public MovieBean(MovieAllBean movie) {
        this.adult=movie.getAdult();
        this.backdrop_path=movie.getBackdropPath();
        this.id=movie.getId();
        this.original_language=movie.getOriginalLanguage();
        this.original_title=movie.getOriginalTitle();
        this.overview=movie.getOverview();
        this.release_date=movie.getReleaseDate();
        this.poster_path=movie.getPosterPath();
        this.popularity=movie.getPopularity();
        this.title=movie.getTitle();
        this.video=movie.getVideo();
        this.vote_average=movie.getVoteAverage();
        this.vote_count=movie.getVoteCount();
    }

    public MovieBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Movies{" +
                "title='" + title + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", genre_ids=" + genre_ids +
                ", id=" + id +
                ", original_language='" + original_language + '\'' +
                ", original_title='" + original_title + '\'' +
                ", overview='" + overview + '\'' +
                ", release_date='" + release_date + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", popularity=" + popularity +
                ", adult=" + adult +
                ", video=" + video +
                ", vote_average=" + vote_average +
                ", vote_count=" + vote_count +
                '}';
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

     public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    private MovieBean(Parcel in) {
        adult=in.readString().equals("true")?true : false;
        backdrop_path=in.readString();
         genre_ids=ArrayManipulator.convertToIntegerList(in.createIntArray());
        id=in.readInt();
        original_language=in.readString();
        original_title=in.readString();
        overview=in.readString();
        release_date=in.readString();
        poster_path=in.readString();
        popularity=in.readDouble();
        title=in.readString();
        video=in.readString().equals("true")?true : false;
        vote_average=in.readDouble();
        vote_count=in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString( (adult ? "true" : "false"));
        dest.writeString(backdrop_path);
       // private List<Integer> genre_ids = new ArrayList<Integer>();
        dest.writeIntArray(ArrayManipulator.convertToIntArray(genre_ids));
        dest.writeInt(id);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(poster_path);
        dest.writeDouble( popularity);
        dest.writeString(title);
        dest.writeString( (video ? "true" : "false"));
        dest.writeDouble( vote_average);
        dest.writeInt(vote_count);

    }


    public static final Parcelable.Creator<MovieBean> CREATOR = new Parcelable.Creator<MovieBean>() {
        public MovieBean createFromParcel(Parcel in) {
            return new MovieBean(in);
        }

        public MovieBean[] newArray(int size) {
            return new MovieBean[size];
        }
    };



}
