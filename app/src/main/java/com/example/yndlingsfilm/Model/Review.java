package com.example.yndlingsfilm.Model;

public class Review {
    private int reviewId;
    private String reviewText;
    private int movieId;
    private int userId;
    private int rating;

    public Review(int reviewId, String reviewText, int movieId, int userId, int rating) {
        this.reviewId = reviewId;
        this.reviewText = reviewText;
        this.movieId = movieId;
        this.userId = userId;
        this.rating = rating;
    }


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

    public int getRating(){
        return rating;
    }
}
