package com.android.moviecrackers.data.network;


import com.android.moviecrackers.model.moviedetails.MovieDetailsResponse;
import com.android.moviecrackers.model.movielist.MovieResponse;
import com.android.moviecrackers.utility.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {

    @GET(API.GET_TOP_RATED_MOVIES)
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("{movie_id}")
    Call<MovieDetailsResponse> getProductionCompany(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

}
