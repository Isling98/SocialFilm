package com.example.yndlingsfilm;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.yndlingsfilm.NavigationBar.ProfileFragment;
import com.example.yndlingsfilm.requests.ServiceGenerator;
import com.example.yndlingsfilm.requests.responses.RelationshipResponse;
import com.example.yndlingsfilm.viewModels.UserViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileAddFriendFragment extends Fragment implements View.OnClickListener {

    ImageView closeButton2;
    EditText addFriendUsername;
    Button sendFriendRequest;
    private UserViewModel userViewModel;

    private static final String TAG = "ProfileAddFriendFragmen";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_add_friend, container, false);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        closeButton2 = view.findViewById(R.id.closeButton2);
        addFriendUsername = view.findViewById(R.id.addFriendUsername);
        sendFriendRequest = view.findViewById(R.id.addFriendConfirm);

        closeButton2.setOnClickListener(this);
        sendFriendRequest.setOnClickListener(this);

        addFriendUsername.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.closeButton2){
            hideKeyboardFrom(getContext(), v);

            Fragment selectedFragment = new ProfileFragment();
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.fragment_nagivation, selectedFragment)
                    .addToBackStack(null)
                    .commit();
        }
        else if (v.getId() == R.id.addFriendConfirm){
            boolean digitsOnly = TextUtils.isDigitsOnly(addFriendUsername.getText());
            if (digitsOnly){
                //addFriend();

                String addFriendValue = addFriendUsername.getText().toString();
                int value = Integer.parseInt(addFriendValue);

                Log.d(TAG, "onClick: " + userViewModel.getLoggedInUser().getValue().getUserId());
                RelationshipResponse relationshipResponse = new RelationshipResponse(2,value);
                userViewModel.addFriend(relationshipResponse);

                CharSequence toastText = "Friend added!";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(getContext(), toastText, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                hideKeyboardFrom(getContext(), v);

                addFriendUsername.setText("");
            }
            else {

                CharSequence toastText = "Wrong user ID!";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(getContext(), toastText, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                hideKeyboardFrom(getContext(), v);

                addFriendUsername.setText("");
            }

        }
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
