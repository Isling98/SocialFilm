package com.example.yndlingsfilm.Model.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.yndlingsfilm.Model.Review;
import com.example.yndlingsfilm.Model.User;
import com.example.yndlingsfilm.requests.UserApiClient;
import com.example.yndlingsfilm.requests.responses.RelationshipResponse;

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



    public boolean login(String username, String password){
        try{
            return userApiClient.login(username, password);
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public User getUser(String username, String token){
        try{
            return userApiClient.getUser(username, token);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Object addFriend(RelationshipResponse relationshipResponse){
        try{
            return userApiClient.addFriend(relationshipResponse);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //reviews
    public MutableLiveData<User> getLoggedInUser(){
        return userApiClient.getLoggedInUser();
    }

    public void getUserReviews(int userId){
        userApiClient.getUserReviews(userId);
    }

    public MutableLiveData<List<Review>> getUserReviews(){
        return userApiClient.getUserReviewss();
    }
}
