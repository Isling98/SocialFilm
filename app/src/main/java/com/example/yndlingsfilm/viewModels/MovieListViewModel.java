package com.example.yndlingsfilm.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.Model.repositories.MovieListRepo;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MovieListRepo movieListRepo;

    public MovieListViewModel() {
        movieListRepo = MovieListRepo.getInstance();
    }

    public MutableLiveData<List<Movie>> getMovies() {
        return movieListRepo.getMovies();
    }

    public void discoverMoviesApi(String query){
        movieListRepo.discoverMoviesApi(query);
    }
}
