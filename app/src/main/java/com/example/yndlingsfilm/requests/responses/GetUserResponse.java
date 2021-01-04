package com.example.yndlingsfilm.requests.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUserResponse {

    @SerializedName("userId")
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

    // add friends, bio, follower etc her

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
