package com.android.moviecrackers.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.android.moviecrackers.model.movielist.MovieResult;

@Database(entities = {MovieResult.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase instance;

    public abstract MovieDao movieDao();
// it will create and provide access to the database
    public static synchronized MovieDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context.getApplicationContext(), MovieDatabase.class, "movie_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBacks)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallBacks = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
