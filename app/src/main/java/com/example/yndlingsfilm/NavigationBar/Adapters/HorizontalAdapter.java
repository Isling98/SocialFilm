package com.example.yndlingsfilm.NavigationBar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.R;
import com.example.yndlingsfilm.util.Constants;

import java.util.List;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder> {

    Context context;
    private List<Movie> movies;
    private OnMovieListener onMovieListener;

    public HorizontalAdapter(Context context, List<Movie> movies, OnMovieListener onMovieListener) {
        this.context = context;
        this.movies = movies;
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal, parent, false);
        return new HorizontalViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {


        holder.title.setText(movies.get(position).getTitle());

        Glide.with(holder.itemView.getContext())
                .load(Constants.BASE_URL_IMG + movies.get(position).getPoster_path())
                .into((holder).moviePoster);
    }

    @Override
    public int getItemCount() {
        if(movies != null){
            return movies.size();
        }
        return 0;
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView moviePoster;
        OnMovieListener onMovieListener;

        public HorizontalViewHolder(View itemView, OnMovieListener onMovieListener){
            super(itemView);
            moviePoster = itemView.findViewById(R.id.moviePoster);
            title = itemView.findViewById(R.id.titel);
            this.onMovieListener = onMovieListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onMovieListener.onMovieClick(getBindingAdapterPosition());
        }
    }
}