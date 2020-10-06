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
    private RecyclerViewClickListener clickListener;

    public HorizontalRecyclerViewAdapter(Context context, ArrayList<ModelHorizontal>arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public HorizontalRecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal, parent, false);
        return new HorizontalRecyclerViewViewHolder(view);
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

    public class HorizontalRecyclerViewViewHolder extends RecyclerView.ViewHolder{

        ImageView filmPlakat;
        TextView titel;

        public HorizontalRecyclerViewViewHolder(View itemView){
            super(itemView);
            filmPlakat = itemView.findViewById(R.id.filmPlakat);
            titel = itemView.findViewById(R.id.titel);
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }

}
