package com.android.moviecrackers.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.moviecrackers.data.network.ApiServices;
import com.android.moviecrackers.data.network.RetroClient;
import com.android.moviecrackers.model.moviedetails.MovieDetailsResponse;
import com.android.moviecrackers.utility.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CompanyRepository {
    private static CompanyRepository companyRepository;
    private static final String TAG = CompanyRepository.class.getSimpleName();

    public static CompanyRepository getInstance() {

        if (companyRepository == null) {
            companyRepository = new CompanyRepository();
        }
        return companyRepository;
    }

    private ApiServices apiServices;
    private MutableLiveData<MovieDetailsResponse> companyMutableLiveData;

    private CompanyRepository() {
        apiServices = RetroClient.getServiceApi(API.BASE_URL);
    }


    public MutableLiveData<MovieDetailsResponse> getProductionCompanies(Integer movieId) {

        companyMutableLiveData = new MutableLiveData<>();

        Call<MovieDetailsResponse> defaultResponseCall = apiServices.getProductionCompany(movieId, API.API_KEY);

        Log.e(TAG, "onRequestURL" + defaultResponseCall.request().url());

        defaultResponseCall.enqueue(new Callback<MovieDetailsResponse>() {
            @Override
            public void onResponse(Call<MovieDetailsResponse> call, Response<MovieDetailsResponse> response) {
                if (response.isSuccessful()) {
                    companyMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieDetailsResponse> call, Throwable t) {
                Log.e("Error CompanyError", "onFailure: .", t);
            }
        });

        return companyMutableLiveData;
    }

}
