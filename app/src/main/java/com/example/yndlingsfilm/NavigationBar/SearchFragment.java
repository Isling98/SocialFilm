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

        setDataInRecyclerviews();

    }

    public void setDataInRecyclerviews(){

        for (int i = 1; i <= 3; i++){
            ModelVertical modelVertical = new ModelVertical();

            modelVertical.setGenre("Action" + i);

            ArrayList<ModelHorizontal> modelHorizontalArrayList = new ArrayList<>();

            for (int j = 0; j <= 4; j++){
                ModelHorizontal modelHorizontal = new ModelHorizontal();
                modelHorizontal.setName("Ringenes Herre " + j);

                modelHorizontalArrayList.add(modelHorizontal);
            }

            modelVertical.setArrayList(modelHorizontalArrayList);
            arrayList.add(modelVertical);
        }

        verticalRecyclerViewAdapter.notifyDataSetChanged();

    }
}