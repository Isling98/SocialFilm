package com.example.yndlingsfilm.requests;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.yndlingsfilm.Model.User;
import com.example.yndlingsfilm.executors.AppExecutors;
import com.example.yndlingsfilm.requests.responses.DiscoverMoviesResponse;
import com.example.yndlingsfilm.requests.responses.GetUserResponse;
import com.example.yndlingsfilm.requests.responses.LoginResponse;
import com.example.yndlingsfilm.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class UserApiClient {
    private static final String TAG = "UserApiClient";
    private static UserApiClient instance;
    private MutableLiveData<List<User>> users;
    private LoginRunnable loginRunnable;
    private GetUserRunnable getUserRunnable;

    public static UserApiClient getInstance() {
        if(instance == null){
            instance = new UserApiClient();
        }
        return instance;
    }

    public MutableLiveData<List<User>> getUsers() {
        return users;
    }

    public void login(String username, String password){
        if(loginRunnable != null){
            loginRunnable = null;
        }
        loginRunnable = new LoginRunnable(username, password);

        final Future handler = AppExecutors.getInstance().getExecutorService().submit(loginRunnable);
        // drops the request if not done in 5 seconds to allow cached callbacks instead later.
        AppExecutors.getInstance().getExecutorService().schedule(new Runnable() {
            @Override
            public void run() {
                // let user know of the network error
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIME_LIMIT, TimeUnit.MILLISECONDS);
    }


    private class LoginRunnable implements Runnable{

        private String username;
        private String password;
        private Boolean cancelRequest;

        public LoginRunnable(String username, String password) {
            this.username = username;
            this.password = password;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try{
                Response response = login(username, password).execute();
                if(cancelRequest){
                    return;
                }
                if (response.code() == 200){
                    //returner en true her og giv brugeren det givne token
                    String token = ((LoginResponse)response.body()).getAccess_token();
                    Log.d(TAG, "run: " + token);
                    //getUser(username, token);

                    Log.d(TAG, "run: succes from code 200. user logged in");
                }else{
                    Log.d(TAG, "run: succes from else");
                }
            } catch (IOException e){
                e.printStackTrace();
                Log.d(TAG, "run: error line____________ from login()");
            }
        }

        private Call<LoginResponse> login(String username, String password){
            return ServiceGenerator.getUserApi().login(username, password, "password");
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
                Response response = getUser(username, token).execute();
                if(cancelRequest){
                    return;
                }
                if (response.code() == 200){
                    // sæt livedata og loggedInUser her
                    // nedenstående må kunne gøres bedre

                    int userId = ((GetUserResponse)response.body()).getUserId();
                    String username = ((GetUserResponse)response.body()).getUserName();
                    String password = ((GetUserResponse)response.body()).getPassword();
                    String email = ((GetUserResponse)response.body()).getEmail();
                    User user = new User(userId, username, password, email);
                    //må kunne optimeres. laver templiste med alle users i lviedata og adder derefter
                    //den nye user. alle disse users addes så på ny i livedata.
                    List<User> tempUsers = new ArrayList<>();
                    tempUsers.addAll(users.getValue());
                    tempUsers.add(user);
                    users.postValue(tempUsers);
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

        private Call<GetUserResponse> getUser(String username, String token){
            return ServiceGenerator.getUserApi().getUser(username, token);
        }

        private void setCancelRequest(){
            Log.d(TAG, "setCancelRequest: cancelled request");
            cancelRequest = true;
        }
    }
}
