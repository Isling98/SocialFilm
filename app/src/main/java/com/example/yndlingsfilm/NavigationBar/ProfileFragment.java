package com.example.yndlingsfilm.NavigationBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.yndlingsfilm.R;


public class ProfileFragment extends Fragment {
    private ImageView profilePic;
    private TextView username;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            profilePic = (ImageView) getView().findViewById(R.id.profilePic);
            username = (TextView) getView().findViewById(R.id.username);
            // lav user.
            //profilePic.setImageResource(user.getUserName);



        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}