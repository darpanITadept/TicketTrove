package com.example.assignment4.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "movie_table")
public class Movie implements Serializable {
    private String original_title;
    private String release_date;
    private String overview;
    private String poster_path;
    private double vote_average;

    @PrimaryKey(autoGenerate = true)
    private int movieID;
    private int numTicketsBought = 1;

    //    Getter
    public String getOriginal_title() {
        return original_title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getMovieID() {
        return movieID;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public double getVote_average() {
        return vote_average;
    }

    //    Setters
    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getNumTicketsBought() {
        return numTicketsBought;
    }

    public void setNumTicketsBought(int numTicketsBought) {
        this.numTicketsBought = numTicketsBought;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "original_title='" + original_title + '\'' +
                ", release_date='" + release_date + '\'' +
                ", overview='" + overview + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", vote_average=" + vote_average +
                ", movieID=" + movieID +
                ", numTicketsBought=" + numTicketsBought +
                '}';
    }
}

