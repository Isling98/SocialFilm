package com.example.yndlingsfilm.NavigationBar.Adapters;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yndlingsfilm.R;

public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    OnMovieListener onMovieListener;
    ImageView moviePoster;
    private static final String TAG = "SearchViewHolder";

    public SearchViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);
        this.onMovieListener = onMovieListener;
        moviePoster = itemView.findViewById(R.id.filmPlakat);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onMovieListener.onMovieClick(getBindingAdapterPosition());
    }
}
