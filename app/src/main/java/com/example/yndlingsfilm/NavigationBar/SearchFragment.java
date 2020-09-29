package com.example.yndlingsfilm.NavigationBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yndlingsfilm.R;

public class SearchFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    /*
    Her defineres de forskellige reyclerviews der skal bruges, og derefter findes de vha. deres ID nede i onViewCreated.
     */
    RecyclerView verticalRecyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        verticalRecyclerView = (verticalRecyclerView).findViewById(R.id.recyclerview1);
        verticalRecyclerView.setHasFixedSize(true); //Dette gør at alle children til recyclerviewet har en fixed størrelse.

        //Benytter en linear layout manager
        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        //Laver vertical adapter, som skal populate hvert view med data - herunder et billede




    }
}