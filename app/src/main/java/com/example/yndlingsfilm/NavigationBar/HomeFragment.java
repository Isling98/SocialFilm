package com.example.yndlingsfilm.NavigationBar;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yndlingsfilm.HomeFeedAdapter;
import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.Model.Review;
import com.example.yndlingsfilm.Model.User;
import com.example.yndlingsfilm.MovieDetailsFragment;
import com.example.yndlingsfilm.R;
import com.example.yndlingsfilm.Model.News;
import com.example.yndlingsfilm.requests.responses.ReviewResponse;
import com.example.yndlingsfilm.util.Constants;
import com.example.yndlingsfilm.viewModels.MovieListViewModel;
import com.example.yndlingsfilm.viewModels.MovieViewModel;
import com.example.yndlingsfilm.viewModels.UserViewModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static android.content.ContentValues.TAG;
import static androidx.constraintlayout.solver.SolverVariable.Type.CONSTANT;
import static com.example.yndlingsfilm.util.Constants.API_KEY;


public class HomeFragment extends Fragment {
    private RecyclerView homeFeed;
    private HomeFeedAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private UserViewModel userViewModel;
    private MovieListViewModel movieListViewModel;
    private List<User> tempReviewListHomePage = new ArrayList<>();
    private ArrayList<News> aNews = new ArrayList<>();
    private boolean beenCreated;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        if(!beenCreated) {
            tempReviewListHomePage = userViewModel.getHomePage(userViewModel.getLoggedInUser().getValue().getUserId());
            userViewModel.getUsers().setValue(tempReviewListHomePage);
            Log.d(TAG, "onCreateView: @@@2 efter"+  userViewModel.getUsers().getValue().size());

            //metoden til at få hentet user objekterne med de reviews som skal vises på homepage
            Log.d(TAG, "@@@@@ind homepagefragment" + beenCreated);

            Collections.shuffle(Arrays.asList(Constants.fuckOff));
            int count = 0;
            for (User user : tempReviewListHomePage) {

                for (Review review : user.getReviews()) {
                    Movie movie = movieListViewModel.searchMovieForSearch(review.getMovieId(), API_KEY);

                    String userName = user.getUsername();

                    int rating = review.getRating();
                    String reviewInText = review.getReviewText();
                    String movieTitle = movie.getTitle();


                    String url = Constants.BASE_URL_IMG + movie.getPoster_path();
                    String urlProfile = "https://cdn.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png";
                    Log.d(TAG, "@@@Homepagefragment_" + user.getProfileUrl());
                    if(user.getProfileUrl() != null) {
                        urlProfile = user.getProfileUrl();

                    }

                    aNews.add(new News(urlProfile, url,
                            userName, movieTitle, rating, reviewInText, movie));
                }

            }
            beenCreated = true;
        }

            homeFeed = (RecyclerView) view.findViewById(R.id.recycler_view);
            homeFeed.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(getContext());
            homeFeed.setLayoutManager(layoutManager);

            mAdapter = new HomeFeedAdapter(aNews);
            homeFeed.setAdapter(mAdapter);

            Log.d(TAG, "onCreateView:@@@@@@" + beenCreated);





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
                ImageView closeButton = dialog.findViewById(R.id.closeButton);
                TextView title = dialog.findViewById(R.id.movieTitle);

                title.setText(aNews.get(position).getMovieName());
                Glide.with(this).load(aNews.get(position).getMovieUrl()).into(moviePic);
                rating.setRating(aNews.get(position).getRating());
                text.setText(aNews.get(position).getReviewInText());

                closeButton.setOnClickListener(view1 -> dialog.dismiss());

                moviePic.setOnClickListener(view1 -> {

                    //sæt bundle med anews.get(position) eller faktisk den givne movie...
                    //open moviedetails fragment

                    Fragment fragment = new MovieDetailsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("movie", aNews.get(position).getMovie());
                    fragment.setArguments(bundle);
                    Log.d(TAG, "onMovieClick: " + bundle.getParcelable("movie").toString());
                    getFragmentManager().beginTransaction().replace
                            (R.id.fragment_nagivation, fragment).addToBackStack(null).commit();
                    dialog.dismiss();
                });
                dialog.show();
            });return view;
        }
    }

