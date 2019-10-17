package com.android.moviecrackers.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.android.moviecrackers.R;

public class SplashActivity extends AppCompatActivity {

    private static final int DELAY_DURATION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        delayScreen();
    }

    void delayScreen() {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pageRedirection();
            }
        }, DELAY_DURATION);
    }

    private void pageRedirection() {
        Intent homeIntent = new Intent(this, MovieListActivity.class);
        startActivity(homeIntent);
        finish();
    }
}
