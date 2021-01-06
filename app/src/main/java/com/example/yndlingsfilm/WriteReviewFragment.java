package com.example.yndlingsfilm;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.util.Constants;

public class WriteReviewFragment extends Fragment {

    ImageView moviePic;
    TextView movieTitle;
    TextView movieDescription;
    RatingBar ratingBar;
    EditText textBox;
    Movie movie;

    private static final String TAG = "WriteReviewFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_write_review, container, false);


        moviePic = view.findViewById(R.id.moviePic);
        movieTitle = view.findViewById(R.id.movieTitle);
        ratingBar = view.findViewById(R.id.rating);
        textBox = view.findViewById(R.id.textBox);
        movieDescription = view.findViewById(R.id.movieDescription);

        Bundle bundle = getArguments();
        if(bundle != null){
            movie = bundle.getParcelable("movie");
            Log.d(TAG, "onCreateView: bundle igennem");
        } else{
            Log.d(TAG, "onCreateView: bundle fejl");
        }

        movieDescription.setText(movie.getOverview());
        movieTitle.setText(movie.getTitle());
        Glide.with(this).load(Constants.BASE_URL_IMG + movie.getPoster_path())
                .into(moviePic);


         ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
             @Override
             public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                 Toast.makeText(getActivity(), String.valueOf(rating), Toast.LENGTH_LONG).show();
             }
         });
         
        return view;
    }
}