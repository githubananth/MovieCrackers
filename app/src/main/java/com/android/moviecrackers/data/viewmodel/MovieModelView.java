package com.android.moviecrackers.data.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.moviecrackers.data.repository.MovieRepository;
import com.android.moviecrackers.model.movielist.MovieResponse;

public class MovieModelView extends ViewModel {

    private MutableLiveData<MovieResponse> responseMutableLiveData = new MutableLiveData<>();

    public void getTopRatedMovies() {
        responseMutableLiveData = MovieRepository.getInstance().getTopRatedMovies();
    }

    public LiveData<MovieResponse> getMovieResponse() {
        return responseMutableLiveData;
    }
}
