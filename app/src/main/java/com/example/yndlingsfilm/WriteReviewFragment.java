package com.example.yndlingsfilm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class WriteReviewFragment extends Fragment {

    ImageView moviePic;
    TextView movieTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_write_review, container, false);



         moviePic = view.findViewById(R.id.moviePic);
         movieTitle = view.findViewById(R.id.movieTitle);

         movieTitle.setText(getArguments().getString("movieTitle"));
         //moviePic.setImageResource(getArguments().getParcelable("moviePic"));
         moviePic.setImageResource(R.drawable.movie_pic);


        return view;
    }
}