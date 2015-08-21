package com.udacity.rwaheng.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rwaheng on 8/18/2015.
 */
public class PreviewResults {
        private Integer id;
        private Integer page;
        @SerializedName("results")
        private List<PreviewBean> results = new ArrayList<PreviewBean>();
        @SerializedName("total_pages")
        private Integer totalPages;
        @SerializedName("total_results")
        private Integer totalResults;

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

    public List<PreviewBean> getResults() {
        return results;
    }

    public void setResults(List<PreviewBean> results) {
        this.results = results;
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