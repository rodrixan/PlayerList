package com.projects.rodrixan.playerlist.functionality.service;

import com.projects.rodrixan.playerlist.model.Player;
import com.projects.rodrixan.playerlist.model.PlayerResponse;

import java.util.List;

public interface PlayerListManager {
    void getPlayers(PlayerListCallback presenter);

    interface PlayerListCallback {
        void onUsersReceivedFromService(List<PlayerResponse> players);

        void onPlayerError(String message);
    }

    void cancelPlayersCall();
}
