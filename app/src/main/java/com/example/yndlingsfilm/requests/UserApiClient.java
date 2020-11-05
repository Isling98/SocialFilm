package com.example.yndlingsfilm.requests;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.yndlingsfilm.Model.User;
import com.example.yndlingsfilm.executors.AppExecutors;
import com.example.yndlingsfilm.requests.responses.RetrieveUserResponse;
import com.example.yndlingsfilm.util.Constants;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class UserApiClient {
    private static final String TAG = "UserApiClient";
    private static UserApiClient instance;
    private MutableLiveData<List<User>> users;
    private RetrieveUserRunnable retrieveUserRunnable;

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
        if(retrieveUserRunnable != null){
            retrieveUserRunnable = null;
        }
        retrieveUserRunnable = new RetrieveUserRunnable(username, password);

        final Future handler = AppExecutors.getInstance().getExecutorService().submit(retrieveUserRunnable);
        // drops the request if not done in 5 seconds to allow cached callbacks instead later.
        AppExecutors.getInstance().getExecutorService().schedule(new Runnable() {
            @Override
            public void run() {
                // let user know of the network error
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIME_LIMIT, TimeUnit.MILLISECONDS);
    }


    private class RetrieveUserRunnable implements Runnable{

        private String username;
        private String password;
        private Boolean cancelRequest;

        public RetrieveUserRunnable(String username, String password) {
            this.username = username;
            this.password = password;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try{
                Response response = getUser(username, password).execute();
                if(cancelRequest){
                    return;
                }
                if (response.code() == 200){
                    //returner en true her og giv brugeren det givne token
                    Log.d(TAG, "run: succes from code 200");
                }else{
                    Log.d(TAG, "run: succes from else");
                }
            } catch (IOException e){
                e.printStackTrace();
                Log.d(TAG, "run: error line____________");
            }
        }

        private Call<RetrieveUserResponse> getUser(String username, String password){
            return ServiceGenerator.getUserApi().getUser(username, password, "password");
        }


        private void setCancelRequest(){
            Log.d(TAG, "setCancelRequest: cancelled request");
            cancelRequest = true;
        }
    }


}
