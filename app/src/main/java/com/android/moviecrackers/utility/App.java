package com.android.moviecrackers.utility;

import android.app.Application;

import com.android.moviecrackers.database.MovieDatabase;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MovieDatabase.getInstance(this);

    }

}