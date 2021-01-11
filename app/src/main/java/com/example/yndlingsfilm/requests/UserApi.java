package com.example.yndlingsfilm.requests;

import com.example.yndlingsfilm.requests.responses.GetUserResponse;
import com.example.yndlingsfilm.requests.responses.LoginResponse;
import com.example.yndlingsfilm.requests.responses.RelationshipResponse;
import com.example.yndlingsfilm.requests.responses.ReviewResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


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


    @POST("cinemano/relationship")
    Call<RelationshipResponse> addFriend(@Body RelationshipResponse relationshipResponse);


    @DELETE("cinemano/user/{user_id}")
    Call<Void> deleteUser(@Path("user_id") int id);

    @POST("cinemano/user/reviews")
    @FormUrlEncoded
    Call<ReviewResponse> saveReview(
            @Field("review_text") String review,
            @Field("movie_id") int movieID,
            @Field("rating") int rating
    );
}
