package com.example.assignment4.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment4.models.Movie;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MovieDAO {

    //    insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertMovie(Movie movie);

    //    retrieve data
    @Query("SELECT * FROM movie_table")
    public List<Movie> getAllMovies();

    //retrieve ONE movie
    @Query("SELECT* FROM movie_table WHERE movieID = :id")
    public Movie getMovieById(int id);

    //update an movie
    @Update
    public void updateMovie(Movie movieToUpdate);

    //delete one movie
    @Delete
    public void deleteMovie(Movie movieToDelete);
}
