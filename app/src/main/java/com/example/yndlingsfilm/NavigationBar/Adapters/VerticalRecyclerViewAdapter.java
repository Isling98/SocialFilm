package com.example.yndlingsfilm.NavigationBar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yndlingsfilm.R;

import java.util.ArrayList;

/*
Denne klasse skal styre det vertikale recyclerview, dvs det skal indeholde alle de andre horizontale recyclerviews samt deres tilknyttet genre.
 */

public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.VerticalRecyclerViewViewHolder> {

    Context context;
    ArrayList<ModelVertical> arrayList;

    //Constructor for vertical recyclerview adapteren.
    public VerticalRecyclerViewAdapter(Context context, ArrayList<ModelVertical> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    /*
    Nedenstående sætter viewholderen op til have det view, som er lavet i XML filen for det vertikale recyclerview.
     */
    @NonNull
    @Override
    public VerticalRecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical, parent, false);
        return new VerticalRecyclerViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalRecyclerViewViewHolder holder, int position) {
        ModelVertical modelVertical = arrayList.get(position);
        String genre = modelVertical.getGenre();
        ArrayList<ModelHorizontal> singleItem = modelVertical.getArrayList();

        holder.genre.setText(genre);
        HorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter = new HorizontalRecyclerViewAdapter(context, singleItem);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        holder.recyclerView.setAdapter(horizontalRecyclerViewAdapter);

    }

    /*
    Nedenstående henter antallet af items i recyclerviewet.
     */
    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    /*
    Nedenstående viser hvad det vertikale recyclerview indeholder,
    herunder er dette det horizontale recyclerview samt titel, som her kommer til at være genre.
     */
    public class VerticalRecyclerViewViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        TextView genre;

        public VerticalRecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerview2);
            genre = itemView.findViewById(R.id.genre);
        }
    }

}
