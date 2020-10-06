package com.example.yndlingsfilm;

import android.content.Intent;
import android.media.Rating;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.yndlingsfilm.Data.Movie;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MovieDetailsFragment extends Fragment {

    Executor bgThread;
    Handler uiThread;

    ImageView imdbLink;
    TextView movieTitle;
    ImageView moviePic;
    TextView overview;
    TextView releaseDate;
    RatingBar rating;
    Button writeReviewButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        // hent rigtig movie parset fra det billede der har ført hertil.
        Movie movie = new Movie("tt0295297", "Harry Potter",
                "An orphaned boy enrolls in a school of wizardry," +
                        " where he learns the truth about himself," +
                        " his family and the terrible evil that haunts the magical world.",
                                    "10-01-2001", R.drawable.movie_pic, 5);

        imdbLink = view.findViewById(R.id.imdbLink);
        movieTitle = view.findViewById(R.id.movieTitle);
        moviePic = view.findViewById(R.id.moviePic);
        overview = view.findViewById(R.id.overview);
        releaseDate = view.findViewById(R.id.releaseDate);
        rating = view.findViewById(R.id.rating);
        writeReviewButton = view.findViewById(R.id.writeReviewButton);

        movieTitle.setText(movie.getMovieTitle());
        overview.setText(movie.getOverview());
        releaseDate.setText(movie.getReleaseDate());
        rating.setRating(movie.getRating());

        moviePic.setImageResource(movie.getMoviePic());


        writeReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right,
                        R.anim.slide_out_left).replace(R.id.fragment_nagivation, new WriteReviewFragment())
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
                            Uri.parse("https://www.imdb.com/title/" + movie.getMovieId()));
                    startActivity(browserIntent);
                } catch (Exception e){
                    // behandl fejl. evt vis cashed svar.
                    e.printStackTrace();
                }
            });
        });


        return view;
    }
}