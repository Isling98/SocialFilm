package com.example.yndlingsfilm.NavigationBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.yndlingsfilm.viewModels.UserViewModel;
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
    TextView friends;
    TextView followers;
    TextView profileName;
    UserViewModel viewModel; // her

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        viewModel.init();
        viewModel.getUsers().observe(getViewLifecycleOwner(), users -> {
            //opdater her
            // vi skal have fat i hvilken bruger der er logget ind og kunne finde brugere på id ofc
           // profileName.setText(users.get(0).getUsername());
           // friends.setText(users.get(0).getFriends());
            //followers.setText(users.get(0).getFollowers());
            updateView();
        });

        addFriend = view.findViewById(R.id.add_friend);
        bio = view.findViewById(R.id.profile_bio);
        topRated = view.findViewById(R.id.profile_top_rated);
        reviews = view.findViewById(R.id.profile_reviews);
        latestComments = view.findViewById(R.id.profile_latest_comments);
        friends = view.findViewById(R.id.profile_friends);
        followers = view.findViewById(R.id.profile_followers);
        profileName = view.findViewById(R.id.profileName);

        profileName.setText((viewModel.getUsers().getValue().get(0)).getUsername());
        friends.setText((Integer.toString(viewModel.getUsers().getValue().get(0).getFriends())));
        followers.setText((Integer.toString(viewModel.getUsers().getValue().get(0).getFollowers())));


        addFriend.setOnClickListener(this);
        bio.setOnClickListener(this);
        topRated.setOnClickListener(this);
        reviews.setOnClickListener(this);
        latestComments.setOnClickListener(this);
        friends.setOnClickListener(this);
        followers.setOnClickListener(this);


        return view;
    }

    public void updateView(){
        profileName.setText((viewModel.getUsers().getValue().get(0)).getUsername());
        friends.setText((Integer.toString(viewModel.getUsers().getValue().get(0).getFriends())));
        followers.setText((Integer.toString(viewModel.getUsers().getValue().get(0).getFollowers())));
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.add_friend) {
            // send friend request and update
            addFriend.setImageResource(R.drawable.ic_friend_added);
            // få fat i rigtig bruger. dette blot test
            viewModel.getUsers().getValue().get(0).addFriend();
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