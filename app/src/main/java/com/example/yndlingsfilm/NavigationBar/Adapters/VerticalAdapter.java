package com.example.yndlingsfilm.NavigationBar.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.MovieDetailsFragment;
import com.example.yndlingsfilm.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/*
Denne klasse skal styre det vertikale recyclerview, dvs det skal indeholde alle de andre horizontale recyclerviews samt deres tilknyttet genre.
 */

public class VerticalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<List<Movie>> categoryList = new ArrayList<>();
    Context context;
    OnMovieListener onMovieListener;
    private List<Movie> popularMovies;
    private List<Movie> topRatedMovies;
    private List<Movie> upcomingMovies;
    private List<Movie> latestMovies;


    public VerticalAdapter(Context context, OnMovieListener onMovieListener) {
        this.context = context;
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical, parent, false);

        return new VerticalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        HorizontalAdapter horizontalAdapter = new HorizontalAdapter(context, categoryList.get(position), onMovieListener);
        ((VerticalViewHolder)holder).recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        ((VerticalViewHolder)holder).recyclerView.setHasFixedSize(true);
        ((VerticalViewHolder)holder).recyclerView.setAdapter(horizontalAdapter);

        // kontrol af category
        ((VerticalViewHolder)holder).category.setText("category");

    }

    @Override
    public int getItemCount() {
        if(categoryList != null){
            return categoryList.size();
        }
        return 0;
    }

    public void setCategoryList(
            List<Movie> movies, String category){
        switch (category){
            case "popular":
                this.popularMovies = movies;
                categoryList.add(popularMovies);
                Log.d(TAG, "setCategoryList:_______________ " + popularMovies.get(0));
                break;
            case "topRated":
                this.topRatedMovies = movies;
                categoryList.add(topRatedMovies);
                break;
            case "upcoming":
                this.upcomingMovies = movies;
                categoryList.add(upcomingMovies);
                break;
            case "latest":
                this.latestMovies = movies;
                categoryList.add(latestMovies);
        }
        notifyDataSetChanged();
    }

    public class VerticalViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        TextView category;

        public VerticalViewHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.genre);
            recyclerView = itemView.findViewById(R.id.recyclerview2);
        }
    }
}
