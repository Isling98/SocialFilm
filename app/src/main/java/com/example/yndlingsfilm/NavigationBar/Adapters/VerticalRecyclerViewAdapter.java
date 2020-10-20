package com.example.yndlingsfilm.NavigationBar.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yndlingsfilm.MovieDetailsFragment;
import com.example.yndlingsfilm.R;

import java.util.ArrayList;

/*
Denne klasse skal styre det vertikale recyclerview, dvs det skal indeholde alle de andre horizontale recyclerviews samt deres tilknyttet genre.
 */

public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.VerticalRecyclerViewViewHolder> implements HorizontalRecyclerViewAdapter.OnNoteListener {

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
        HorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter = new HorizontalRecyclerViewAdapter(context, singleItem, this);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setAdapter(horizontalRecyclerViewAdapter);

    }

    /*
    Nedenstående henter antallet af items i recyclerviewet.
     */
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onNoteClick(int position) {
        final Dialog dialog1 = new Dialog(context, R.style.AnimationDialog);
        dialog1.setContentView(R.layout.explore_movie_details);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView moviePicExplore = dialog1.findViewById(R.id.moviePicture);
        TextView title = dialog1.findViewById(R.id.movieTitleExplore);
        ImageView closeButton = dialog1.findViewById(R.id.closeButton2);
        Button readMore = dialog1.findViewById(R.id.readMore);

        /*
        Nedenstående skal i sidste ende sættes til at have de informationer, der svarer til det
        card man har klikket på under searchFragment. Der er problemer med dette lige nu, så
        til at starte med er det blot hardcoded.
         */
        moviePicExplore.setImageResource(R.drawable.poster_harry_potter_1);
        title.setText("Harry Potter");

        closeButton.setOnClickListener(view1 -> dialog1.dismiss());

        readMore.setOnClickListener(view1 -> {
            //Åbner movieDetails fragmentet
            ((AppCompatActivity)context).getSupportFragmentManager()
                    .beginTransaction().replace(R.id.fragment_nagivation, new MovieDetailsFragment())
                    .addToBackStack(null).commit();
            dialog1.dismiss();
        });
        dialog1.show();
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

            genre = itemView.findViewById(R.id.genre);
            recyclerView = itemView.findViewById(R.id.recyclerview2);
        }
    }

}
