package com.example.yndlingsfilm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yndlingsfilm.viewModels.UserViewModel;

public class ProfileBioFragment extends Fragment {

    TextView profileBio;
    private UserViewModel userViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_bio, container, false);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        profileBio = view.findViewById(R.id.profile_bio);

        String bioFull = userViewModel.getLoggedInUser().getValue().getBio();

        profileBio.setText(bioFull);

        return view;
    }
}