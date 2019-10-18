package com.android.moviecrackers.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.moviecrackers.R;
import com.android.moviecrackers.data.viewmodel.CompanyModelView;
import com.android.moviecrackers.databinding.ActivityMovieDetailsBinding;
import com.android.moviecrackers.model.moviedetails.MovieDetailsResponse;
import com.android.moviecrackers.model.moviedetails.ProductionCompany;
import com.android.moviecrackers.model.movielist.MovieResult;
import com.android.moviecrackers.utility.NetworkCheck;
import com.android.moviecrackers.view.adapter.CompanyAdapter;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {
    private static final String TAG = MovieDetailsActivity.class.getSimpleName();
    private Context mContext;
    private ActivityMovieDetailsBinding movieDetailsBinding;
    private MovieResult movieResult;

    private CompanyModelView companyModelView;
    private List<ProductionCompany> productionCompanyList;
    private CompanyAdapter companyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        companyModelView = ViewModelProviders.of(this).get(CompanyModelView.class);
        initView();
        getMovieIntentDetails();
        setToolBarContent();
        if (NetworkCheck.isNetworkConnected(mContext)) {
            getMovieDetails();
        } else {
            movieDetailsBinding.includeMovieDetailsId.shimmerViewContainer.setVisibility(View.GONE);
            movieDetailsBinding.includeMovieDetailsId.imgNoNetworkId.setVisibility(View.VISIBLE);
        }

    }

    private void initView() {
        mContext = this;
    }

    private void getMovieIntentDetails() {
        movieResult = getIntent().getParcelableExtra("movie_details");
        movieDetailsBinding.setMovie(movieResult);
    }

    private void setToolBarContent() {
        setSupportActionBar(movieDetailsBinding.toolbar);
        movieDetailsBinding.toolbarLayout.setTitle(movieResult.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        movieDetailsBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getMovieDetails() {
        movieDetailsBinding.includeMovieDetailsId.shimmerViewContainer.startShimmerAnimation();
        companyModelView.getProductionCompany(movieResult.getId());
        companyModelView.getCompanyResponse().observe(this, new Observer<MovieDetailsResponse>() {
            @Override
            public void onChanged(MovieDetailsResponse movieDetailsResponse) {

                movieDetailsBinding.includeMovieDetailsId.shimmerViewContainer.stopShimmerAnimation();
                movieDetailsBinding.includeMovieDetailsId.shimmerViewContainer.setVisibility(View.GONE);
                productionCompanyList = movieDetailsResponse.getProductionCompanies();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                movieDetailsBinding.includeMovieDetailsId.listProductionCompanyId.setLayoutManager(linearLayoutManager);
                companyAdapter = new CompanyAdapter(mContext, productionCompanyList);
                movieDetailsBinding.includeMovieDetailsId.listProductionCompanyId.setAdapter(companyAdapter);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition( R.anim.activity_stay, R.anim.slide_bottom );
    }

}
