package com.example.yndlingsfilm.Model;

import java.util.ArrayList;

public class User {

    private int userId;
    private String username;
    private String password;
    private String email;
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Review> reviews;
    private ArrayList<Rated> rated;
    private int friends; // antallet kan vi hente direkte fra listen af venner, men for nu har vi ikke listen
    private int followers; //same
    private String bio;


    public User(int userId, String username, String password, String email, String bio, ArrayList<Review> reviews) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.reviews = reviews;
        friends = 0;
        followers = 0;

    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getFriends() {
        return friends;
    }

    public int getFollowers() {
        return followers;
    }

    public String getUsername() {
        return username;
    }

    public int getUserId() {
        return userId;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }


    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public ArrayList<Rated> getRated() {
        return rated;
    }



    public void addRated(Rated newRated) {
        rated.add(newRated);
    }

    public void addWatchedMovies(Movie movie) {
        watchedMovies.add(movie);
    }

    public void addReview(Review review){
        reviews.add(review);
    }

    public void addFriend() {
        friends+=1;
    }

    public void addFollower() {
        followers+=1;
    }

    public void deleteFriend() {
        friends-=1;
    }

    public void deleteFollower() {
        followers-=1;
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
}
