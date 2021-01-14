package com.example.yndlingsfilm.Model.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.yndlingsfilm.Model.Review;
import com.example.yndlingsfilm.Model.User;
import com.example.yndlingsfilm.requests.UserApiClient;
import com.example.yndlingsfilm.requests.responses.RelationshipResponse;

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

    public List<User> getHomePage (int id){
        try{
            return userApiClient.getHomePage(id);
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

    public void deleteUser(int userId){
        userApiClient.deleteUser(userId);
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

    public void saveReview(int movieID, int rating, String review){
        userApiClient.saveReview(movieID, rating, review);
    }
}
