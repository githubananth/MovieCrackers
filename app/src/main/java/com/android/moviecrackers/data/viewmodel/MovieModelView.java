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

    private MutableLiveData<MovieResponse> responseMutableLiveData = new MutableLiveData<>();
    private LiveData<List<MovieResult>> listMutableLiveData = new MutableLiveData<>();

    /*
    By extending AndroidViewModel class it is mandatory to define constructor
    Initialise the local and server repository
     */

    public MovieModelView(@NonNull Application application) {
        super(application);
        this.application = application;
        this.movieServerRepository = new MovieServerRepository();
        this.movieLocalRepository = new MovieLocalRepository(application);
    }

    /*
    Call server manipulation process for get movie details
    Which will return as Mutable live data format
    */

    public void getTopRatedMovies() {
        this.movieServerRepository = new MovieServerRepository();
        responseMutableLiveData = movieServerRepository.getTopRatedMovies();
    }

    /*
    Call local db manipulation process for insert, get and delete movie details
    Which will return as Live data format
    */
    public void getTopRatedMoviesFromLocal() {
        listMutableLiveData = movieLocalRepository.getMoviesList();
    }

    public void insertMoviesToLocal(MovieResult movieResult) {
        movieLocalRepository.insert(movieResult);
    }

    public void deleteMoviesToLocal() {
        movieLocalRepository.deleteMoviesToLocal();
    }

    public LiveData<MovieResponse> getMovieResponse() {
        return responseMutableLiveData;
    }

    public LiveData<List<MovieResult>> getMovieListFromLocal() {
        return listMutableLiveData;
    }
}
