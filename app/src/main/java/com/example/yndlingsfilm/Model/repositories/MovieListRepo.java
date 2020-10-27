package com.example.yndlingsfilm.Model.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.requests.MovieApiClient;

import java.util.List;

public class MovieListRepo {

    private static MovieListRepo instance;
    private MovieApiClient movieApiClient;


    public static MovieListRepo getInstance() {
        if(instance == null){
            instance = new MovieListRepo();
        }
        return instance;
    }

    public MovieListRepo() {
        movieApiClient = MovieApiClient.getInstance();

    }

    public MutableLiveData<List<Movie>> getMovies() {
        return movieApiClient.getMovies();
    }

    public void discoverMoviesApi(String query){
        movieApiClient.discoverMoviesApi(query);
    }
}
