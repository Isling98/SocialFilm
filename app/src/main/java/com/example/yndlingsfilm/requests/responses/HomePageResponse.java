package com.example.yndlingsfilm.requests.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class  HomePageResponse {

    @SerializedName("Users")
    @Expose()
    private List<GetUserResponse> userResponses;

    public List<GetUserResponse> getResponses() {
        return userResponses;
    }

    @Override
    public String toString() {
        return "HomePageResponse{" +
                "responses=" + userResponses +
                '}';
    }
}
