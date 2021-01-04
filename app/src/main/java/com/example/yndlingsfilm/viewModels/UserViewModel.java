package com.example.yndlingsfilm.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yndlingsfilm.Model.repositories.UserRepo;
import com.example.yndlingsfilm.Model.User;

import java.util.List;

public class UserViewModel extends ViewModel {

    private UserRepo userRepo;

    public UserViewModel() {
        userRepo = UserRepo.getInstance();
    }

    public MutableLiveData<List<User>> getUsers(){
        return userRepo.getUsers();
    }

    public MutableLiveData<User> getLoggedInUser(){
        return userRepo.getLoggedInUser();
    }

    public boolean login(String username, String password){
        return userRepo.login(username, password);
    }

    public void getUser(String username, String token){
        userRepo.getUser(username, token);
    }
}