package com.example.yndlingsfilm.NavigationBar.Adapters;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yndlingsfilm.R;

import java.util.ArrayList;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.HorizontalRecyclerViewViewHolder> {


    Context context;
    ArrayList<ModelHorizontal> arrayList;
    private OnNoteListener mOnNoteListener;

    public HorizontalRecyclerViewAdapter(Context context, ArrayList<ModelHorizontal>arrayList, OnNoteListener onNoteListener){
        this.context = context;
        this.arrayList = arrayList;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public HorizontalRecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal, parent, false);
        return new HorizontalRecyclerViewViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalRecyclerViewViewHolder holder, int position) {
        ModelHorizontal modelHorizontal = arrayList.get(position);
        //String titel = modelHorizontal.getName();

        holder.titel.setText(arrayList.get(position).getName());
        holder.filmPlakat.setImageResource(arrayList.get(position).getImageUrl());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class HorizontalRecyclerViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView filmPlakat;
        TextView titel;
        OnNoteListener onNoteListener;

        public HorizontalRecyclerViewViewHolder(View itemView, OnNoteListener onNoteListener){
            super(itemView);
            filmPlakat = itemView.findViewById(R.id.filmPlakat);
            titel = itemView.findViewById(R.id.titel);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getBindingAdapterPosition());
        }
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }

}