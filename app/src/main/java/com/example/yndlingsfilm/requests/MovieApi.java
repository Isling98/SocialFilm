package com.example.yndlingsfilm.requests;

import com.example.yndlingsfilm.Data.Movie;
import com.example.yndlingsfilm.requests.responses.MovieResponse;
import com.example.yndlingsfilm.requests.responses.DiscoverMoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    // get movie from id
    @GET("/3/movie")
    Call<MovieResponse> movie(
            @Query("movie_id") String id,
            @Query("api_key") String key
    );

    // discover movies
    @GET("/3/discover/movie")
    Call<DiscoverMoviesResponse> discoverMovies(
            @Query("api_key") String key,
            @Query("sort_by") String sortBy
    );

    // get popular movies
    @GET("/3/movie/popular")
    Call<Movie> getPopularMovies(
            @Query("api_key") String key,
            @Query("language") String language,
            @Query("page") int page

    );
}
