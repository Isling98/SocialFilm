package com.example.yndlingsfilm;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.Model.Review;
import com.example.yndlingsfilm.util.Constants;
import com.example.yndlingsfilm.viewModels.UserViewModel;

public class WriteReviewFragment extends Fragment {

    ImageView moviePic;
    TextView movieTitle;
    TextView movieDescription;
    RatingBar ratingBar;
    EditText textBox;
    Button submitReview;
    Movie movie;
    UserViewModel userViewModel;
    int mRating;

    private static final String TAG = "WriteReviewFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_write_review, container, false);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        moviePic = view.findViewById(R.id.moviePic);
        movieTitle = view.findViewById(R.id.movieTitle);
        ratingBar = view.findViewById(R.id.rating);
        textBox = view.findViewById(R.id.textBox);
        movieDescription = view.findViewById(R.id.movieDescription);
        submitReview = view.findViewById(R.id.submitReview);

        Bundle bundle = getArguments();
        if(bundle != null){
            movie = bundle.getParcelable("movie");
        } else{
        }

        movieDescription.setText(movie.getOverview());
        movieTitle.setText(movie.getTitle());
        Glide.with(this).load(Constants.BASE_URL_IMG + movie.getPoster_path())
                .into(moviePic);


         ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
             @Override
             public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                 Toast.makeText(getActivity(), String.valueOf(rating), Toast.LENGTH_LONG).show();
                 mRating = (int)rating;
             }
         });

         submitReview.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 // saves review
                 if(mRating >= 1){
                     if(String.valueOf(textBox.getText()).equals("")){
                         textBox.getText().append("No comments...");
                     }
                     Log.d(TAG, "onClick: " + mRating);

                     userViewModel.saveReview(movie.getId(), mRating, String.valueOf(textBox.getText()));

                     Review review = new Review(10000, String.valueOf(textBox.getText()), movie.getId(),userViewModel.getLoggedInUser().getValue().getUserId(),mRating);

                     userViewModel.getLoggedInUser().getValue().getReviews().add(review);

                     Toast.makeText(getActivity(), "Review submitted", Toast.LENGTH_LONG).show();
                     getActivity().getFragmentManager().popBackStack();
                 } else {
                     Toast.makeText(getActivity(), "Please enter rating and review", Toast.LENGTH_LONG).show();
                 }
             }
         });
        return view;
    }
}