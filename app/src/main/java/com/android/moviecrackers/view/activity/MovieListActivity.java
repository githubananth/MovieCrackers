package com.android.moviecrackers.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.moviecrackers.R;
import com.android.moviecrackers.data.viewmodel.MovieModelView;
import com.android.moviecrackers.databinding.ActivityMovieListBinding;
import com.android.moviecrackers.interfaces.ItemClickListener;
import com.android.moviecrackers.model.movielist.MovieResponse;
import com.android.moviecrackers.model.movielist.MovieResult;
import com.android.moviecrackers.utility.NetworkCheck;
import com.android.moviecrackers.view.adapter.MovieAdapter;

import java.util.List;

public class MovieListActivity extends AppCompatActivity implements ItemClickListener {
    private Context mContext;
    private ActivityMovieListBinding activityMovieListBinding;
    private MovieModelView movieModelView;
    private MovieAdapter movieAdapter;
    private List<MovieResult> movieResultList;
    private ItemClickListener itemClickListener = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialising the view by using databinding layout
        activityMovieListBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list);

        //Initialising the viewmodel by using ViewModelProviders
        movieModelView = ViewModelProviders.of(this).get(MovieModelView.class);
        initView();

        // get all the movies list from the server,
        getMoviesList();
    }

    private void initView() {
        mContext = this;
    }

    private void getMoviesList() {
        // Start shimmer animation until data loaded from the server
        activityMovieListBinding.shimmerViewContainer.startShimmerAnimation();

        /*
         * Check whether internet connection is available or not,
         * If no internet connection then it will take data from the local database
         */
        if (NetworkCheck.isNetworkConnected(mContext)) {
            // Calling the getTopRatedMovies() to get the movies list from the viewmodel

            movieModelView.getTopRatedMovies();

            // Once get the response from the server, observe will get notify it and response back with model class values
            movieModelView.getMovieResponse().observe(this, new Observer<MovieResponse>() {
                @Override
                public void onChanged(MovieResponse movieResponse) {
                    movieResultList = movieResponse.getResults();

                    // Call this method for sending the data to adapter
                    showMoviesList();

                    // Delete the movies list from the database before inserting the data to the local db
                    movieModelView.deleteMoviesToLocal();

                    /*
                     * Get the number of movies and pass it to the view model for the insert movie data,
                     * it will add till last movie
                     */
                    int movieSize = movieResultList.size();
                    for (int i = 0; i < movieSize; i++) {
                        movieModelView.insertMoviesToLocal(movieResultList.get(i));
                    }
                }
            });
        } else {
            // Get the movie record from the local db,
            movieModelView.getTopRatedMoviesFromLocal();

            // Once get the response from the local db, observe will get notify it and response back with model class values
            movieModelView.getMovieListFromLocal().observe(this, new Observer<List<MovieResult>>() {
                @Override
                public void onChanged(List<MovieResult> movieResult) {
                    movieResultList = movieResult;
                    /* There is no data available initial state, that time movieResultList is become null value
                     * Then it will show error message, else it pass the movie db record to adapter and show it
                     */
                    if (movieResultList != null) {
                        showMoviesList();
                    } else {
                        Toast.makeText(mContext, "Please check your network connection", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }

    private void showMoviesList() {
        // Hide the shimmer view controller once get the response from the server or local
        activityMovieListBinding.shimmerViewContainer.stopShimmerAnimation();
        activityMovieListBinding.shimmerViewContainer.setVisibility(View.GONE);


        /**
         *Take the movies list from the result and pass it to the Recycler view adapter,
         * Which contain movie image, title, ratings, release date and contents
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        activityMovieListBinding.listMovieId.setLayoutManager(linearLayoutManager);
        movieAdapter = new MovieAdapter(mContext, movieResultList);
        activityMovieListBinding.listMovieId.setAdapter(movieAdapter);
        movieAdapter.setOnItemClickListener(itemClickListener);
    }

    @Override
    public void onItemClick(View view, int position) {
        /*
        Based on the item position, it will get the movie data from the list and keep it into the
        MovieResult,
         */
        MovieResult movieResult = movieResultList.get(position);
        Intent movieDetailsIntent = new Intent(mContext, MovieDetailsActivity.class);

        //Make the MovieResult class as Parcelable for passing the object between activities.
        movieDetailsIntent.putExtra("movie_details", movieResult);
        startActivity(movieDetailsIntent);

        /* Start animation while opening the movie details activity,
        For that we have to add animation file inside anim folder
        R.anim.slide_up --> for opening new activity to top
        R.anim.activity_stay --> to stay current activity
         */
        overridePendingTransition(R.anim.slide_up, R.anim.activity_stay);
    }

}
