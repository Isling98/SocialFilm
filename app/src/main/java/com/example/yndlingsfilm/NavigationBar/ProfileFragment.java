package com.example.yndlingsfilm.NavigationBar;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.yndlingsfilm.Model.Review;
import com.example.yndlingsfilm.Model.User;
import com.example.yndlingsfilm.PhotoFragment;
import com.example.yndlingsfilm.ProfileAddFriendFragment;
import com.example.yndlingsfilm.requests.ServiceGenerator;
import com.example.yndlingsfilm.requests.UserApi;
import com.example.yndlingsfilm.requests.responses.RelationshipResponse;
import com.example.yndlingsfilm.util.Constants;
import com.example.yndlingsfilm.viewModels.MovieListViewModel;
import com.example.yndlingsfilm.viewModels.UserViewModel;
import com.example.yndlingsfilm.FollowersFragment;
import com.example.yndlingsfilm.FriendsListFragment;
import com.example.yndlingsfilm.ProfileBioFragment;
import com.example.yndlingsfilm.ProfileLatestCommentsFragment;
import com.example.yndlingsfilm.ProfileReviewsFragment;
import com.example.yndlingsfilm.ProfileTopRatedFragment;
import com.example.yndlingsfilm.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ProfileFragment";

    ImageView addFriend;
    ImageView profilePic;
    View bio;
    View topRated;
    View reviews;
    View camera;
    TextView friends;
    TextView profileName;
    TextView bioShort;
    TextView numberreview,numberfriends,topRatedId,lastReview;
    private UserViewModel userViewModel;
    private MovieListViewModel movieListViewModel;
    private List<User> friendList = new ArrayList<>();
    ArrayList<Integer> highest = new ArrayList<Integer>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment_try, container, false);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        addFriend = view.findViewById(R.id.add_friend);
        bio = view.findViewById(R.id.profile_bio);
        topRated = view.findViewById(R.id.profile_top_rated);
        reviews = view.findViewById(R.id.profile_reviews);
        friends = view.findViewById(R.id.profile_friends);
        profileName = view.findViewById(R.id.profileName);
        bioShort = view.findViewById(R.id.bioShort);
        camera = view.findViewById(R.id.camera);
        numberreview = view.findViewById(R.id.textView7);
        numberfriends = view.findViewById(R.id.textView6);
        topRatedId = view.findViewById(R.id.topRatedId);
        lastReview = view.findViewById(R.id.lastReview);
        profilePic = view.findViewById(R.id.profilePic);


        profileName.setText(userViewModel.getLoggedInUser().getValue().getUsername());
        Glide.with(this).load(userViewModel.getLoggedInUser().getValue().getProfileUrl()).into(profilePic);

        friendList = userViewModel.getUsers().getValue();

        String bioShortDescprition;
        try {
            if(userViewModel.getLoggedInUser().getValue().getBio() != null){
                bioShortDescprition = userViewModel.getLoggedInUser().getValue().getBio().substring(0, 40) + "...";
            } else {
                bioShortDescprition = "User has no bio...";
            }
        } catch (IndexOutOfBoundsException e){
            bioShortDescprition = userViewModel.getLoggedInUser().getValue().getBio() + "...";
        }
            bioShort.setText(bioShortDescprition);
        numberreview.setText(Integer.toString(userViewModel.getLoggedInUser().getValue().getReviews().size()));
        numberfriends.setText(Integer.toString(friendList.size()));

        int currentHighestRating = 0;
        int currentHighestReviewId = 0;
        Review reviewBestRating = null;
        Review reviewLatest = null;
        for(Review review : userViewModel.getLoggedInUser().getValue().getReviews()){
            if(review.getRating() > currentHighestRating){

                currentHighestRating = review.getRating();

                reviewBestRating = review;
                Log.d(TAG, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + reviewBestRating.getRating() + " currentHighestrating: " + currentHighestRating);
            }

            if(review.getReviewId() > currentHighestReviewId){

                currentHighestReviewId = review.getReviewId();
                reviewLatest = review;
            }

        }
        topRatedId.setText(movieListViewModel.searchMovieForSearch(reviewBestRating.getMovieId(), Constants.API_KEY).getTitle());
        lastReview.setText(movieListViewModel.searchMovieForSearch(reviewLatest.getMovieId(), Constants.API_KEY).getTitle());



        //profileName.setText(userViewModel.getUsers().getValue().get(0).getUsername());
        //friends.setText((Integer.toString(userViewModel.getLoggedInUser().getValue().getFriends())));
        //followers.setText((Integer.toString(userViewModel.getLoggedInUser().getValue().getFollowers())));

        addFriend.setOnClickListener(this);
        bio.setOnClickListener(this);
        topRated.setOnClickListener(this);
        reviews.setOnClickListener(this);
        friends.setOnClickListener(this);
        camera.setOnClickListener(this);



        return view;
    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.add_friend) {
            // send friend request and update
            //addFriend.setImageResource(R.drawable.ic_friend_added);
            // få fat i rigtig bruger. dette blot test
            //viewModel.getUsers().getValue().get(0).addFriend();

            Fragment selectedFragment = new ProfileAddFriendFragment();
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.fragment_nagivation, selectedFragment)
                    .addToBackStack(null)
                    .commit();

        } else {
            Fragment selectedFragment = null;
            switch (view.getId()) {
                case R.id.profile_bio:
                    selectedFragment = new ProfileBioFragment();
                    break;
                case R.id.profile_top_rated:
                    selectedFragment = new ProfileTopRatedFragment();
                    break;
                case R.id.profile_reviews:
                    selectedFragment = new ProfileReviewsFragment();
                    break;
                case R.id.profile_latest_comments:
                    selectedFragment = new ProfileLatestCommentsFragment();
                    break;
                case R.id.profile_friends:
                    selectedFragment = new FriendsFragment();
                    break;
                case R.id.profile_followers:
                    selectedFragment = new FollowersFragment();
                    break;
                case R.id.camera:
                    selectedFragment = new PhotoFragment();
                    break;
            }
            getFragmentManager().beginTransaction().replace
                    (R.id.fragment_nagivation, selectedFragment).addToBackStack(null).commit();

        }
    }
}