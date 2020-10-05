package com.example.yndlingsfilm.Data;

import java.util.ArrayList;

public class DataController {

    //mockup data
    ArrayList<Movie> watchedMovies = new ArrayList<Movie>();
    ArrayList<Review> reviews = new ArrayList<Review>();

    public ArrayList<Review> getReviews() {

        return reviews;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public User getUser1() {
        User user1 = new User(1,"Markus","ViBurdeIkkeHavePassWordHer",
                "Markus@email.com",getWatchedMovies(),null,null);
        return user1;
    }
    public User getUser2() {
        User user2 = new User(2,"Asger","ViBurdeIkkeHavePassWordHer",
                "Asger@email.com",getWatchedMovies(),null,null);
        return user2;
    }
    public User getUser3() {
        User user3 = new User(2,"Andreas","ViBurdeIkkeHavePassWordHer",
                "Andreas@email.com",getWatchedMovies(),null,null);
        return user3;
    }
    


}
