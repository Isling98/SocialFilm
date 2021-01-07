package com.example.yndlingsfilm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yndlingsfilm.viewModels.UserViewModel;

public class ProfileReviewsFragment extends Fragment {
    UserViewModel userViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUserReviewsss(10);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_reviews, container, false);

    }

}