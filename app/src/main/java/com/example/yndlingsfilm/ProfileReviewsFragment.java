package com.example.yndlingsfilm;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.yndlingsfilm.Model.News;

import java.util.ArrayList;

public class ProfileReviewsFragment extends Fragment {
    private RecyclerView homeFeed;
    private HomeFeedAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_reviews, container, false);

        // go through each review the user has
        final ArrayList<News> aNews = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            aNews.add(new News(R.drawable.profile_pic, R.drawable.movie_pic,
                    "Asger Åkanden:", "2hrs", "Harry Potter", 4));
        }

        homeFeed = (RecyclerView) view.findViewById(R.id.recycler_view);
        homeFeed.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        homeFeed.setLayoutManager(layoutManager);

        mAdapter = new HomeFeedAdapter(aNews);
        homeFeed.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(position -> {
            final Dialog dialog = new Dialog(getContext(), R.style.AnimationDialog);
            dialog.setContentView(R.layout.popup_news_details);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            // sætter popup til at fylde parent
            //WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            //lp.copyFrom(dialog.getWindow().getAttributes());
            //lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            //lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            //dialog.getWindow().setAttributes(lp);

            ImageView moviePic = dialog.findViewById(R.id.moviePic);
            TextView text = dialog.findViewById(R.id.text);
            RatingBar rating = dialog.findViewById(R.id.rating);
            ImageView closeButton = dialog.findViewById(R.id.closeButton);

            moviePic.setImageResource(aNews.get(position).getMoviePicResource());
            text.setText(aNews.get(position).getTime());
            rating.setRating(aNews.get(position).getRating());

            closeButton.setOnClickListener(view1 -> dialog.dismiss());

            moviePic.setOnClickListener(view1 -> {
                //open moviedetails fragment
                getFragmentManager().beginTransaction().replace
                        (R.id.fragment_nagivation, new MovieDetailsFragment())
                        .addToBackStack(null).commit();
                dialog.dismiss();
            });
            dialog.show();
        });
        return view;
    }
}