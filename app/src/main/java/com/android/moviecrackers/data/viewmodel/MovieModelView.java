package com.android.moviecrackers.data.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.airbnb.lottie.L;
import com.android.moviecrackers.data.repository.MovieLocalRepository;
import com.android.moviecrackers.data.repository.MovieServerRepository;
import com.android.moviecrackers.model.movielist.MovieResponse;
import com.android.moviecrackers.model.movielist.MovieResult;

import java.util.List;

public class MovieModelView extends AndroidViewModel {

    private Application application;
    private MovieServerRepository movieServerRepository;
    private MovieLocalRepository movieLocalRepository;
    private MutableLiveData<MovieResponse> movieResponseMutableLiveData;

    private MutableLiveData<MovieResponse> responseMutableLiveData = new MutableLiveData<>();
    private LiveData<List<MovieResult>> listMutableLiveData = new MutableLiveData<>();

    public MovieModelView(@NonNull Application application) {
        super(application);
        this.application = application;
        this.movieServerRepository = new MovieServerRepository();
        this.movieLocalRepository = new MovieLocalRepository(application);
    }

    public void getTopRatedMovies() {
        this.movieServerRepository = new MovieServerRepository();
        responseMutableLiveData = movieServerRepository.getTopRatedMovies();
    }

    public void getTopRatedMoviesFromLocal() {
        listMutableLiveData = movieLocalRepository.getMoviesList();
    }

    public void insertMoviesToLocal(MovieResult movieResult) {
        movieLocalRepository.insert(movieResult);
    }

    public LiveData<MovieResponse> getMovieResponse() {
        return responseMutableLiveData;
    }

    public LiveData<List<MovieResult>> getMovieListFromLocal() {
        return listMutableLiveData;
    }
}
