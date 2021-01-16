package com.example.yndlingsfilm;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yndlingsfilm.Model.News;
import com.example.yndlingsfilm.Model.Review;
import com.example.yndlingsfilm.viewModels.MovieListViewModel;
import com.example.yndlingsfilm.viewModels.UserViewModel;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.example.yndlingsfilm.util.Constants.API_KEY;

public class ProfileReviewsFragment extends Fragment {
    private RecyclerView homeFeed;
    private HomeFeedAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private UserViewModel userViewModel;
    private MovieListViewModel movieListViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_reviews, container, false);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        // go through each review the user has

        // time skal måske slettes. er den svær at holde styr på?
        // vi skal have lavet både movieID og userID om til navme i stedet
        final ArrayList<News> aNews = new ArrayList<>();
        Log.d(TAG, "onCreateView: " + userViewModel.getLoggedInUser().getValue().getReviews().get(0).getReviewText());
        for (Review review: userViewModel.getLoggedInUser().getValue().getReviews()) {
            Log.d(TAG, "onCreateView: " + review.getReviewText());
            String userName = userViewModel.getLoggedInUser().getValue().getUsername();
            String movieName = String.valueOf(review.getMovieId());
            int rating = review.getRating();
            String reviewInText = review.getReviewText();

            aNews.add(new News("https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",movieListViewModel.searchMovieForSearch(review.getMovieId(),API_KEY).getPoster_path() ,
                    userName, movieName, rating, reviewInText));
        }

        homeFeed = (RecyclerView) view.findViewById(R.id.recycler_view);
        homeFeed.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        homeFeed.setLayoutManager(layoutManager);

        mAdapter = new HomeFeedAdapter(aNews);
        homeFeed.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(position -> {
            final Dialog dialog = new Dialog(getContext(), R.style.AnimationDialog);
            dialog.setContentView(R.layout.popup_news_details);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            // sætter popup til at fylde parent
            //WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            //lp.copyFrom(dialog.getWindow().getAttributes());
            //lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            //lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            //dialog.getWindow().setAttributes(lp);

            ImageView moviePic = dialog.findViewById(R.id.moviePic);
            TextView text = dialog.findViewById(R.id.text);
            RatingBar rating = dialog.findViewById(R.id.rating);
            ImageView closeButton = dialog.findViewById(R.id.closeButton);


            Glide.with(this).load(aNews.get(position).getMovieUrl()).into(moviePic);
            rating.setRating(aNews.get(position).getRating());

            closeButton.setOnClickListener(view1 -> dialog.dismiss());

            moviePic.setOnClickListener(view1 -> {
                //open moviedetails fragment
                getFragmentManager().beginTransaction().replace
                        (R.id.fragment_nagivation, new MovieDetailsFragment())
                        .addToBackStack(null).commit();
                dialog.dismiss();
            });
            dialog.show();
        });
        return view;
    }
}