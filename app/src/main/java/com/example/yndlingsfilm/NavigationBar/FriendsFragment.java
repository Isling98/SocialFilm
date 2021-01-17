package com.example.yndlingsfilm.NavigationBar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yndlingsfilm.Model.Friendlist;
import com.example.yndlingsfilm.Model.User;
import com.example.yndlingsfilm.NavigationBar.Adapters.FriendlistAdapter;
import com.example.yndlingsfilm.R;
import com.example.yndlingsfilm.viewModels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class FriendsFragment extends Fragment {

    ArrayList<Friendlist> exampleFriendlist;

    private RecyclerView friendlistRecycler;
    private RecyclerView.LayoutManager layoutManager;
    private FriendlistAdapter friendlistAdapter;

    private UserViewModel userViewModel;

    private List<User> friendList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);


        exampleFriendlist = new ArrayList<>();

        friendList = userViewModel.getUsers().getValue();
        System.out.println(friendList);

        for (User user: userViewModel.getUsers().getValue()) {
            Log.d(TAG, "onCreateView: @@@@@@@@" + user.getUsername());

            String urlProfile = "https://cdn.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png";
            if(user.getProfileUrl() != null) {
                urlProfile = user.getProfileUrl();
            }

            exampleFriendlist.add(new Friendlist(urlProfile, user.getUsername(), user.getReviews().size()));

        }

        friendlistRecycler = view.findViewById(R.id.friendListRecycler);
        friendlistRecycler.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        friendlistRecycler.setLayoutManager(layoutManager);

        friendlistAdapter = new FriendlistAdapter(exampleFriendlist);
        friendlistRecycler.setAdapter(friendlistAdapter);

        return view;

    }

}

