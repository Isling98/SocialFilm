package com.example.yndlingsfilm.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yndlingsfilm.Data.Movie;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<Movie> movies = new MutableLiveData<>();

    public MovieViewModel(MutableLiveData<Movie> movies) {
        this.movies = movies;
    }

    public LiveData<Movie> getMovies() {
        return movies;
    }
}
