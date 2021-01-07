package com.example.yndlingsfilm.requests;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.Model.Review;
import com.example.yndlingsfilm.Model.User;
import com.example.yndlingsfilm.executors.AppExecutors;
import com.example.yndlingsfilm.requests.responses.DiscoverMoviesResponse;
import com.example.yndlingsfilm.requests.responses.GetUserResponse;
import com.example.yndlingsfilm.requests.responses.LoginResponse;
import com.example.yndlingsfilm.requests.responses.ReviewResponse;
import com.example.yndlingsfilm.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class UserApiClient {
    private static final String TAG = "UserApiClient";
    private static UserApiClient instance;
    private MutableLiveData<List<User>> users;
    private MutableLiveData<List<Review>> userReview;
    private MutableLiveData<User> loggedInUser;
    private LoginCallable loginCallable;
    private GetUserRunnable getUserRunnable;
    private GetUserReviewRunnable getUserReviewRunnable;
    private boolean isUserLoggedIn;


    public static UserApiClient getInstance() {
        if(instance == null){
            instance = new UserApiClient();

        }
        return instance;
    }

    public UserApiClient() {
        loggedInUser = new MutableLiveData<>();
        users = new MutableLiveData<>();
        userReview = new MutableLiveData<>();
    }

    public MutableLiveData<List<User>> getUsers() {
        return users;
    }

    public MutableLiveData<User> getLoggedInUser() {
        return loggedInUser;
    }

    public boolean login(String username, String password) throws ExecutionException, InterruptedException {
        if(loginCallable != null){
            loginCallable = null;
        }
        loginCallable = new LoginCallable(username, password);

        final Future handler = AppExecutors.getInstance().getExecutorService().submit(loginCallable);
        isUserLoggedIn = (boolean) handler.get();
        // drops the request if not done in 5 seconds to allow cached callbacks instead later.
        AppExecutors.getInstance().getExecutorService().schedule(new Runnable() {
            @Override
            public void run() {
                // let user know of the network error
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIME_LIMIT, TimeUnit.MILLISECONDS);
        return isUserLoggedIn;
    }
//review
    public MutableLiveData<List<Review>> getUserReviewss() {
        return userReview;
    }

    private class LoginCallable implements Callable {

        private String username;
        private String password;
        private Boolean cancelRequest;

        public LoginCallable(String username, String password) {
            this.username = username;
            this.password = password;
            cancelRequest = false;
        }

        @Override
        public Boolean call() {
            try{
                Response response = login(username, password).execute();

                if(cancelRequest){
                    return false;
                }
                if (response.code() == 200){
                    //returner en true her og giv brugeren det givne token
                    isUserLoggedIn = true;
                    String token = ((LoginResponse)response.body()).getAccess_token();
                    Log.d(TAG, "run: " + token);
                    getUser(username, token);

                    Log.d(TAG, "run: succes from code 200. user logged in");
                    return true;

                }else if (response.code() == 400){
                    Log.d(TAG, "Wrong grant type ____________________");
                    return false;

                } else{
                    Log.d(TAG,"Somethign else than 400 or 200");
                    return false;
                }
            } catch (IOException e){
                e.printStackTrace();
                Log.d(TAG, "run: error line____________ from login()");
                return false;
            }
        }

        private Call<LoginResponse> login(String username, String password){

            return ServiceGenerator.getUserApi().login("application/x-www-form-urlencoded","password",username, password);
        }

        private void setCancelRequest(){
            Log.d(TAG, "setCancelRequest: cancelled request");
            cancelRequest = true;
        }
    }


    public void getUser(String username, String token){
        if(getUserRunnable != null){
            getUserRunnable = null;
        }
        getUserRunnable = new GetUserRunnable(username, token);

        final Future handler = AppExecutors.getInstance().getExecutorService().submit(getUserRunnable);
        // drops the request if not done in 5 seconds to allow cached callbacks instead later.
        AppExecutors.getInstance().getExecutorService().schedule(new Runnable() {
            @Override
            public void run() {
                // let user know of the network error
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIME_LIMIT, TimeUnit.MILLISECONDS);
    }

    private class GetUserRunnable implements Runnable{

        private String username;
        private String token;
        private Boolean cancelRequest;

        public GetUserRunnable(String username, String token) {
            this.username = username;
            this.token = token;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try{
                Response response = getUser(username).execute();
                if(cancelRequest){
                    return;
                }
                if (response.code() == 200){

                    int userId = ((GetUserResponse)response.body()).getUserId();
                    String username = ((GetUserResponse)response.body()).getUserName();
                    String password = ((GetUserResponse)response.body()).getPassword();
                    String email = ((GetUserResponse)response.body()).getEmail();
                    String bio = ((GetUserResponse)response.body()).getBio();
                    User user = new User(userId, username, password, email, bio);
                    Log.d(TAG, "run: ____________________________");
                    Log.d(TAG, "run: " + user.getUsername());
                    //må kunne optimeres. laver templiste med alle users i lviedata og adder derefter
                    //den nye user. alle disse users addes så på ny i livedata.

                    /* herfra skal stå når man laver en bruger første gang og ikke her
                    List<User> tempUsers = new ArrayList<>();
                    tempUsers.addAll(users.getValue());
                    tempUsers.add(user);
                    // nødvendigt?
                    users.setValue(null);
                    users.postValue(tempUsers);
                     hertil*/

                    loggedInUser.postValue(user);
                    Log.d(TAG, "run: " + loggedInUser.getValue().getUsername());
                    Log.d(TAG, "run: username = " + users.getValue().get(0).getUsername());
                    Log.d(TAG, "run: succes from code 200");
                }else{
                    Log.d(TAG, "run: succes from else");
                }
            } catch (IOException e){
                e.printStackTrace();
                Log.d(TAG, "run: error line____________ from getUser()");
            }
        }

        private Call<GetUserResponse> getUser(String username){
            return ServiceGenerator.getUserApi().getUser(username);
        }

        private void setCancelRequest(){
            Log.d(TAG, "setCancelRequest: cancelled request");
            cancelRequest = true;
        }
    }


    public void getUserReviews(int userId){
        if(getUserReviewRunnable != null){
            getUserReviewRunnable = null;
        }
        getUserReviewRunnable = new GetUserReviewRunnable(userId);

        final Future handler = AppExecutors.getInstance().getExecutorService().submit(getUserReviewRunnable);
        // drops the request if not done in 5 seconds to allow cached callbacks instead later.
        AppExecutors.getInstance().getExecutorService().schedule(new Runnable() {
            @Override
            public void run() {
                // let user know of the network error
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIME_LIMIT, TimeUnit.MILLISECONDS);
    }

    private class GetUserReviewRunnable implements Runnable{

        private int userId;
        private Boolean cancelRequest;

        public GetUserReviewRunnable(int userId) {
            this.userId = userId;
            cancelRequest = false;
        }

        @Override
        public void run() {

            try{
                Response response = getUserReview(userId).execute();
                if(cancelRequest){
                    return;
                }

                if (response.code() == 200){

                 String hello = ((Review) response.body()).getReviewText();





                 Log.d(TAG,"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + hello);

                }else{

                    Log.d(TAG, "run: succes from else");
                }
            } catch (IOException e){
                e.printStackTrace();
                Log.d(TAG, "run: error line____________ from getUser()");
            }
        }

        private Call<List<ReviewResponse>> getUserReview(int userId){

            return ServiceGenerator.getUserApi().getUserReview(userId);
        }

        private void setCancelRequest(){
            Log.d(TAG, "setCancelRequest: cancelled request");
            cancelRequest = true;
        }}




}
