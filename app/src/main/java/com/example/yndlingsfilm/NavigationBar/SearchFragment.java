package com.example.yndlingsfilm.NavigationBar;

import android.nfc.Tag;
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
/*
        //Første kategori
        ArrayList<ModelHorizontal> modelHorizontalArrayList = new ArrayList<>();
        modelHorizontalArrayList.add(new ModelHorizontal("Ringenes Herre 1", R.drawable.ringenes_herre_1_poster_1));
        modelHorizontalArrayList.add(new ModelHorizontal("Ringenes Herre 2", R.drawable.ringenes_herre_2));
        modelHorizontalArrayList.add(new ModelHorizontal("Ringenes Herre 3", R.drawable.ringenes_herre_3_poster_1));
        modelHorizontalArrayList.add(new ModelHorizontal("Harry Potter 2", R.drawable.harry_potter_2));

        //Anden kategori
        ArrayList<ModelHorizontal> modelHorizontalArrayList2 = new ArrayList<>();
        modelHorizontalArrayList2.add(new ModelHorizontal("Harry Potter 2", R.drawable.harry_potter_2));
        modelHorizontalArrayList2.add(new ModelHorizontal("Ringenes Herre 1", R.drawable.ringenes_herre_1_poster_1));
        modelHorizontalArrayList2.add(new ModelHorizontal("Ringenes Herre 2", R.drawable.ringenes_herre_2));
        modelHorizontalArrayList2.add(new ModelHorizontal("Ringenes Herre 3", R.drawable.ringenes_herre_3_poster_1));

        //Tredje kategori
        ArrayList<ModelHorizontal> modelHorizontalArrayList3 = new ArrayList<>();
        modelHorizontalArrayList3.add(new ModelHorizontal("Ringenes Herre 1", R.drawable.ringenes_herre_1_poster_1));
        modelHorizontalArrayList3.add(new ModelHorizontal("Ringenes Herre 2", R.drawable.ringenes_herre_2));
        modelHorizontalArrayList3.add(new ModelHorizontal("Harry Potter 2", R.drawable.harry_potter_2));
        modelHorizontalArrayList3.add(new ModelHorizontal("Ringenes Herre 3", R.drawable.ringenes_herre_3_poster_1));

        ArrayList<ModelVertical> modelVerticalArrayList1 = new ArrayList<>();
        modelVerticalArrayList1.add(new ModelVertical("Fantasy", modelHorizontalArrayList));
        modelVerticalArrayList1.add(new ModelVertical("Action", modelHorizontalArrayList2));
        modelVerticalArrayList1.add(new ModelVertical("Horror", modelHorizontalArrayList3));


        arrayList.add(modelVerticalArrayList1);
        verticalRecyclerViewAdapter.notifyDataSetChanged();
        */

//Nedenstående er en besværlig løsning til at indsætte data, så derfor skal ovenstående laves så det virker og er lettere
        ArrayList<ModelHorizontal> modelHorizontalArrayList = new ArrayList<>();
        ArrayList<ModelHorizontal> modelHorizontalArrayList2 = new ArrayList<>();
        ArrayList<ModelHorizontal> modelHorizontalArrayList3 = new ArrayList<>();
        ModelVertical modelVertical = new ModelVertical("Action", modelHorizontalArrayList);
        ModelVertical modelVertical2 = new ModelVertical("Fantasy", modelHorizontalArrayList2);
        ModelVertical modelVertical3 = new ModelVertical("Comedy", modelHorizontalArrayList3);

        //Første genre
        ModelHorizontal modelHorizontal = new ModelHorizontal("Ringenes Herre", R.drawable.ringenes_herre_2);
        ModelHorizontal modelHorizontal2 = new ModelHorizontal("Ringenes Herre", R.drawable.ringenes_herre_3_poster_1);
        ModelHorizontal modelHorizontal3 = new ModelHorizontal("Ringenes Herre", R.drawable.ringenes_herre_1_poster_1);
        ModelHorizontal modelHorizontal4 = new ModelHorizontal("Harry Potter", R.drawable.harry_potter_2);

        modelHorizontalArrayList.add(modelHorizontal);
        modelHorizontalArrayList.add(modelHorizontal2);
        modelHorizontalArrayList.add(modelHorizontal3);
        modelHorizontalArrayList.add(modelHorizontal4);

        //Anden genre
        ModelHorizontal modelHorizontal5 = new ModelHorizontal("Harry Potter", R.drawable.harry_potter_2);
        ModelHorizontal modelHorizontal6 = new ModelHorizontal("Ringenes Herre", R.drawable.ringenes_herre_3_poster_1);
        ModelHorizontal modelHorizontal7 = new ModelHorizontal("Ringenes Herre", R.drawable.ringenes_herre_1_poster_1);
        ModelHorizontal modelHorizontal8 = new ModelHorizontal("Ringenes Herre", R.drawable.ringenes_herre_2);

        modelHorizontalArrayList2.add(modelHorizontal5);
        modelHorizontalArrayList2.add(modelHorizontal6);
        modelHorizontalArrayList2.add(modelHorizontal7);
        modelHorizontalArrayList2.add(modelHorizontal8);

        //Tredje genre
        ModelHorizontal modelHorizontal9 = new ModelHorizontal("Ringenes Herre", R.drawable.ringenes_herre_3_poster_1);
        ModelHorizontal modelHorizontal10 = new ModelHorizontal("Harry Potter", R.drawable.harry_potter_2);
        ModelHorizontal modelHorizontal11 = new ModelHorizontal("Ringenes Herre", R.drawable.ringenes_herre_1_poster_1);
        ModelHorizontal modelHorizontal12 = new ModelHorizontal("Ringenes Herre", R.drawable.ringenes_herre_2);

        modelHorizontalArrayList3.add(modelHorizontal9);
        modelHorizontalArrayList3.add(modelHorizontal10);
        modelHorizontalArrayList3.add(modelHorizontal11);
        modelHorizontalArrayList3.add(modelHorizontal12);

        //Adder de forskellige genre til det vertikale recyclerview
        modelVertical.setArrayList(modelHorizontalArrayList);
        arrayList.add(modelVertical);
        modelVertical2.setArrayList(modelHorizontalArrayList2);
        arrayList.add(modelVertical2);
        modelVertical.setArrayList(modelHorizontalArrayList3);
        arrayList.add(modelVertical3);

        verticalRecyclerViewAdapter.notifyDataSetChanged();

    }
}