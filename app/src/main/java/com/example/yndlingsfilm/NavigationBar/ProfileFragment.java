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

import com.example.yndlingsfilm.ProfileAddFriendFragment;
import com.example.yndlingsfilm.requests.ServiceGenerator;
import com.example.yndlingsfilm.requests.UserApi;
import com.example.yndlingsfilm.requests.responses.RelationshipResponse;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ProfileFragment";

    ImageView addFriend;
    View bio;
    View topRated;
    View reviews;
    View latestComments;
    TextView friends;
    TextView followers;
    TextView profileName;
    TextView bioShort;
    private UserViewModel userViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        addFriend = view.findViewById(R.id.add_friend);
        bio = view.findViewById(R.id.profile_bio);
        topRated = view.findViewById(R.id.profile_top_rated);
        reviews = view.findViewById(R.id.profile_reviews);
        latestComments = view.findViewById(R.id.profile_latest_comments);
        friends = view.findViewById(R.id.profile_friends);
        followers = view.findViewById(R.id.profile_followers);
        profileName = view.findViewById(R.id.profileName);
        bioShort = view.findViewById(R.id.bioShort);

        profileName.setText(userViewModel.getLoggedInUser().getValue().getUsername());

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


        //profileName.setText(userViewModel.getUsers().getValue().get(0).getUsername());
        //friends.setText((Integer.toString(userViewModel.getLoggedInUser().getValue().getFriends())));
        //followers.setText((Integer.toString(userViewModel.getLoggedInUser().getValue().getFollowers())));

        addFriend.setOnClickListener(this);
        bio.setOnClickListener(this);
        topRated.setOnClickListener(this);
        reviews.setOnClickListener(this);
        latestComments.setOnClickListener(this);
        friends.setOnClickListener(this);
        followers.setOnClickListener(this);


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
                    selectedFragment = new FriendsListFragment();
                    break;
                case R.id.profile_followers:
                    selectedFragment = new FollowersFragment();
                    break;
            }
            getFragmentManager().beginTransaction().replace
                    (R.id.fragment_nagivation, selectedFragment).addToBackStack(null).commit();

        }
    }
}