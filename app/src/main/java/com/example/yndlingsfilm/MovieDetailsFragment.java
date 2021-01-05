package com.example.yndlingsfilm;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.util.Constants;
import com.google.android.material.textview.MaterialTextView;

public class MovieDetailsFragment extends Fragment {
    private static final String TAG = "MovieDetailsFragment";
    ImageView imdbLink;
    TextView movieTitle;
    ImageView moviePic;
    TextView overview;
    TextView releaseDate;
    TextView runTime;
    RatingBar rating;
    Button writeReviewButton;
    Movie movie;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        imdbLink = view.findViewById(R.id.imdbLink);
        movieTitle = view.findViewById(R.id.movieTitle);
        moviePic = view.findViewById(R.id.moviePic);
        overview = view.findViewById(R.id.overview);
        releaseDate = view.findViewById(R.id.releaseDate);
        rating = view.findViewById(R.id.rating);
        writeReviewButton = view.findViewById(R.id.writeReviewButton);
        runTime = view.findViewById(R.id.runTime);



        Bundle bundle = getArguments();
        if(bundle != null){
            movie = bundle.getParcelable("movie");
            Log.d(TAG, "onCreateView: bundle igennem");
        } else{
            Log.d(TAG, "onCreateView: bundle fejl");
        }

        movieTitle.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        releaseDate.setText(movie.getRelease_date());
        rating.setRating(movie.getVote_average());
        //runTime.setText(movie.getRuneTime());
        Glide.with(this).load(Constants.BASE_URL_IMG + movie.getPoster_path())
                .into(moviePic);

/*
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
}