package com.example.yndlingsfilm.Data;

public class Review {
    private int rating;
    private Movie movie;
    private String reviewInText;

    public Review(int rating, Movie movie, String reviewInText) {
        this.rating = rating;
        this.movie = movie;
        this.reviewInText = reviewInText;
    }

    public int getRating() {
        return rating;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getReviewInText() {
        return reviewInText;
    }
}
