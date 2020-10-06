package com.example.yndlingsfilm.NavigationBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yndlingsfilm.NavigationBar.Adapters.ModelHorizontal;
import com.example.yndlingsfilm.NavigationBar.Adapters.ModelVertical;
import com.example.yndlingsfilm.NavigationBar.Adapters.VerticalRecyclerViewAdapter;
import com.example.yndlingsfilm.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    /*
    Her defineres de forskellige reyclerviews der skal bruges, og derefter findes de vha. deres ID nede i onViewCreated.
     */
    RecyclerView verticalRecyclerView;
    VerticalRecyclerViewAdapter verticalRecyclerViewAdapter;
    ArrayList<ModelVertical> arrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        verticalRecyclerView = view.findViewById(R.id.recyclerview1);
        verticalRecyclerView.setHasFixedSize(true); //Dette gør at alle children til recyclerviewet har en fixed størrelse.

        //Benytter en linear layout manager til at sætte layoutet af fragmentet til at være det vertikale recyclerview.
        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        //Laver vertical adapter, som skal populate hvert view med data - herunder et billede
        arrayList = new ArrayList<>();
        verticalRecyclerViewAdapter = new VerticalRecyclerViewAdapter(getContext(), arrayList);
        verticalRecyclerView.setAdapter(verticalRecyclerViewAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Log siger det skippes mange frames, så måske nedenstående metode skal klares i baggrunden oppe mens viewet laves ovenover?
        setDataInRecyclerviews();
    }

    public void setDataInRecyclerviews(){

        //Første kategori
        ArrayList<ModelHorizontal> modelHorizontalArrayList = new ArrayList<>();
        modelHorizontalArrayList.add(new ModelHorizontal("Ringenes Herre 1", R.drawable.poster_ringenes_herre_1_poster_1));
        modelHorizontalArrayList.add(new ModelHorizontal("Ringenes Herre 2", R.drawable.poster_ringenes_herre_2));
        modelHorizontalArrayList.add(new ModelHorizontal("Ringenes Herre 3", R.drawable.poster_ringenes_herre_3_poster_1));
        modelHorizontalArrayList.add(new ModelHorizontal("Harry Potter 1", R.drawable.poster_harry_potter_1));
        modelHorizontalArrayList.add(new ModelHorizontal("Harry Potter 2", R.drawable.poster_harry_potter_2));

        //Anden kategori
        ArrayList<ModelHorizontal> modelHorizontalArrayList2 = new ArrayList<>();
        modelHorizontalArrayList2.add(new ModelHorizontal("Enemy at the gates", R.drawable.poster_enemy_at_the_gates));
        modelHorizontalArrayList2.add(new ModelHorizontal("Fury", R.drawable.poster_fury_1));
        modelHorizontalArrayList2.add(new ModelHorizontal("The transporter", R.drawable.poster_the_transporter));
        modelHorizontalArrayList2.add(new ModelHorizontal("The marksman", R.drawable.poster_the_marksman));
        modelHorizontalArrayList2.add(new ModelHorizontal("The detonator", R.drawable.poster_the_detonator));

        //Tredje kategori
        ArrayList<ModelHorizontal> modelHorizontalArrayList3 = new ArrayList<>();
        modelHorizontalArrayList3.add(new ModelHorizontal("The nun", R.drawable.poster_the_nun));
        modelHorizontalArrayList3.add(new ModelHorizontal("Hereditary", R.drawable.poster_hereditary));
        modelHorizontalArrayList3.add(new ModelHorizontal("Insidious", R.drawable.poster_insidious));
        modelHorizontalArrayList3.add(new ModelHorizontal("Sinister", R.drawable.poster_sinister));
        modelHorizontalArrayList3.add(new ModelHorizontal("The conjuring", R.drawable.poster_the_conjuring));

        ArrayList<ModelVertical> modelVerticalArrayList1 = new ArrayList<>();
        modelVerticalArrayList1.add(new ModelVertical("Fantasy", modelHorizontalArrayList));
        modelVerticalArrayList1.add(new ModelVertical("Action", modelHorizontalArrayList2));
        modelVerticalArrayList1.add(new ModelVertical("Horror", modelHorizontalArrayList3));

        arrayList.addAll(modelVerticalArrayList1);
        verticalRecyclerViewAdapter.notifyDataSetChanged();
    }
}