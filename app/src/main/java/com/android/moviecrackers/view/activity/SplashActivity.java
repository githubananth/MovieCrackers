package com.android.moviecrackers.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.android.moviecrackers.R;

public class SplashActivity extends AppCompatActivity {

    /* Delay Duration for splash screen, it will stay until this given duration time */
    private static final int DELAY_DURATION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        delayScreen();
    }

    /**
     * Handler's run function wait until delay duration over, after that pageRedirection() get called.
     */
    void delayScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pageRedirection();
            }
        }, DELAY_DURATION);
    }

    /**
     * Once delay time over this method get called once and redirect to MovieListActivity.class,
     * Where as we have defined all the movies list network call and showing the list
     */

    private void pageRedirection() {
        Intent homeIntent = new Intent(this, MovieListActivity.class);
        startActivity(homeIntent);
        finish();
    }
}
