package com.android.moviecrackers.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.android.moviecrackers.model.movielist.MovieResult;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert
    void insertMovies(MovieResult movieResult);

    @Query("Delete from movie_table")
    void deleteAllMovieRecords();

    @Query("Select * from movie_table")
    LiveData<List<MovieResult>> getMovieList();
}
