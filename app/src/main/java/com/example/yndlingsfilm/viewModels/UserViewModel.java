package com.example.yndlingsfilm.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yndlingsfilm.Model.Review;
import com.example.yndlingsfilm.Model.repositories.UserRepo;
import com.example.yndlingsfilm.Model.User;
import com.example.yndlingsfilm.requests.responses.RelationshipResponse;

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

    public User getUser(String username, String token){
        return userRepo.getUser(username, token);
    }

    public Object addFriend(RelationshipResponse relationshipResponse){
        return userRepo.addFriend(relationshipResponse);
    }
    //review
    public MutableLiveData<List<Review>> getUserReviews(){
        return userRepo.getUserReviews();
    }
    public void getUserReviewsss(int userId){
        userRepo.getUserReviews(userId);
    }

    public void saveReview(int movieID, int rating, String review){
        userRepo.saveReview(movieID, rating, review);
    }
}