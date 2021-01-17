package com.example.yndlingsfilm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yndlingsfilm.Model.Friendlist;
import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.Model.Review;
import com.example.yndlingsfilm.Model.User;
import com.example.yndlingsfilm.NavigationBar.Adapters.FriendlistAdapter;
import com.example.yndlingsfilm.NavigationBar.Adapters.TopRatedAdapter;
import com.example.yndlingsfilm.util.Constants;
import com.example.yndlingsfilm.viewModels.MovieListViewModel;
import com.example.yndlingsfilm.viewModels.UserViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.yndlingsfilm.util.Constants.API_KEY;

public class ProfileTopRatedFragment extends Fragment {
    ArrayList<Friendlist> exampleFriendlist;
    ArrayList<Friendlist> exampleFriendlistSorted;

    private RecyclerView friendlistRecycler;
    private RecyclerView.LayoutManager layoutManager;
    private TopRatedAdapter topRatedAdapter;
    TextView hej1;


    private UserViewModel userViewModel;
    private MovieListViewModel movieListViewModel;

    private List<User> friendList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topreviews, container, false);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);



        exampleFriendlist = new ArrayList<>();

        friendList = userViewModel.getUsers().getValue();
        System.out.println(friendList);

        for (Review review: userViewModel.getLoggedInUser().getValue().getReviews()) {
            Movie movie = movieListViewModel.searchMovieForSearch(review.getMovieId(), API_KEY);
            String url = Constants.BASE_URL_IMG + movie.getPoster_path();


            exampleFriendlist.add(new Friendlist(url, movie.getTitle(), review.getRating()));

        }
        Collections.sort(exampleFriendlist, (p1, p2) -> p1.getFriendListReviews() - (p2.getFriendListReviews() ));
        Collections.reverse(exampleFriendlist);


        friendlistRecycler = view.findViewById(R.id.friendListRecycler);
        friendlistRecycler.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        friendlistRecycler.setLayoutManager(layoutManager);

        topRatedAdapter = new TopRatedAdapter(exampleFriendlist);
        friendlistRecycler.setAdapter(topRatedAdapter);




        return view;
    }

}