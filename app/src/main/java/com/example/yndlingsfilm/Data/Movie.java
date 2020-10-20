package com.example.yndlingsfilm.Data;

public class Movie{
    private String movieId;
    private String movieTitle;
    private String overview;
    private String releaseDate;
    private int moviePicResource;
    private int rating;


    public Movie(String movieId, String movieTitle, String overview,
                 String releaseDate, int moviePicResource, int rating) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.moviePicResource = moviePicResource;
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }


    public String getMovieTitle() {
        return movieTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getMoviePic() {
        return moviePicResource;
    }

    public int getRating() {
        return rating;
    }
}
