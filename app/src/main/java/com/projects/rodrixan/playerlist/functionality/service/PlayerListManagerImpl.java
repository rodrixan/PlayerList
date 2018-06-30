package com.projects.rodrixan.playerlist.functionality.service;

import com.projects.rodrixan.playerlist.common.base.PlayerListApplication;
import com.projects.rodrixan.playerlist.common.rest.PlayerService;
import com.projects.rodrixan.playerlist.common.rest.ResponseCallback;
import com.projects.rodrixan.playerlist.common.rest.ServiceCallback;
import com.projects.rodrixan.playerlist.model.ErrorResponse;
import com.projects.rodrixan.playerlist.model.Player;
import com.projects.rodrixan.playerlist.model.PlayerResponse;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EBean(scope = EBean.Scope.Singleton)
public class PlayerListManagerImpl implements PlayerListManager {
    @Bean
    protected PlayerService mPlayerService;

    private Call<List<PlayerResponse>> mPlayerListCall;

    @Override
    public void getPlayers(PlayerListCallback presenter) {
        if (presenter != null) {
            if (mPlayerService == null) {
                presenter.onPlayerError(
                        PlayerListApplication.getInstance().getDefaultErrorMessage());
            }
            else {
                mPlayerListCall = mPlayerService.getApiService().getPlayers();
                mPlayerListCall.enqueue(
                        new ServiceCallback<>(new ResponseCallback<List<PlayerResponse>>() {
                            @Override
                            public void onResponseOK(Response<List<PlayerResponse>> response) {
                                if (response == null || response.body() == null || response.body()
                                        .isEmpty()) {
                                    onResponseKO(new ErrorResponse());
                                }
                                else {
                                    presenter.onUsersReceivedFromService(response.body());
                                }
                            }

                            @Override
                            public void onResponseKO(ErrorResponse errorResponse) {
                                if (errorResponse == null || errorResponse.getError() == null ||
                                        errorResponse
                                        .getError()
                                        .isEmpty()) {
                                    presenter.onPlayerError(PlayerListApplication.getInstance()
                                            .getDefaultErrorMessage());
                                }
                                else {
                                    presenter.onPlayerError(errorResponse.getError());
                                }
                            }
                        }));
            }
        }
    }


    @Override
    public void cancelPlayersCall() {
        if (mPlayerListCall != null && mPlayerListCall.isExecuted()) {
            mPlayerListCall.cancel();
        }
    }
}
