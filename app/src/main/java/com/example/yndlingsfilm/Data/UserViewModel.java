package com.example.yndlingsfilm.Data;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {

    private MutableLiveData<List<User>> users;
    private MutableLiveData<User> CurrentuserMutableLiveData;
/*
Test af livedata + modelview. VI kommer tilbage til det når backenden er klar. 
 */

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<List<User>>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        DataController dC = new DataController();
        List<User> hej = new ArrayList<>();
        System.out.println("We are in the viewmodel");
        hej.add(dC.getUser1());
        hej.add(dC.getUser2());
        hej.add(dC.getUser3());
        System.out.println(hej.get(0).getUsername());
        users.postValue(hej);
    }

    private void loadUser() {
        DataController dC = new DataController();
        List<User> hej = new ArrayList<>();
        System.out.println("We are in the viewmodel");
        hej.add(dC.getUser1());
        hej.add(dC.getUser2());
        hej.add(dC.getUser3());
        System.out.println(hej.get(0).getUsername());
        users.postValue(hej);
    }
    public MutableLiveData<User> getUser() {

        if (CurrentuserMutableLiveData == null) {
            CurrentuserMutableLiveData = new MutableLiveData<>();
        }
        return CurrentuserMutableLiveData;

    }
}