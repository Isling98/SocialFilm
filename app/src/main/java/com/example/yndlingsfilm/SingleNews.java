package com.example.yndlingsfilm;

public class SingleNews {
    private int profilePicResource;
    private int moviePicResource;
    private String headLine;
    private String text;
    private String movieName;
    private String rating;

    // tag hellere kun et enkelt objekt i konstruktøren og brug dets getmetoder til at initialisere.
    public SingleNews(int profilePicResource, int moviePicResource,
                      String headLine, String text, String movieName, String rating) {
        this.profilePicResource = profilePicResource;
        this.moviePicResource = moviePicResource;
        this.headLine = headLine;
        this.text = text;
        this.movieName = movieName;
        this.rating = rating;
    }








    public int getProfilePicResource() {
        return profilePicResource;
    }

    public int getMoviePicResource() {
        return moviePicResource;
    }

    public String getHeadLine() {
        return headLine;
    }

    public String getText() {
        return text;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getRating() {
        return rating;
    }
}
