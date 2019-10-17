package com.android.moviecrackers.data.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.moviecrackers.data.repository.CompanyRepository;
import com.android.moviecrackers.model.moviedetails.MovieDetailsResponse;

public class CompanyModelView extends ViewModel {

    private MutableLiveData<MovieDetailsResponse> responseMutableLiveData = new MutableLiveData<>();

    public void getProductionCompany(Integer movieId) {
        responseMutableLiveData = CompanyRepository.getInstance().getProductionCompanies(movieId);
    }

    public LiveData<MovieDetailsResponse> getCompanyResponse() {
        return responseMutableLiveData;
    }
}
