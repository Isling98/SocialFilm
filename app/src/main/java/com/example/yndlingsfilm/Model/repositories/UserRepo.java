package com.example.yndlingsfilm.Model.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.yndlingsfilm.Model.User;
import com.example.yndlingsfilm.requests.UserApiClient;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

@Singleton
public class UserRepo{
    private static UserRepo instance;
    private UserApiClient userApiClient;

    public static UserRepo getInstance(){
        if(instance == null){
            instance = new UserRepo();
        }
        return instance;
    }

    public UserRepo() {
        userApiClient = UserApiClient.getInstance();
    }

    // skal nok ikke være mutable
    public MutableLiveData<List<User>> getUsers(){
        return userApiClient.getUsers();
    }

    public MutableLiveData<User> getLoggedInUser(){
        return userApiClient.getLoggedInUser();
    }

    public boolean login(String username, String password){
        try{
            return userApiClient.login(username, password);
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void getUser(String username, String token){
        userApiClient.getUser(username, token);
    }
}
