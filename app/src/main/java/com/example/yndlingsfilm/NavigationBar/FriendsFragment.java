package com.example.yndlingsfilm.NavigationBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yndlingsfilm.Model.Friendlist;
import com.example.yndlingsfilm.NavigationBar.Adapters.FriendlistAdapter;
import com.example.yndlingsfilm.R;

import java.util.ArrayList;


public class FriendsFragment extends Fragment {

    ArrayList<Friendlist> exampleFriendlist;

    private RecyclerView friendlistRecycler;
    private RecyclerView.LayoutManager layoutManager;
    private FriendlistAdapter friendlistAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);


        exampleFriendlist = new ArrayList<>();
        exampleFriendlist.add(new Friendlist(R.drawable.profile_pic, "Asger", "0 reviews head"));
        exampleFriendlist.add(new Friendlist(R.drawable.profile_pic, "Asger", "1 reviews head"));
        exampleFriendlist.add(new Friendlist(R.drawable.profile_pic, "Asger", "5 reviews head"));

        friendlistRecycler = view.findViewById(R.id.friendListRecycler);
        friendlistRecycler.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        friendlistRecycler.setLayoutManager(layoutManager);

        friendlistAdapter = new FriendlistAdapter(exampleFriendlist);
        friendlistRecycler.setAdapter(friendlistAdapter);

        return view;

    }}
