package com.example.yndlingsfilm.NavigationBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import androidx.savedstate.SavedStateRegistry;

import com.example.yndlingsfilm.Data.User;
import com.example.yndlingsfilm.Data.UserViewModel;
import com.example.yndlingsfilm.FollowersFragment;
import com.example.yndlingsfilm.FriendsListFragment;
import com.example.yndlingsfilm.ProfileBioFragment;
import com.example.yndlingsfilm.ProfileLatestCommentsFragment;
import com.example.yndlingsfilm.ProfileReviewsFragment;
import com.example.yndlingsfilm.ProfileTopRatedFragment;
import com.example.yndlingsfilm.R;


public class ProfileFragment extends Fragment implements View.OnClickListener {
    ImageView addFriend;
    View bio;
    View topRated;
    View reviews;
    View latestComments;
    View friends;
    View followers;
    TextView profileName;
    UserViewModel viewModel; // her

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        viewModel = new ViewModelProvider(this).get(UserViewModel.class); // her

        addFriend = view.findViewById(R.id.add_friend);
        bio = view.findViewById(R.id.profile_bio);
        topRated = view.findViewById(R.id.profile_top_rated);
        reviews = view.findViewById(R.id.profile_reviews);
        latestComments = view.findViewById(R.id.profile_latest_comments);
        friends = view.findViewById(R.id.profile_friends);
        followers = view.findViewById(R.id.profile_followers);
//
//        profileName = view.findViewById(R.id.profileName);
//        profileName.setText(model.getUser().getValue().getUsername());





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
            addFriend.setImageResource(R.drawable.ic_friend_added);
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