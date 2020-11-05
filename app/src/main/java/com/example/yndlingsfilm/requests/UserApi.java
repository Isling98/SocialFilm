package com.example.yndlingsfilm.requests;

import com.example.yndlingsfilm.requests.responses.RetrieveUserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {

    @FormUrlEncoded
    @POST("/token")
    Call<RetrieveUserResponse> getUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("grant_type") String hardcodedPass
    );
}
