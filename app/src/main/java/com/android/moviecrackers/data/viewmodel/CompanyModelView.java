package com.android.moviecrackers.data.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.moviecrackers.data.repository.CompanyRepository;
import com.android.moviecrackers.model.moviedetails.MovieDetailsResponse;

public class CompanyModelView extends AndroidViewModel {

    public CompanyModelView(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<MovieDetailsResponse> responseMutableLiveData = new MutableLiveData<>();

    public void getProductionCompany(Integer movieId) {
        responseMutableLiveData = CompanyRepository.getInstance().getProductionCompanies(movieId);
    }

    public LiveData<MovieDetailsResponse> getCompanyResponse() {
        return responseMutableLiveData;
    }
}
