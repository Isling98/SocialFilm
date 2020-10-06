package com.example.yndlingsfilm.Data;

import java.util.ArrayList;

public class User {

    private int userId;
    private String username;
    private String password;
    private String email;
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Review> reviews;
    private ArrayList<Rated> rated;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<Rated> getRated() {
        return rated;
    }

    public void setRated(ArrayList<Rated> rated) {
        this.rated = rated;
    }

    public User(int userId, String username, String password, String email, ArrayList<Movie> watchedMovies,
                ArrayList<Review> reviews, ArrayList<Rated> rated) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.watchedMovies = watchedMovies;
        this.reviews = reviews;
        this.rated = rated;
    }
}
