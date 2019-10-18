package com.android.moviecrackers.view.activity;

import android.app.AlertDialog;
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
import com.android.moviecrackers.utility.CustomProgressBar;
import com.android.moviecrackers.utility.NetworkCheck;
import com.android.moviecrackers.view.adapter.MovieAdapter;

import java.util.List;

public class MovieListActivity extends AppCompatActivity implements ItemClickListener {
    private Context mContext;
    private ActivityMovieListBinding activityMovieListBinding;
    private MovieModelView movieModelView;
    private CustomProgressBar customProgressBar;
    private MovieAdapter movieAdapter;
    private View dialogView;
    private AlertDialog mAlertDialog;
    private List<MovieResult> movieResultList;
    private ItemClickListener itemClickListener = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMovieListBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list);
        movieModelView = ViewModelProviders.of(this).get(MovieModelView.class);
        initView();

        activityMovieListBinding.shimmerViewContainer.startShimmerAnimation();
        if (NetworkCheck.isNetworkConnected(mContext)) {
            movieModelView.getTopRatedMovies();
            movieModelView.getMovieResponse().observe(this, new Observer<MovieResponse>() {
                @Override
                public void onChanged(MovieResponse movieResponse) {
                    activityMovieListBinding.shimmerViewContainer.stopShimmerAnimation();
                    activityMovieListBinding.shimmerViewContainer.setVisibility(View.GONE);
                    movieResultList = movieResponse.getResults();
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                    activityMovieListBinding.listMovieId.setLayoutManager(linearLayoutManager);
                    movieAdapter = new MovieAdapter(mContext, movieResultList);
                    activityMovieListBinding.listMovieId.setAdapter(movieAdapter);
                    movieAdapter.setOnItemClickListener(itemClickListener);
                    int movieSize = movieResultList.size();

                    for (int i = 0; i < movieSize; i++) {
                        movieModelView.insertMoviesToLocal(movieResultList.get(i));
                    }
                }
            });
        } else {
            movieModelView.getTopRatedMoviesFromLocal();
            movieModelView.getMovieListFromLocal().observe(this, new Observer<List<MovieResult>>() {
                @Override
                public void onChanged(List<MovieResult> movieResult) {
                    movieResultList = movieResult;
                    if (movieResultList != null) {
                        activityMovieListBinding.shimmerViewContainer.stopShimmerAnimation();
                        activityMovieListBinding.shimmerViewContainer.setVisibility(View.GONE);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                        activityMovieListBinding.listMovieId.setLayoutManager(linearLayoutManager);
                        movieAdapter = new MovieAdapter(mContext, movieResultList);
                        activityMovieListBinding.listMovieId.setAdapter(movieAdapter);
                        movieAdapter.setOnItemClickListener(itemClickListener);
                    } else {
                        Toast.makeText(mContext, "Please check your network connection", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

    }

    private void initView() {
        mContext = this;
        customProgressBar = new CustomProgressBar(mContext);
    }

    @Override
    public void onItemClick(View view, int position) {
        MovieResult movieResult = movieResultList.get(position);
        Intent movieDetailsIntent = new Intent(mContext, MovieDetailsActivity.class);
        movieDetailsIntent.putExtra("movie_details", movieResult);
        startActivity(movieDetailsIntent);
    }


}
