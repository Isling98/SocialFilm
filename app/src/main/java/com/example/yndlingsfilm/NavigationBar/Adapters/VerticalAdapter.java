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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.MovieDetailsFragment;
import com.example.yndlingsfilm.R;
import com.example.yndlingsfilm.util.Constants;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/*
Denne klasse skal styre det vertikale recyclerview, dvs det skal indeholde alle de andre horizontale recyclerviews samt deres tilknyttet genre.
 */

public class VerticalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int BROWSE_MOVIES_TYPE = 1;
    private static final int SEARCH_MOVIES_TYPE = 2;
    private static final int LOADING_MOVIES_TYPE = 3;


    List<List<Movie>> categoryList = new ArrayList<>();
    Context context;
    OnMovieListener onMovieListener;
    private List<Movie> popularMovies;
    private List<Movie> topRatedMovies;
    private List<Movie> upcomingMovies;
    private List<Movie> nowPlayingMovies;
    private List<Movie> searchMovies;

    public VerticalAdapter(Context context, OnMovieListener onMovieListener) {
        this.context = context;
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;

        switch(viewType){
            case SEARCH_MOVIES_TYPE:
                view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal, parent, false);
                return new SearchViewHolder(view, onMovieListener);
            case BROWSE_MOVIES_TYPE:
                view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical, parent, false);
                return new VerticalViewHolder(view);
            case LOADING_MOVIES_TYPE:
                view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading_movies, parent, false);
                return new LoadingViewHolder(view);
            default:
                view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical, parent, false);
                return new VerticalViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);

        if(viewType == SEARCH_MOVIES_TYPE){
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.poster_harry_potter_1);

            Glide.with(holder.itemView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(Constants.BASE_URL_IMG + searchMovies.get(position).getPoster_path())
                    .into(((SearchViewHolder)holder).moviePoster);

        } else if(viewType == BROWSE_MOVIES_TYPE) {
            HorizontalAdapter horizontalAdapter = new HorizontalAdapter(context, categoryList.get(position), onMovieListener);
            ((VerticalViewHolder)holder).recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            ((VerticalViewHolder)holder).recyclerView.setHasFixedSize(true);
            ((VerticalViewHolder)holder).recyclerView.setAdapter(horizontalAdapter);

            // kontrol af category
            String category;
            switch(position){
                case 0:
                    category = "Popular";
                    break;
                case 1:
                    category = "Top Rated";
                    break;
                case 2:
                    category = "Upcoming";
                    break;
                case 3:
                    category = "In theatres";
                    break;
                default:
                    category = "Category";
            }
            ((VerticalViewHolder)holder).category.setText(category);
        }
    }

    @Override
    public int getItemCount() {
        if(categoryList != null){
            return categoryList.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        // husk at slette searchmovies når search forlades igen
        if(searchMovies != null && searchMovies.size() > 1){
            return SEARCH_MOVIES_TYPE;
        } else {
            return BROWSE_MOVIES_TYPE;
        }
        // husk loading type
    }

    public void setCategoryList(List<Movie> movies, String category){
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
            case "nowPlaying":
                this.nowPlayingMovies = movies;
                categoryList.add(nowPlayingMovies);
                break;
            case "searchMovies":
                this.searchMovies = movies;
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
