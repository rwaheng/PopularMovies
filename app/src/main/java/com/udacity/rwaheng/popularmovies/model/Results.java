
package com.udacity.rwaheng.popularmovies.model;

import java.util.ArrayList;
import java.util.List;


public class Results {

    private Integer page;
    private ArrayList<Movie> results = new ArrayList<Movie>();
    private Integer total_pages;
    private Integer total_results;
  
    
    
    
    
    
    @Override
	public String toString() {
		return "Results [page=" + page + ", results=" + results + ", total_pages=" + total_pages + ", total_results="
				+ total_results + "]";
	}

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }
}
