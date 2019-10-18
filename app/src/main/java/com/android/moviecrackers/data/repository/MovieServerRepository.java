package com.android.moviecrackers.data.repository;

import android.app.Application;
import android.util.Log;

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

public class MovieServerRepository {
    private ApiServices apiServices;
    private MutableLiveData<MovieResponse> movieResponseMutableLiveData;

    /**
     * All data manipulation from the server database
     */

    public MovieServerRepository() {
        apiServices = RetroClient.getServiceApi(API.BASE_URL);
    }

    public MutableLiveData<MovieResponse> getTopRatedMovies() {

        movieResponseMutableLiveData = new MutableLiveData<>();

        Call<MovieResponse> defaultResponseCall = apiServices.getTopRatedMovies(API.API_KEY);

        defaultResponseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    movieResponseMutableLiveData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e("Error MovieResult", "onFailure: .", t);
            }
        });

        return movieResponseMutableLiveData;
    }
}
