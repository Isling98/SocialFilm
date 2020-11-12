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
    public MutableLiveData<List<Movie>> getTopRatedMovies() {
        return movieApiClient.getTopRatedMovies();
    }
    public MutableLiveData<List<Movie>> getPopularMovies() {
        return movieApiClient.getPopularMovies();
    }
    public MutableLiveData<List<Movie>> getUpcomingMovies() {
        return movieApiClient.getUpcomingMovies();
    }
    public MutableLiveData<List<Movie>> getNowPlayingMovies() {
        return movieApiClient.getNowPlayingMovies();
    }

    public void discoverMoviesApi(String query){
        movieApiClient.discoverMoviesApi(query);
    }
    public void searchMovies(String searchWord){
        movieApiClient.searchMovies(searchWord);
    }
}
