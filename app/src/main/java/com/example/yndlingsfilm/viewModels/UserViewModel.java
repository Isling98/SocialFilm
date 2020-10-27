package com.example.yndlingsfilm.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yndlingsfilm.Model.repositories.UserRepo;
import com.example.yndlingsfilm.Model.User;

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