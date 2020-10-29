package com.example.yndlingsfilm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class WriteReviewFragment extends Fragment {

    ImageView moviePic;
    TextView movieTitle;
    RatingBar ratingBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_write_review, container, false);



         moviePic = view.findViewById(R.id.moviePic);
         movieTitle = view.findViewById(R.id.movieTitle);
         ratingBar = view.findViewById(R.id.rating);

         movieTitle.setText(getArguments().getString("movieTitle"));
         //moviePic.setImageResource(getArguments().getParcelable("moviePic"));
         moviePic.setImageResource(R.drawable.movie_pic);


         ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
             @Override
             public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                 Toast.makeText(getActivity(), String.valueOf(rating), Toast.LENGTH_LONG).show();
             }
         });

         /*
         Der skal laves noget kode til at knappen 'submit review' gemmer dataen fra ratingbar og EditText til databasen.
         Måske vi skal lave databasen/ideen om sådan at vi ikke har review og rating adskilt, men de foretages på samme side?
          */


        return view;
    }
}