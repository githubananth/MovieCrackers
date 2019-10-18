package com.android.moviecrackers.data.repository.task;

import android.os.AsyncTask;

import com.android.moviecrackers.database.MovieDao;
import com.android.moviecrackers.model.movielist.MovieResult;

public class InsertMovieAsyncTask extends AsyncTask<MovieResult, Void, Void> {
    private MovieDao movieDao;

    public InsertMovieAsyncTask(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    protected Void doInBackground(MovieResult... movieResults) {
        movieDao.insert(movieResults[0]);
        return null;
    }
}
