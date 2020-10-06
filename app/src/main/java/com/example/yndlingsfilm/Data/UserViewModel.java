package com.example.yndlingsfilm.Data;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yndlingsfilm.Data.Fetch.UserRepo;

import java.util.ArrayList;
import java.util.List;
/*
Test af livedata + modelview. VI kommer tilbage til det når backenden er klar.
 */
//UserViewModel holder på userdata for profile viewet
public class UserViewModel extends ViewModel {
    UserRepo userRepo;

    private MutableLiveData<List<User>> users;
    private MutableLiveData<User> user;

    public MutableLiveData<User> getUser(String userName) {
        //userRepo metoden findes ikke endnu;
    user = userRepo.getUser(String userName);
    if (user == null) {
        user = new MutableLiveData<>();
    }
    return user;

}







//Randomness

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

}