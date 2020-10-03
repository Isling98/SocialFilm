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
import android.widget.RatingBar;
import android.widget.TextView;

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
            singleNews.add(new SingleNews(R.drawable.profile_pic, R.drawable.movie_pic,
                    "Åkanden kommenterede:", "Jeg elsker Markus", "Harry Potter", 4));
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
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.popup_news_details);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                ImageView moviePic = dialog.findViewById(R.id.moviePic);
                TextView text = dialog.findViewById(R.id.text);
                RatingBar rating = dialog.findViewById(R.id.rating);
                TextView closeButton = dialog.findViewById(R.id.closeButton);

                moviePic.setImageResource(singleNews.get(position).getMoviePicResource());
                text.setText(singleNews.get(position).getText());
                rating.setRating(singleNews.get(position).getRating());

                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                moviePic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getFragmentManager().beginTransaction().replace(R.id.fragment_nagivation,new HomeFragment()).commit();
                    }
                });



                dialog.show();
            }
        });
        return view;
    }
}

