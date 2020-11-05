package com.example.yndlingsfilm.NavigationBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.yndlingsfilm.PhotoFragment;
import com.example.yndlingsfilm.R;

public class SettingFragment extends Fragment implements View.OnClickListener {

    ImageButton editProfilePic;
    EditText editPassword;
    EditText editPassword2;
    ImageButton editPasswordConfirm;
    ImageButton deleteConfirm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        editProfilePic = view.findViewById(R.id.editProfilePicButton);
        editPassword = view.findViewById(R.id.editPass);
        editPassword2 = view.findViewById(R.id.editPass2);
        editPasswordConfirm = view.findViewById(R.id.editPassConfirm);
        deleteConfirm = view.findViewById(R.id.deleteConfirm);

        editProfilePic.setOnClickListener(this);
        editPasswordConfirm.setOnClickListener(this);
        deleteConfirm.setOnClickListener(this);





        return view;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.editProfilePicButton){
            Fragment selectedFragment = new PhotoFragment();
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.fragment_nagivation, selectedFragment)
                    .addToBackStack(null)
                    .commit();
        }


        /*
        -Lav nyt fragment(måske activity) for at skifte billede.
        -Lave foreløbig placeholder til editPassword (snak med andreas om hvordan denne skal laves)
        -Lave deleteConfirm placeholder.
         */

    }
}