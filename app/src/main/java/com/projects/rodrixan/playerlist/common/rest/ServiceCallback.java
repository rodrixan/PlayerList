package com.projects.rodrixan.playerlist.common.rest;

import android.util.Log;

import com.projects.rodrixan.playerlist.common.base.PlayerListApplication;
import com.projects.rodrixan.playerlist.common.util.StringUtils;
import com.projects.rodrixan.playerlist.model.ErrorResponse;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceCallback<T> implements Callback<T> {
    private static final String TAG = ServiceCallback.class.getSimpleName();

    private ResponseCallback<T> mRestCallback;

    public ServiceCallback(ResponseCallback<T> ResponseCallback) {
        mRestCallback = ResponseCallback;

    }

    @Override
    public void onResponse(final Call<T> call, final Response<T> response) {
        String defaultError = PlayerListApplication.getInstance().getDefaultErrorMessage();
        if (response == null) {
            if (mRestCallback != null) {
                mRestCallback.onResponseKO(new ErrorResponse(defaultError));
            }
        }
        else {
            Log.d(TAG,
                    "Request successful? " + response.isSuccessful() + ". Canceled? " + call
                            .isCanceled() + ". HTTP Code:  " + response
                            .code() + ". URL: " + call.request().url().url());

            switch (response.code()) {
                case HttpURLConnection.HTTP_OK:
                    if (mRestCallback != null) {
                        mRestCallback.onResponseOK(response);
                    }
                    break;

                default:
                    ErrorResponse errorResponse = StringUtils.parseErrorFromService(response,
                            defaultError);

                    if (mRestCallback != null) {
                        mRestCallback.onResponseKO(errorResponse);
                    }
                    break;
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.e(TAG, "Failure on call: " + t.getMessage(), t);
        if (mRestCallback != null) {
            mRestCallback.onResponseKO(new ErrorResponse(
                    PlayerListApplication.getInstance().getDefaultErrorMessage()));
        }
    }

}
