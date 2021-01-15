package com.example.yndlingsfilm.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.Model.repositories.MovieListRepo;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MovieListRepo movieListRepo;
    private boolean isSearching;

    public boolean isSearching() {
        return isSearching;
    }

    public MovieListViewModel() {
        movieListRepo = MovieListRepo.getInstance();
    }

    public MutableLiveData<List<Movie>> getMovies() {
        return movieListRepo.getMovies();
    }

    public MutableLiveData<List<Movie>> getTopratedMovies() {
        return movieListRepo.getTopRatedMovies();
    }

    public MutableLiveData<List<Movie>> getPopularMovies() {
        return movieListRepo.getPopularMovies();
    }

    public MutableLiveData<List<Movie>> getUpcomingMovies() {
        return movieListRepo.getUpcomingMovies();
    }

    public MutableLiveData<List<Movie>> getNowPlayingMovies() {
        return movieListRepo.getNowPlayingMovies();
    }

    public void discoverMoviesApi(String query){
        movieListRepo.discoverMoviesApi(query);
    }

    public void searchMovies(String searchWord){
        isSearching = true;
        movieListRepo.searchMovies(searchWord);
    }

    public Movie searchMovieForSearch(int movieId, String key){
        try{
            return movieListRepo.searchMovieForSearch(movieId,key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean backNav(){
        if(isSearching){
            isSearching = false;
            return false;
        }
        return true;
    }
}
