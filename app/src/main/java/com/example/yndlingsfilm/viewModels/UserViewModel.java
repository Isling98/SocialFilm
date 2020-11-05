package com.example.yndlingsfilm.viewModels;

import androidx.lifecycle.LiveData;
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

    public void login(String username, String password){
        userRepo.login(username, password);
    }
}