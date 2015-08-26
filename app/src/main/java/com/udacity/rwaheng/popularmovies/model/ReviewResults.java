package com.udacity.rwaheng.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rwaheng on 8/18/2015.
 */
public class ReviewResults {
        private Integer id;
        private Integer page;
        @SerializedName("results")
        private List<ReviewBean> reviews = new ArrayList<ReviewBean>();
        @SerializedName("total_pages")
        private Integer totalPages;
        @SerializedName("total_results")
        private Integer totalResults;

    @Override
    public String toString() {
        return "ReviewResults{" +
                "id=" + id +
                ", page=" + page +
                ", reviews=" + reviews +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<ReviewBean> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewBean> reviews) {
        this.reviews = reviews;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
}