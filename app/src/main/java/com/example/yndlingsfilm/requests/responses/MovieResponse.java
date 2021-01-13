package com.example.yndlingsfilm.requests.responses;

import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.Model.repositories.MovieListRepo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {

    @SerializedName("original_title")
    @Expose()
    private String movieTitle;

    @SerializedName("overview")
    @Expose()
    private String overview;

    @SerializedName("release_date")
    @Expose()
    private String release_date;

    @SerializedName("id")
    @Expose()
    private int movieId;

    @SerializedName("poster_path")
    @Expose()
    private String poster_path;

    @SerializedName("vote_average")
    @Expose()
    private float vote_average;

    @SerializedName("genre_ids;")
    @Expose()
    private  int[] genre_ids;


    public String getPoster_path() {
        return poster_path;
    }

    public float getVote_average() {
        return vote_average;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getMovieId() {
        return movieId;
    }


}
