package com.android.moviecrackers.data.repository.task;

import android.os.AsyncTask;

import com.android.moviecrackers.database.MovieDao;
import com.android.moviecrackers.model.movielist.MovieResult;

public class DeleteMovieAsyncTask extends AsyncTask<Void, Void, Void> {
    private MovieDao movieDao;

    public DeleteMovieAsyncTask(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        movieDao.deleteAllMovieRecords();
        return null;
    }
}
