package com.example.yndlingsfilm.requests.responses;

import com.example.yndlingsfilm.Model.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {

    @SerializedName("movie")
    @Expose()
    private Movie movie;

    public Movie getMovie() {
        return movie;
    }
}
