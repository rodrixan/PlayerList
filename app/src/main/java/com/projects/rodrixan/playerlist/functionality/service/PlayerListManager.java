package com.projects.rodrixan.playerlist.functionality.service;

import com.projects.rodrixan.playerlist.model.Player;

import java.util.List;

public interface PlayerListManager {
    void getPlayers(PlayerListCallback presenter);

    interface PlayerListCallback {
        void onUsersReceivedFromService(List<Player> players);

        void onPlayerError(String message);
    }

    void cancelPlayersCall();
}
