package com.example.yndlingsfilm.NavigationBar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.MovieDetailsFragment;
import com.example.yndlingsfilm.NavigationBar.Adapters.OnMovieListener;
import com.example.yndlingsfilm.NavigationBar.Adapters.VerticalAdapter;
import com.example.yndlingsfilm.R;
import com.example.yndlingsfilm.viewModels.MovieListViewModel;

import java.util.List;

public class SearchFragment extends Fragment implements OnMovieListener {


    private RecyclerView verticalRecyclerView;
    private VerticalAdapter verticalAdapter;
    private MovieListViewModel movieListViewModel;
    private static final String TAG = "SearchFragment";
    private boolean isCreated;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        verticalRecyclerView = view.findViewById(R.id.recyclerview1);
        verticalRecyclerView.setHasFixedSize(true);

        // init searchview:
        SearchView searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                movieListViewModel.searchMovies(s);
                return false;
            }
            // vil vi ikke bruge
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        subscribeObservers();
        initRecyclerView();
        if(!isCreated){
            discoverMoviesApi("popular");
            discoverMoviesApi("top_rated");
            discoverMoviesApi("upcoming");
            discoverMoviesApi("now_playing");
            isCreated = true;
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initRecyclerView(){
        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        verticalAdapter = new VerticalAdapter(getContext(), this);
        verticalRecyclerView.setAdapter(verticalAdapter);
    }

    private void subscribeObservers(){
        movieListViewModel.getPopularMovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                // sæt filmene her
                if(movies != null){
                    Log.d(TAG, "onChanged: -------------popular" );
                    for(Movie movie: movies){
                        Log.d(TAG, "onChanged: " + movie.getTitle());
                    }
                    verticalAdapter.setCategoryList(movies, "popular");
                }
            }
        });
        movieListViewModel.getNowPlayingMovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if(movies != null){
                    Log.d(TAG, "onChanged: -------------now playing" );
                    for(Movie movie: movies){
                        Log.d(TAG, "onChanged: " + movie.getTitle());
                    }
                    verticalAdapter.setCategoryList(movies, "nowPlaying");
                }
            }
        });
        movieListViewModel.getTopratedMovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if(movies != null){
                    Log.d(TAG, "onChanged: -------------toprated" );
                    for(Movie movie: movies){
                        Log.d(TAG, "onChanged: " + movie.getTitle());
                    }
                    verticalAdapter.setCategoryList(movies, "topRated");
                }
            }
        });
        movieListViewModel.getUpcomingMovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                Log.d(TAG, "onChanged: -------------upcoming" );
                if (movies != null) {
                    for (Movie movie : movies) {
                        Log.d(TAG, "onChanged: " + movie.getTitle());
                    }
                    verticalAdapter.setCategoryList(movies, "upcoming");
                }
            }
        });
        movieListViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (movies != null) {
                    for (Movie movie : movies) {
                        Log.d(TAG, "onChanged: " + movie.getTitle());
                    }
                    verticalAdapter.setCategoryList(movies, "searchMovies");
                }
            }
        });
    }

    private void discoverMoviesApi(String query){
        movieListViewModel.discoverMoviesApi(query);
    }

    @Override
    public void onMovieClick(int position) {

        Fragment fragment = new MovieDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", verticalAdapter.getClickedMovie(position));
        fragment.setArguments(bundle);
        Log.d(TAG, "onMovieClick: " +bundle.getParcelable("movie").toString());
        getFragmentManager().beginTransaction().replace
                (R.id.fragment_nagivation, fragment).addToBackStack(null).commit();

    }
}