package com.example.yndlingsfilm;

public class SingleNews {
    private int profilePicResource;
    private int moviePicResource;
    private String headLine;
    private String time;
    private String movieName;
    private int rating;
//    private User user; så vi kan komme ind på useren profil direkte.

    // tag hellere kun et enkelt objekt i konstruktøren og brug dets getmetoder til at initialisere.
    public SingleNews(int profilePicResource, int moviePicResource,
                      String headLine, String time, String movieName, int rating) {
        this.profilePicResource = profilePicResource;
        this.moviePicResource = moviePicResource;
        this.headLine = headLine;
        this.time = time;
        this.movieName = movieName;
        this.rating = rating;
    }
    // åben detail fragment her
    public void openDetails(){
        // popup
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

    public String getTime() {
        return time;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getRating() {
        return rating;
    }
}
