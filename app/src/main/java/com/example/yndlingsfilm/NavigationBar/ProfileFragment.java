package com.example.yndlingsfilm.NavigationBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.yndlingsfilm.R;


public class ProfileFragment extends Fragment implements View.OnClickListener {
    ImageView addFriend;
    View bio;
    View topRated;
    View reviews;
    View latestComments;
    View friends;
    View followers;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        addFriend = view.findViewById(R.id.add_friend);
        bio = view.findViewById(R.id.profile_bio);
        topRated = view.findViewById(R.id.profile_top_rated);
        reviews = view.findViewById(R.id.profile_reviews);
        latestComments = view.findViewById(R.id.profile_latest_comments);
        friends = view.findViewById(R.id.profile_friends);
        followers = view.findViewById(R.id.profile_followers);

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

        switch (view.getId()) {
            case R.id.add_friend:
                // send friend request and update
                addFriend.setImageResource(R.drawable.ic_friend_added);
                break;
            case R.id.profile_bio:

                break;
            case R.id.profile_top_rated:

                break;
            case R.id.profile_reviews:

                break;
            case R.id.profile_latest_comments:

                break;
            case R.id.profile_friends:

                break;
            case R.id.profile_followers:

                break;
        }
    }
}