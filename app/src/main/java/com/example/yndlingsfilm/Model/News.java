package com.example.yndlingsfilm.Model;

public class News {
    private String profileUrl;
    private Movie movie;
    private String movieUrl;
    private String headLine;
    private String movieName;
    private int rating;
    private boolean liked;
    private String reviewInText;




    // tag hellere kun et enkelt objekt i konstruktøren og brug dets getmetoder til at initialisere.
    public News(String profileUrl,String movieUrl,
                String headLine, String movieName, int rating, String reviewInText, Movie movie) {
        this.profileUrl = profileUrl;
        this.movieUrl = movieUrl;
        this.headLine = headLine;
        this.movieName = movieName;
        this.rating = rating;
        liked = false;
        this.reviewInText = reviewInText;
        this.movie = movie;
    }
    // åben detail fragment her
    public void openDetails(){
        // popup
    }


    public String getProfileUrl() {
        return profileUrl;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public String getHeadLine() {
        return headLine;
    }

    public Movie getMovie(){
        return movie;
    }



    public String getMovieName() {
        return movieName;
    }

    public int getRating() {
        return rating;
    }

    public boolean isLiked() {
        return liked;
    }

    public String getReviewInText() {
        return reviewInText;
    }
}
