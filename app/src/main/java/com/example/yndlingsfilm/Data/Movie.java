package com.example.yndlingsfilm.Data;

import java.util.Arrays;

public class Movie{
    private int id;
    private String title;
    private String overview;
    private String release_date;
    private String poster_path;
    private float vote_average;
    private int[] genre_ids;

    public Movie(int id, String title, String overview, String release_date,
                 String poster_path, float vote_average, int[] genre_ids) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.vote_average = vote_average;
        this.genre_ids = genre_ids;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public float getVote_average() {
        return vote_average;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", release_date='" + release_date + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", vote_average='" + vote_average + '\'' +
                ", genre_ids=" + Arrays.toString(genre_ids) +
                '}';
    }
}
