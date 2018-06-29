package com.projects.rodrixan.playerlist.functionality.presenter;

import com.projects.rodrixan.playerlist.functionality.view.PlayerListView;

public interface PlayerListPresenter {

    void initPresenter(PlayerListView view);
    void getPlayerList();
    void cancelUsersCall();
}
