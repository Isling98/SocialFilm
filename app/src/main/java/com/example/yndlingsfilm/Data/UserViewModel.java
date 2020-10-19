package com.example.yndlingsfilm.Data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.yndlingsfilm.Data.Fetch.UserRepo;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {

    private MutableLiveData<List<User>> users;
    private UserRepo userRepo;

    public void init(){
        if(users!=null){
            return;
        }
        userRepo = UserRepo.getInstance();
        users = userRepo.getUsers();
    }

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
        }
        return users;
    }

    public void addFriend(){

    }
}