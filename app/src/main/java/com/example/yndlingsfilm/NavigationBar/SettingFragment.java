package com.example.yndlingsfilm.NavigationBar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.yndlingsfilm.LoginActivity;
import com.example.yndlingsfilm.PhotoFragment;
import com.example.yndlingsfilm.R;
import com.example.yndlingsfilm.requests.UserApi;
import com.example.yndlingsfilm.requests.responses.GetUserResponse;
import com.example.yndlingsfilm.requests.responses.LoginResponse;
import com.example.yndlingsfilm.requests.responses.RelationshipResponse;
import com.example.yndlingsfilm.requests.responses.ReviewResponse;
import com.example.yndlingsfilm.viewModels.UserViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class SettingFragment extends Fragment implements View.OnClickListener {

    ImageButton editProfilePic;
    EditText editPassword;
    EditText editPassword2;
    ImageButton editPasswordConfirm;
    ImageButton deleteConfirm;
    ImageButton logoutConfirm;

    private UserViewModel userViewModel;
    private UserApi userApi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        editProfilePic = view.findViewById(R.id.editProfilePicButton);
        editPassword = view.findViewById(R.id.editPass);
        editPassword2 = view.findViewById(R.id.editPass2);
        editPasswordConfirm = view.findViewById(R.id.editPassConfirm);
        deleteConfirm = view.findViewById(R.id.deleteConfirm);
        logoutConfirm = view.findViewById(R.id.logOutConfirm);

        editProfilePic.setOnClickListener(this);
        editPasswordConfirm.setOnClickListener(this);
        deleteConfirm.setOnClickListener(this);
        logoutConfirm.setOnClickListener(this);


        return view;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.editProfilePicButton:
                Fragment selectedFragment = new PhotoFragment();
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.fragment_nagivation, selectedFragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.editPassConfirm:
                System.out.println("editpass virker");



                break;
            case R.id.logOutConfirm:

                logOut();


                break;
            case R.id.deleteConfirm:
                System.out.println("delete virker");

                userViewModel.deleteUser(userViewModel.getLoggedInUser().getValue().getUserId());

                logOut();

                break;
        }
    }

    public void logOut(){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

        getActivity().finish();
        //Nedenstående bruges til at fjerne alle fragments så der ikke kan klikkes 'tilbage' efter logout.
    }
}