package com.projects.rodrixan.playerlist.functionality.view;

import com.projects.rodrixan.playerlist.common.view.BaseView;
import com.projects.rodrixan.playerlist.model.Player;

import java.util.List;

public interface PlayerListView extends BaseView {

    void onPlayersReceived(List<Player> players);
}
