package com.example.yndlingsfilm.NavigationBar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yndlingsfilm.R;

import java.util.ArrayList;

public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.VerticalRecyclerViewViewHolder> {

    Context context;
    ArrayList<ModelVertical> arrayList;

    //Constructor for vertical recyclerview adapteren.
    public VerticalRecyclerViewAdapter(Context context, ArrayList<ModelVertical> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    /*
    Nedenstående viewholder beskriver et item view og dets plads i recyclerviewet.
     */
    @NonNull
    @Override
    public VerticalRecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical, parent, false);
        return new VerticalRecyclerViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalRecyclerViewViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class VerticalRecyclerViewViewHolder extends RecyclerView.ViewHolder{

        public VerticalRecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
