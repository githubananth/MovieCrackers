package com.android.moviecrackers.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.moviecrackers.data.network.ApiServices;
import com.android.moviecrackers.data.network.RetroClient;
import com.android.moviecrackers.data.repository.task.InsertMovieAsyncTask;
import com.android.moviecrackers.database.MovieDao;
import com.android.moviecrackers.database.MovieDatabase;
import com.android.moviecrackers.model.movielist.MovieResponse;
import com.android.moviecrackers.model.movielist.MovieResult;
import com.android.moviecrackers.utility.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieLocalRepository {
    private MovieDao movieDao;
    private LiveData<List<MovieResult>> movieListLiveData;

    /**
     * All data manipulation from the local database
     *
     * @param application
     */

    public MovieLocalRepository(Application application) {
        MovieDatabase movieDatabase = MovieDatabase.getInstance(application);
        movieDao = movieDatabase.movieDao();
    }

    public void insert(MovieResult movieResult) {
        new InsertMovieAsyncTask(movieDao).execute(movieResult);
    }

    public LiveData<List<MovieResult>> getMoviesList() {
        movieListLiveData = movieDao.getMovieList();
        return movieListLiveData;
    }


}
