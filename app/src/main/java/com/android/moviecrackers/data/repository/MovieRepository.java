package com.android.moviecrackers.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.moviecrackers.data.network.ApiServices;
import com.android.moviecrackers.data.network.RetroClient;
import com.android.moviecrackers.model.movielist.MovieResponse;
import com.android.moviecrackers.utility.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieRepository {
    private static MovieRepository movieRepository;
    private static final String TAG = MovieRepository.class.getSimpleName();

    public static MovieRepository getInstance() {

        if (movieRepository == null) {
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    private ApiServices apiServices;
    private MutableLiveData<MovieResponse> movieResponseMutableLiveData;

    private MovieRepository() {
        apiServices = RetroClient.getServiceApi(API.BASE_URL);
    }


    public MutableLiveData<MovieResponse> getTopRatedMovies() {

        movieResponseMutableLiveData = new MutableLiveData<>();

        Call<MovieResponse> defaultResponseCall = apiServices.getTopRatedMovies(API.API_KEY);

//        Log.e(TAG, "onRequestURL" + defaultResponseCall.request().url());

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
