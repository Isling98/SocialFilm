package com.example.yndlingsfilm.requests;

import com.example.yndlingsfilm.requests.responses.GetUserResponse;
import com.example.yndlingsfilm.requests.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface UserApi {

    @FormUrlEncoded
    @POST("/token")
    Call<LoginResponse> login(
            @Field("username") String username,
            @Field("password") String password,
            @Field("grant_type") String hardcodedPass
    );


    @GET("/cinemano/user/{username}")
    Call<GetUserResponse> getUser(
            @Path("username") String username,
            @Header("Authorization") String token

    );
}
