package com.example.yndlingsfilm.NavigationBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yndlingsfilm.HomeFeedAdapter;
import com.example.yndlingsfilm.R;
import com.example.yndlingsfilm.SingleNews;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private RecyclerView homeFeed;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<SingleNews> singleNews = new ArrayList<>();
        for(int i=0; i<10; i++) {
            singleNews.add(new SingleNews(R.drawable.ic_android, R.drawable.ic_android,
                    "Åkanden kommenterede:", "Jeg elsker Markus", "Harry Potter", "6"));
        }




        homeFeed = (RecyclerView) view.findViewById(R.id.recycler_view);
        homeFeed.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        homeFeed.setLayoutManager(layoutManager);

        mAdapter = new HomeFeedAdapter(singleNews);
        homeFeed.setAdapter(mAdapter);



        return view;
    }
}

