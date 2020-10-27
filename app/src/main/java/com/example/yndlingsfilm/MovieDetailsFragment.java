package com.example.yndlingsfilm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.yndlingsfilm.Data.Movie;
import com.example.yndlingsfilm.requests.MovieApi;
import com.example.yndlingsfilm.requests.ServiceGenerator;
import com.example.yndlingsfilm.requests.responses.MovieResponse;
import com.example.yndlingsfilm.requests.responses.DiscoverMoviesResponse;
import com.example.yndlingsfilm.util.Constants;
import com.example.yndlingsfilm.viewModels.MovieViewModel;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsFragment extends Fragment {
    private static final String TAG = "MovieDetailsFragment";

    Executor bgThread;
    Handler uiThread;

    ImageView imdbLink;
    TextView movieTitle;
    ImageView moviePic;
    TextView overview;
    TextView releaseDate;
    RatingBar rating;
    Button writeReviewButton;

    private MovieViewModel movieViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        testRetrofitRequest();

        // hent rigtig movie parset fra det billede der har ført hertil.
       /* Movie movie = new Movie("tt0295297", "Harry Potter",
                "An orphaned boy enrolls in a school of wizardry," +
                        " where he learns the truth about himself," +
                        " his family and the terrible evil that haunts the magical world.",
                                    "10-01-2001", R.drawable.movie_pic, 5, null);*/

        /*imdbLink = view.findViewById(R.id.imdbLink);
        movieTitle = view.findViewById(R.id.movieTitle);
        moviePic = view.findViewById(R.id.moviePic);
        overview = view.findViewById(R.id.overview);
        releaseDate = view.findViewById(R.id.releaseDate);
        rating = view.findViewById(R.id.rating);
        writeReviewButton = view.findViewById(R.id.writeReviewButton);

        movieTitle.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        releaseDate.setText(movie.getRelease_date());
        rating.setRating(movie.getVote_average());
        moviePic.setImageResource(movie.getPoster_path());


        writeReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = new WriteReviewFragment();
                f.setArguments(new Intent().putExtra("movieTitle", movie.getTitle()).getExtras());

                getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right,
                        R.anim.slide_out_left).replace(R.id.fragment_nagivation, f)
                        .addToBackStack(null).commit();
            }
        });

        imdbLink.setOnClickListener(view1 -> {
            //åben imdb-link.
            bgThread = Executors.newSingleThreadExecutor(); // håndtag til baggrundstråd
            uiThread = new Handler(Looper.getMainLooper()); // håndtag til forgrundstråd

            bgThread.execute(()->{
                try{
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.imdb.com/title/" + movie.getId()));
                    startActivity(browserIntent);
                } catch (Exception e){
                    // behandl fejl. evt vis cashed svar.
                    e.printStackTrace();
                }
            });
        });*/


        return view;
    }


    private void testRetrofitRequest(){
        MovieApi movieApi = ServiceGenerator.getMovieApi();

        Call<DiscoverMoviesResponse> responseCall =
                movieApi.discoverMovies(Constants.API_KEY, "popularity.desc");

        responseCall.enqueue(new Callback<DiscoverMoviesResponse>() {
            @Override
            public void onResponse(Call<DiscoverMoviesResponse> call, Response<DiscoverMoviesResponse> response) {
                Log.d(TAG, "onResponse:" + response.toString());
                if(response.code() == 200){
                    Log.d(TAG, "onResponse: " + response.body().toString() );
                    //gør noget med respons her

                }else{
                    try {
                        Log.d(TAG, "onResponse: " + response.errorBody().string());
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DiscoverMoviesResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + "failed");
            }
        });
    }
}