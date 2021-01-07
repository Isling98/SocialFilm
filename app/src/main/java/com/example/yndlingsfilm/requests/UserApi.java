package com.example.yndlingsfilm.requests;

import com.example.yndlingsfilm.requests.responses.GetUserResponse;
import com.example.yndlingsfilm.requests.responses.LoginResponse;
import com.example.yndlingsfilm.requests.responses.ReviewResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface UserApi {

    @FormUrlEncoded
    @POST("/token")
    Call<LoginResponse> login(
            @Header("Content-type") String wwww,
            @Field("grant_type") String hardcodedPass,
            @Field("username") String username,
            @Field("password") String password

    );


    @GET("/cinemano/user/username/{username}")
    Call<GetUserResponse> getUser(
            @Path("username") String username
            //@Header("Authorization") String token

    );

    @GET("/cinemano/reviews/{id}")
    Call<List<ReviewResponse>> getUserReview(
            @Path("id") int userId
    );
}
