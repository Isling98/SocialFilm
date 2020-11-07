package com.example.yndlingsfilm.NavigationBar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.NavigationBar.Adapters.ModelVertical;
import com.example.yndlingsfilm.NavigationBar.Adapters.OnMovieListener;
import com.example.yndlingsfilm.NavigationBar.Adapters.VerticalAdapter;
import com.example.yndlingsfilm.R;
import com.example.yndlingsfilm.viewModels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements OnMovieListener {


    private RecyclerView verticalRecyclerView;
    private VerticalAdapter verticalAdapter;
    private MovieListViewModel movieListViewModel;
    private List<List<Movie>> categoryList;

    private static final String TAG = "SearchFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        verticalRecyclerView = view.findViewById(R.id.recyclerview1);
        verticalRecyclerView.setHasFixedSize(true);


        subscribeObservers();
        discoverMoviesApi("top_rated");
        discoverMoviesApi("popular");
        discoverMoviesApi("upcoming");
        initRecyclerView();



        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void initRecyclerView(){
        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        verticalAdapter = new VerticalAdapter(getContext(), this);
        verticalRecyclerView.setAdapter(verticalAdapter);

    }



    private void subscribeObservers(){
        movieListViewModel.getPopularMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                // sæt filmene her
                if(movies != null){
                    Log.d(TAG, "onChanged: -------------popular" );
                    for(Movie movie: movies){
                        Log.d(TAG, "onChanged: " + movie.getTitle());
                    }
                }
                verticalAdapter.setCategoryList(movies, "popular");
        }
        });
        movieListViewModel.getLatestMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if(movies != null){
                    Log.d(TAG, "onChanged: -------------latest" );
                    for(Movie movie: movies){
                        Log.d(TAG, "onChanged: " + movie.getTitle());
                    }
                }
                verticalAdapter.setCategoryList(movies, "latest");
            }
        });
        movieListViewModel.getTopratedMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if(movies != null){
                    Log.d(TAG, "onChanged: -------------toprated" );
                    for(Movie movie: movies){
                        Log.d(TAG, "onChanged: " + movie.getTitle());
                    }
                }
                verticalAdapter.setCategoryList(movies, "topRated");
            }
        });
        movieListViewModel.getUpcomingMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                Log.d(TAG, "onChanged: -------------upcoming" );
                if (movies != null) {
                    for (Movie movie : movies) {
                        Log.d(TAG, "onChanged: " + movie.getTitle());
                    }
                }
                verticalAdapter.setCategoryList(movies, "upcoming");
            }
        });
    }

    private void discoverMoviesApi(String query){
        movieListViewModel.discoverMoviesApi(query);
    }

    @Override
    public void onMovieClick(int position) {

    }
}