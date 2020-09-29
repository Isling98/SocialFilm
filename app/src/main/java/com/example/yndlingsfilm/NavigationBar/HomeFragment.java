package com.example.yndlingsfilm.NavigationBar;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
    private HomeFeedAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        final ArrayList<SingleNews> singleNews = new ArrayList<>();
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

        mAdapter.setOnItemClickListener(new HomeFeedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //singleNews.get(position).openDetails();
               // openDialog(getLayoutInflater().inflate(R.layout.popup_news_details, null));
                Dialog dialog = new Dialog(getContext());

                // du laver et nyt view her tåger
                View view = getLayoutInflater().inflate(R.layout.popup_news_details, null);
                ImageView moviePic = (ImageView) view.findViewById(R.id.moviePic);
                moviePic.setImageResource(singleNews.get(position).getMoviePicResource());


                dialog.setContentView(R.layout.popup_news_details);
                dialog.show();

            }
        });

        return view;
    }
}

