package com.projects.rodrixan.playerlist.common.rest;



import com.projects.rodrixan.playerlist.model.ErrorResponse;

import retrofit2.Response;

public interface ResponseCallback<T> {
    void onResponseOK(Response<T> response);

    void onResponseKO(ErrorResponse errorResponse);
}
