package com.example.yndlingsfilm.requests.responses;

import com.example.yndlingsfilm.Data.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
// kan nok være generel for alle "sort_by"
public class DiscoverMoviesResponse {

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("total_results")
    @Expose
    private int total_results;

    @SerializedName("total_pages")
    @Expose
    private int total_pages;



    @SerializedName("results")
    @Expose
    private List<Movie> movies;


    public int getPage() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "PopularMoviesResponse{" +
                "page=" + page +
                ", total_results=" + total_results +
                ", total_pages=" + total_pages +
                ", movies=" + movies +
                '}';
    }
}
