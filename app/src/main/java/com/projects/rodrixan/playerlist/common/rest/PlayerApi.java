package com.projects.rodrixan.playerlist.common.rest;


import com.projects.rodrixan.playerlist.model.PlayerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlayerApi {

    @GET("/bins/66851")
    Call<PlayerResponse> getPlayers();
}
