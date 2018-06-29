package com.projects.rodrixan.playerlist.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.projects.rodrixan.playerlist.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //nothing to load before launch first activity, so...
//        RandomUsersListActivity_.intent(this).isFavouriteList(false).start();
        finish();
    }
}