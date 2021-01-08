package com.example.yndlingsfilm.Model;

public class Review {
    private int reviewId;
    private String reviewText;
    private int movieId;
    private int userId;
    private int rating;

    public Review(int reviewId, String reviewText, int movieId, int userId) {
        this.reviewId = reviewId;
        this.reviewText = reviewText;
        this.movieId = movieId;
        this.userId = userId;
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

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", reviewText='" + reviewText + '\'' +
                ", movieId=" + movieId +
                ", userId=" + userId +
                ", rating=" + rating +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public int getRating(){
        return rating;
    }
}
