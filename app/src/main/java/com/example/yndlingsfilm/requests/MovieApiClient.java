package com.example.yndlingsfilm.requests;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.Model.Review;
import com.example.yndlingsfilm.Model.User;
import com.example.yndlingsfilm.executors.AppExecutors;
import com.example.yndlingsfilm.requests.responses.DiscoverMoviesResponse;
import com.example.yndlingsfilm.requests.responses.MovieResponse;
import com.example.yndlingsfilm.requests.responses.ReviewResponse;
import com.example.yndlingsfilm.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    private static MovieApiClient instance;
    private MutableLiveData<List<Movie>> movies;
    private MutableLiveData<List<Movie>> topRatedMovies;
    private MutableLiveData<List<Movie>> popularMovies;
    private MutableLiveData<List<Movie>> upcomingMovies;
    private MutableLiveData<List<Movie>> nowPlayingMovies;
    private MutableLiveData<Movie> movieForSearch;

    private static final String TAG = "MovieApiClient";
    private RetrieveMoviesRunnable retrieveMoviesRunnable;
    private SearchMoviesRunnable searchMoviesRunnable;
    private GetMovieForSearchCallable getMovieForSearchCallable;
    private Movie movie;


    public static MovieApiClient getInstance() {
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    public MovieApiClient() {
        movies = new MutableLiveData<>();
        topRatedMovies = new MutableLiveData<>();
        popularMovies = new MutableLiveData<>();
        upcomingMovies = new MutableLiveData<>();
        nowPlayingMovies = new MutableLiveData<>();
        movieForSearch = new MutableLiveData<>();
    }

    public MutableLiveData<Movie> getMovieForSearch() {
        return movieForSearch;
    }

    public MutableLiveData<List<Movie>> getMovies() {
        return movies;
    }

    public MutableLiveData<List<Movie>> getTopRatedMovies() {
        return topRatedMovies;
    }

    public MutableLiveData<List<Movie>> getPopularMovies() {
        return popularMovies;
    }

    public MutableLiveData<List<Movie>> getUpcomingMovies() {
        return upcomingMovies;
    }

    public MutableLiveData<List<Movie>> getNowPlayingMovies() {
        return nowPlayingMovies;
    }


    public void discoverMoviesApi(String query) {
        if (retrieveMoviesRunnable != null) {
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


    private class RetrieveMoviesRunnable implements Runnable {

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
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    //kontrol af query
                    List<Movie> movieList =
                            new ArrayList<>(((DiscoverMoviesResponse) response.body()).getMovies());
                    switch (query) {
                        // add evt til allMovies også
                        case "top_rated":
                            topRatedMovies.postValue(movieList);
                            break;
                        case "popular":
                            popularMovies.postValue(movieList);
                            break;
                        case "upcoming":
                            upcomingMovies.postValue(movieList);
                            break;
                        case "now_playing":
                            nowPlayingMovies.postValue(movieList);
                            break;
                    }
                } else {
                    Log.e(TAG, "run: " + response.errorBody().string());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private Call<DiscoverMoviesResponse> getMovies(String query) {
            return ServiceGenerator.getMovieApi().getMovies(
                    query, Constants.API_KEY, Constants.LANGUAGE, 1);

        }

        private void setCancelRequest() {
            Log.d(TAG, "setCancelRequest: cancelled request");
            cancelRequest = true;
        }
    }

    // search movies
    public void searchMovies(String searchWord) {
        if (searchMoviesRunnable != null) {
            searchMoviesRunnable = null;
        }
        searchMoviesRunnable = new SearchMoviesRunnable(searchWord);
        final Future handler = AppExecutors.getInstance().getExecutorService().submit(searchMoviesRunnable);
        // drops the request if not done in 5 seconds to allow cached callbacks instead later.
        AppExecutors.getInstance().getExecutorService().schedule(new Runnable() {
            @Override
            public void run() {
                // let user know of the network error
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIME_LIMIT, TimeUnit.MILLISECONDS);
    }

    private class SearchMoviesRunnable implements Runnable {

        private String searchWord;
        private Boolean cancelRequest;

        public SearchMoviesRunnable(String searchWord) {
            this.searchWord = searchWord;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = searchMovies(searchWord).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    List<Movie> movieList =
                            new ArrayList<>(((DiscoverMoviesResponse) response.body()).getMovies());
                    movies.postValue(movieList);
                } else {
                    movies.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                movies.postValue(null);
            }
        }

        private Call<DiscoverMoviesResponse> searchMovies(String searchWord) {
            return ServiceGenerator.getMovieApi().searchMovies(
                    searchWord, Constants.API_KEY, Constants.LANGUAGE, 1);

        }

        private void setCancelRequest() {
            Log.d(TAG, "setCancelRequest: cancelled request");
            cancelRequest = true;
        }
    }






    public Movie SearchMovieForSearch(int movieId, String key) throws ExecutionException, InterruptedException {
        if(getMovieForSearchCallable != null){
            getMovieForSearchCallable = null;
        }
        getMovieForSearchCallable = new GetMovieForSearchCallable(movieId,key);

        final Future handler = AppExecutors.getInstance().getExecutorService().submit(getMovieForSearchCallable);
        movie = (Movie) handler.get();
        // drops the request if not done in 5 seconds to allow cached callbacks instead later.
        AppExecutors.getInstance().getExecutorService().schedule(new Runnable() {
            @Override
            public void run() {
                // let user know of the network error
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIME_LIMIT, TimeUnit.MILLISECONDS);
        return movie;
    }

    private class GetMovieForSearchCallable implements Callable {

        private int movieId;
        private String key;
        private Boolean cancelRequest;

        public GetMovieForSearchCallable(int movieId, String key) {
            this.movieId = movieId;
            this.key = key;
            cancelRequest = false;
        }

        @Override
        public Movie call() {

            try{
                Response response = getMovie(movieId,key).execute();
                if(cancelRequest){
                    return null;
                }
                Log.d(TAG, "@@" + movieId);

                if (response.code() == 200){
                String movieTitle = ((MovieResponse) response.body()).getMovieTitle();
                String overview = ((MovieResponse) response.body()).getOverview();
                String release_date = ((MovieResponse) response.body()).getRelease_date();
                int movieId =  ((MovieResponse) response.body()).getMovieId();
                String posterPath = ((MovieResponse) response.body()).getPoster_path();
                float vote = ((MovieResponse) response.body()).getVote_average();
                int[] genre_ids = ((MovieResponse) response.body()).getGenre_ids();


                Log.d(TAG,"Movietitle " + movieTitle);
                Movie movie = new Movie(movieId,movieTitle,overview,release_date,posterPath,vote,genre_ids);

                    return movie;
                }else{

                    Log.d(TAG, "run: succes from else" + response.code());
                }
            } catch (IOException e){
                e.printStackTrace();
                Log.d(TAG, "run: error line____________ from getUser()");
            } return null;
        }

        private Call<MovieResponse> getMovie(int movieId, String key){

            return ServiceGenerator.getMovieApi().movie(movieId,key);
        }

        private void setCancelRequest(){
            Log.d(TAG, "setCancelRequest: cancelled request");
            cancelRequest = true;
        }
    }
}
