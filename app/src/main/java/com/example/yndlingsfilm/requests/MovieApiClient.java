package com.example.yndlingsfilm.requests;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.executors.AppExecutors;
import com.example.yndlingsfilm.requests.responses.DiscoverMoviesResponse;
import com.example.yndlingsfilm.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    private static MovieApiClient instance;
    // need data for the different categories. eg. popularmovies, trendingmovies, newMovies.
    // dont need class for each, just control of the query sent.
    private MutableLiveData<List<Movie>> movies;
    private static final String TAG = "MovieApiClient";
    private RetrieveMoviesRunnable retrieveMoviesRunnable;


    public static MovieApiClient getInstance() {
        if(instance == null){
            instance = new MovieApiClient();
        }
        return instance;
    }

    public MovieApiClient() {
        movies = new MutableLiveData<>();
    }

    public MutableLiveData<List<Movie>> getMovies() {
        return movies;
    }

    public void discoverMoviesApi(String query){
        if(retrieveMoviesRunnable != null){
            retrieveMoviesRunnable = null;
        }
        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query);
        final Future handler = AppExecutors.getInstance().getExecutorService().submit(retrieveMoviesRunnable);
        // drops the request if not done in 5 seconds to allow cached callbacks instead later.
        AppExecutors.getInstance().getExecutorService().schedule(new Runnable() {
            @Override
            public void run() {
                // let user know of the network error
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIME_LIMIT, TimeUnit.MILLISECONDS);

    }



    private class RetrieveMoviesRunnable implements Runnable{

        private String query;
        private Boolean cancelRequest;

        public RetrieveMoviesRunnable(String query) {
            this.query = query;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getMovies(query).execute();
                if(cancelRequest){
                    return;
                }
                if(response.code() == 200){
                    List<Movie> movieList =
                            new ArrayList<>(((DiscoverMoviesResponse)response.body()).getMovies());
                    movies.postValue(movieList);
                } else {
                    Log.e(TAG, "run: " + response.errorBody().string() );
                    movies.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                movies.postValue(null);
            }


        }

        private Call<DiscoverMoviesResponse> getMovies(String query) {
            return ServiceGenerator.getMovieApi().getMovies(
                    query, Constants.API_KEY, Constants.LANGUAGE, 1);

        }

        private void setCancelRequest(){
            Log.d(TAG, "setCancelRequest: cancelled request");
            cancelRequest = true;
        }
    }
}
