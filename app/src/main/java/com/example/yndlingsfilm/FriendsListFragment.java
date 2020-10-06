package com.example.yndlingsfilm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class FriendsListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friends_list, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        LinearLayout container = (LinearLayout) view.findViewById(R.id.profile_friends_friends);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

//        List listOfNames = new ArrayList();
//        MyViewModel myViewModel = new MyViewModel();
//        listOfNames.add((myViewModel.getUsers().getValue().get(1).get
//
//        TextView tv =new TextView(getContext());
//        tv.setLayoutParams(lparams);
//        TextView tv2=new TextView(getContext());
//        tv.setLayoutParams(lparams);
//        tv.setText("test");
//        tv2.setText("hej");
//        container.addView(tv);
//        container.addView(tv2);
    }
}