package com.example.yndlingsfilm.requests.responses;

import com.example.yndlingsfilm.Model.Review;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUserResponse {

    @SerializedName("UserId")
    @Expose()
    private int UserId;

    @SerializedName("Username")
    @Expose()
    private String userName;

    @SerializedName("Password")
    @Expose()
    private String password;

    @SerializedName("Email")
    @Expose()
    private String email;

    @SerializedName("Role")
    @Expose()
    private String role;

    @SerializedName("Bio")
    @Expose()
    private String bio;

    @SerializedName("Review")
    @Expose()
    private List<Review> review;



    public int getUserId() {
        return UserId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getBio() {
        return bio;
    }

    public List<Review> getReviews(){
        return review;
    }

    @Override
    public String toString() {
        return "GetUserResponse{" +
                "UserId=" + UserId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
