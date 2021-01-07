package com.example.yndlingsfilm.requests.responses;

import com.example.yndlingsfilm.Model.Movie;
import com.example.yndlingsfilm.Model.Review;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {

    @SerializedName("review_id")
    @Expose()
    private int reviewId;

    @SerializedName("review_text")
    @Expose()
    private String reviewText;

    @SerializedName("movie_id")
    @Expose()
    private int movieId;

    @SerializedName("user_id")
    @Expose()
    private int userId;

    public int getReviewId() {
        return reviewId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getUserId() {
        return userId;
    }


    @Override
    public String toString() {
        return "ReviewResponse{" +
                "reviewId=" + reviewId +
                ", reviewText='" + reviewText + '\'' +
                ", movieId=" + movieId +
                ", userId=" + userId +
                '}';
    }
}
