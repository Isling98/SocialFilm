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
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.Model.News;
import com.example.yndlingsfilm.Model.Review;
import com.example.yndlingsfilm.util.Constants;
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

        final ArrayList<News> aNews = new ArrayList<>();

        for (Review review: userViewModel.getLoggedInUser().getValue().getReviews()) {
            Movie movie = movieListViewModel.searchMovieForSearch(review.getMovieId(), API_KEY);

            int rating = review.getRating();
            String reviewInText = review.getReviewText();
            String movieTitle = movie.getTitle();

            String url = Constants.BASE_URL_IMG + movie.getPoster_path();
            String urlProfile = "https://cdn.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png";

            if(userViewModel.getLoggedInUser().getValue().getProfileUrl() != null) {
                urlProfile = userViewModel.getLoggedInUser().getValue().getProfileUrl();

            }
            aNews.add(new News(urlProfile, url,
                    userViewModel.getLoggedInUser().getValue().getUsername(), movieTitle, rating, reviewInText, movie));
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


            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setAttributes(lp);

            ImageView moviePic = dialog.findViewById(R.id.moviePic);
            TextView text = dialog.findViewById(R.id.text);
            RatingBar rating = dialog.findViewById(R.id.rating);
            TextView movieTitle = dialog.findViewById(R.id.movieTitle);
            ImageView closeButton = dialog.findViewById(R.id.closeButton);



            Glide.with(this).load(aNews.get(position).getMovieUrl()).into(moviePic);
            rating.setRating(aNews.get(position).getRating());
            movieTitle.setText(aNews.get(position).getMovieName());
            text.setText(aNews.get(position).getReviewInText());

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