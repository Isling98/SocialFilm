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
