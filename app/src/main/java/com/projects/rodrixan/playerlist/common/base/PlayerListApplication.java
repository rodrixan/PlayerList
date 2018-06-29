package com.projects.rodrixan.playerlist.common.base;

import android.app.Application;
import android.util.Log;

import com.projects.rodrixan.playerlist.R;

import org.androidannotations.annotations.EApplication;

@EApplication
public class PlayerListApplication extends Application {
    private static final String TAG = PlayerListApplication.class.getSimpleName();

    private static volatile PlayerListApplication sInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "On create Application");
        sInstance = this;
    }

    public static PlayerListApplication getInstance() {
        return sInstance;
    }

    public String getDefaultErrorMessage() {
        return getString(R.string.default_error_message);
    }

}
